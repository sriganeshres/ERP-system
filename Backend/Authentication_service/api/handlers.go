package api

import (
	"errors"
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

	var userData models.UserData
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
	if user, er := app.Db.GetUserByEmail(userData.Email); user != nil {
		ctx.JSON(http.StatusBadRequest, "Already the email is in use.")
		return er
	}
	userData.Password = utils.HashString(password)
	if errorer := app.Db.CreateUser(&userData); errorer != nil {
		ctx.JSON(http.StatusBadRequest, errorer)
		return errorer
	}
	ctx.JSON(http.StatusCreated, userData.Username)
	return nil
}
