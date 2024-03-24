package api

import (
	"github.com/labstack/echo/v4"
	"github.com/sriganeshres/WorkHub-Pro/Backend/Authentication_service/database"
)

type Config struct {
	Router *echo.Echo
	Db *database.Database
}

func (app *Config) Routes() {
	app.Router.POST("/api/login", app.Login)
    app.Router.POST("/api/signup", app.Signup)
}
