package api

import (
	"fmt"
	"net/http"

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

// func (app *Config) JoinWorkHub(ctx *echo.Context) error {
// 	fmt.Println("Joining WorkHub...")

// }

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