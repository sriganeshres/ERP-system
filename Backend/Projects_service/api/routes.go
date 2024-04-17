package api

import (
	"github.com/labstack/echo/v4"
	"github.com/sriganeshres/WorkHub-Pro/Backend/Projects_service/database"
)

type Config struct {
	Router *echo.Echo
	Db     *database.Database
}

func (app *Config) Routes() {
	app.Router.POST("/api/createproject", app.CreateProject)
	app.Router.GET("/api/Project/:id", app.GetProject)
	app.Router.DELETE("/api/Project/:id", app.Deleteproject)
	app.Router.GET("/api/Projects/:id", app.GetAllProjects)
}
