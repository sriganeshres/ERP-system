package api

import (
	"fmt"
	"net/http"
	"time"

	"github.com/golang-jwt/jwt/v5"

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
	if user == nil {
		return ctx.JSON(http.StatusBadRequest, "Invalid credentials")
	}
	whether, erro := utils.VerifyPassword(password, user.Password)
	if whether == false {
		fmt.Printf(erro.Error())
		return ctx.JSON(http.StatusBadRequest, "Invalid password")
	}

	// Set custom claims
	token := jwt.New(jwt.SigningMethodHS256)
	claims := token.Claims.(jwt.MapClaims)
	claims["email"] = user.Email
	claims["exp"] = time.Now().Add(time.Hour * 24).Unix()
	t, err := token.SignedString([]byte("secret"))
	if err != nil {
		return err
	}
	ctx.JSON(http.StatusCreated, echo.Map{
		"token": t,
		"user":  user,
	})

	return nil
}
func (app *Config) VerifyToken(ctx echo.Context) error {

	var tokenString string
	err := ctx.Bind(&tokenString)
	if err != nil {
		return ctx.JSON(http.StatusBadRequest, err.Error())
	}
	token, err := jwt.Parse(tokenString, func(token *jwt.Token) (interface{}, error) {
		return []byte("secret"), nil
	})
	if err != nil {
		return err
	}
	claims, ok := token.Claims.(jwt.MapClaims)
	if !ok {
		return ctx.JSON(http.StatusUnauthorized, "Invalid token claims")
	}

	// Extract email from claims
	email, ok := claims["email"].(string)
	if !ok {
		return ctx.JSON(http.StatusUnauthorized, "Invalid token claims")
	}

	// Now you have the email extracted from the token
	// Use it as needed in your application logic
	user, err := app.Db.GetUserByEmail(email)
	if err != nil {
		return err
	}
	return ctx.JSON(http.StatusOK, user)

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

	var userData models.UserData
	err1 := ctx.Bind(&userData)
	userData.Role = "admin"

	if err1 != nil {

		return ctx.JSON(http.StatusBadRequest, err1.Error())
	}
	password := userData.Password
	if password == "" || len(password) <= 8 {
		return fmt.Errorf("password must be at least 8 characters long")
	}
	if user, er := app.Db.GetUserByEmail(userData.Email); user != nil {
		if er == nil {
			return fmt.Errorf("already the email is in use")
		}
	}
	userData.Password = utils.HashString(password)
	if errorer := app.Db.CreateUser(&userData); errorer != nil {
		ctx.JSON(http.StatusBadRequest, errorer)
		return errorer
	}
	token := jwt.New(jwt.SigningMethodHS256)
	claims := token.Claims.(jwt.MapClaims)
	claims["email"] = userData.Email
	claims["exp"] = time.Now().Add(time.Hour * 24).Unix()
	t, err := token.SignedString([]byte("secret"))
	if err != nil {
		return err
	}

	ctx.JSON(http.StatusCreated, echo.Map{
		"user":  userData,
		"token": t,
	})
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
