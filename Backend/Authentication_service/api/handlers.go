package api

import (
	"errors"
	"log"
	"net/http"

	"github.com/labstack/echo/v4"
	"github.com/sriganeshres/WorkHub-Pro/Backend/Authentication_service/database"
	"github.com/sriganeshres/WorkHub-Pro/Backend/Authentication_service/utils"
)

func (app *Config) Login(ctx echo.Context) error {
	var userData database.LoginUser
	err := ctx.Bind(userData)
	if err != nil {
		return ctx.JSON(http.StatusBadRequest, err.Error())
	}
	// email := userData.Email
	// password := userData.Password
	// user, err := app.Db.GetUserByEmail(email)

	// Return the users as JSON response
	return ctx.JSON(http.StatusOK, userData)
}

func (app *Config) Signup(ctx echo.Context) error {

	var userData database.UserData

	log.Printf("hi")
	err := ctx.Bind(&userData)

	if err != nil {
		return ctx.JSON(http.StatusBadRequest, err.Error())
	}
	password := userData.Password
	if password == "" {
		ctx.JSON(http.StatusBadRequest, err.Error())
		return errors.New("no Password was sent")
	} else if len(password) <= 8 {
		ctx.JSON(http.StatusBadRequest, err.Error())
		return errors.New("password must be at least 8 characters long")
	}
	userData.Password = utils.HashString(password)
	if errorer := app.Db.DB.Create(&userData).Error; errorer != nil {
		ctx.JSON(http.StatusBadRequest, errorer.Error())
		return errorer
	}
	ctx.JSON(http.StatusCreated, userData.Username)
	return nil
}
