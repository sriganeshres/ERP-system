package api

import (
	"fmt"
	"net/http"
	"strconv"

	"github.com/labstack/echo/v4"
	// "github.com/sriganeshres/WorkHub-Pro/Backend/Projects_service/utils"
	"github.com/sriganeshres/WorkHub-Pro/Backend/models"
)

func (app *Config) CreateProject(ctx echo.Context) error {
	fmt.Println("Handling POST request Workhub...")
	var Project models.Project
	err := ctx.Bind(&Project)
	if err != nil {
		fmt.Println("error in bindin")
		return ctx.JSON(http.StatusBadGateway, err.Error())
	}
	
	if errorer := app.Db.CreateProject(&Project); errorer != nil {
		ctx.JSON(http.StatusBadRequest, errorer)
		return errorer
	}
	ctx.JSON(http.StatusCreated, Project)
	return nil
}

func (app *Config) GetProject(ctx echo.Context) error {
	fmt.Println("Handling GET request Workhub...")
	code := ctx.Param("id")
	if code == "" {
		return ctx.JSON(http.StatusBadRequest, "Invalid project code")
	}

	projectId, err := strconv.Atoi(code)
if err != nil {
    // Handle the error if the conversion fails
    return ctx.JSON(http.StatusBadRequest, "Invalid project code")
}

project, errorer := app.Db.FindProject(projectId)
	if project == nil {
		return ctx.JSON(http.StatusNotFound, "Project not found")
	}
	if errorer != nil {
		ctx.JSON(http.StatusBadRequest, errorer)
		return errorer
	}
	ctx.JSON(http.StatusCreated, project)
	return nil
}

func (app *Config) Deleteproject(ctx echo.Context) error {
	code:=ctx.Param("id")
	ProjectId,err1:=strconv.Atoi(code)
	if err1!=nil{
		ctx.JSON(http.StatusBadRequest,err1)
		return err1
	}
	err:=app.Db.DeleteProject(ProjectId)
	if err!=nil{
		ctx.JSON(http.StatusBadRequest,err)
	}
	return nil
}

func (app *Config) GetAllProjects(ctx echo.Context) error {
	fmt.Println("Handling GET request Workhub...")
	code:=ctx.Param("id")
	workhub_id,err1:=strconv.Atoi(code)
	if err1!=nil{
		ctx.JSON(http.StatusBadRequest,err1)
		return err1
	}
	project, errorer := app.Db.GetProjectsForWorkhub(workhub_id)	
	if errorer != nil {
		ctx.JSON(http.StatusBadRequest, errorer)
		return errorer
	}
	ctx.JSON(http.StatusCreated, project)
	return nil
}