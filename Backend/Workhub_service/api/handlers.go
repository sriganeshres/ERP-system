package api

import (
	"encoding/json"
	"errors"
	"fmt"
	"io/ioutil"
	"net/http"
	"strconv"

	"github.com/labstack/echo/v4"
	"github.com/sriganeshres/WorkHub-Pro/Backend/Workhub_service/utils"
	"github.com/sriganeshres/WorkHub-Pro/Backend/models"
)

func (app *Config) CreateWorkHub(ctx echo.Context) error {
	fmt.Println("Handling POST request Workhub...")
	var WorkHub models.WorkHub
	err := ctx.Bind(&WorkHub)
	if err != nil {
		return ctx.JSON(http.StatusBadRequest, err.Error())
	}
	var Code = utils.GenerateRandomCode()
	WorkHub.PrivacyKey = Code
	if errorer := app.Db.CreateWorkhub(&WorkHub); errorer != nil {
		ctx.JSON(http.StatusBadRequest, errorer)
		return errorer
	}
	ctx.JSON(http.StatusCreated, WorkHub)
	return nil
}

func (app *Config) JoinWorkHub(ctx echo.Context) error {
	fmt.Println("Joining WorkHub...")
	var JoinData models.JoinWorkHub
	err := ctx.Bind(&JoinData)
	if err != nil {
		ctx.JSON(http.StatusBadRequest, err.Error())
		return err
	}
	key := JoinData.PrivacyKey
	email := JoinData.UserEmail
	id, err := app.VerifyKey(key)
	if err != nil {
		ctx.JSON(http.StatusBadRequest, "Invalid key")
		return errors.New("invalid key")
	}
	url := "http://localhost:8000/api/user?email=" + email // Replace with your actual server URL
	// Make GET request
	response, err := http.Get(url)
	if err != nil {
		fmt.Println("Error:", err)
		return err
	}
	defer response.Body.Close()

	// Read response body
	body, err := ioutil.ReadAll(response.Body)
	if err != nil {
		fmt.Println("Error reading response body:", err)
		return err
	}

	// Print response body
	fmt.Println("Response:", string(body))
	var user models.UserData
	err = json.Unmarshal([]byte(body), &user)
	if err != nil {
		fmt.Println("Error parsing response body:", err)
		return err
	}
	if errorer := app.Db.UpdateWorkhub(id, user); errorer != nil {
		ctx.JSON(http.StatusBadRequest, errorer)
		return errorer
	}
	ctx.JSON(http.StatusCreated, "Successfully joined workhub")
	return nil
}

func (app *Config) Verify(ctx echo.Context) error {
	var requestData struct {
		Code int `json:"code"`
	}
	if err := ctx.Bind(&requestData); err != nil {
		return err
	}
	code := requestData.Code

	workhub, err := app.Db.FindWorkHub(code)
	if err != nil {
		return err
	}
	ctx.JSON(http.StatusCreated, workhub)
	return nil
}

func (app *Config) VerifyKey(key int) (uint, error) {
	WorkHub, err := app.Db.FindWorkHub(key)
	if err != nil {
		return 0, errors.New("invalid key")
	}
	fmt.Println(WorkHub.Name)
	return WorkHub.ID, nil
}

func (app *Config) GetWorkHub(ctx echo.Context) error {
	key := ctx.QueryParam("key")
	int_key, err := strconv.Atoi(key)
	if err != nil {
		ctx.JSON(http.StatusBadRequest, "Invalid key")
		return err
	}
	Workhub, err := app.Db.FindWorkHub(int_key)
	if err != nil {
		ctx.JSON(http.StatusBadRequest, "Invalid key")
		return err
	}
	ctx.JSON(http.StatusOK, strconv.Itoa(int(Workhub.ID)))
	return nil
}
