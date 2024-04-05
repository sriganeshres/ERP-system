package api

import (
	"errors"
	"fmt"
	"net/http"

	"github.com/labstack/echo/v4"
	"github.com/sriganeshres/WorkHub-Pro/Backend/Authentication_service/utils"
	"github.com/sriganeshres/WorkHub-Pro/Backend/models"
)

func (app *Config) Login(ctx echo.Context) error {
	var userData models.LoginUser
	err := ctx.Bind(&userData)
	if err != nil {
		return ctx.JSON(http.StatusBadRequest, err.Error())
	}
	email := userData.Email
	password := userData.Password
	user, err := app.Db.GetUserByEmail(email)
	if err != nil {
		return ctx.JSON(http.StatusBadRequest, err.Error())
	}
	if whether, erro := utils.VerifyPassword(password, user.Password); erro == nil {
		if whether {
			return ctx.JSON(http.StatusOK, user.Username)
		} else {
			return ctx.JSON(http.StatusBadRequest, "Invalid password")
		}
	} else {
		return ctx.JSON(http.StatusUnauthorized, "Invalid credentials")
	}
}

func (app *Config) Signup(ctx echo.Context) error {
	body := ctx.Request().Body
	defer body.Close()

	// Create a byte slice to hold the body content
	var data []byte
	_, err := body.Read(data)
	if err != nil {
		return ctx.String(http.StatusInternalServerError, "Error reading request body")
	}

	// Print the raw body content (for debugging purposes)
	fmt.Println("Request Body:", string(data))
	var userData models.UserData
	err1 := ctx.Bind(&userData)
	// userData.Role = "admin"
	fmt.Println("Handling GET request...")
	fmt.Println(userData)
	if err1 != nil {
		fmt.Println(err1.Error())
		fmt.Println("i am the fire")
		return ctx.JSON(http.StatusBadRequest, err1.Error())
	}
	password := userData.Password
	if password == "" {
		ctx.JSON(http.StatusBadRequest, err.Error())
		return errors.New("no Password was sent")
	} else if len(password) <= 8 {
		ctx.JSON(http.StatusBadRequest, err.Error())
		return errors.New("password must be at least 8 characters long")
	}
	if user, er := app.Db.GetUserByEmail(userData.Email); user != nil {
		ctx.JSON(http.StatusBadRequest, "Already the email is in use.")
		fmt.Println(er)
		return er
	}
	userData.Password = utils.HashString(password)
	if errorer := app.Db.CreateUser(&userData); errorer != nil {
		ctx.JSON(http.StatusBadRequest, errorer)
		fmt.Println(errorer)
		return errorer
	}
	fmt.Println("hEY i am good here no error")
	ctx.JSON(http.StatusCreated, userData)
	return nil
}

func (app *Config) SendEmailHandler(ctx echo.Context) error {
	var requestData struct {
		Email string `json:"email"`
		Code  int    `json:"code"`
	}

	if err := ctx.Bind(&requestData); err != nil {
		return ctx.JSON(http.StatusBadRequest, map[string]string{"error": "Invalid request"})
	}
	email := requestData.Email
	code := requestData.Code

	var err = utils.SendEmail(email, code)
	if err != nil {
		return ctx.String(http.StatusInternalServerError, err.Error())
	}
	return ctx.String(http.StatusOK, "Email sent successfully")
}

