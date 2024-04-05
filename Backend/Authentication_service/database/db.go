package database

import (
	"fmt"
	"os"

	_ "github.com/lib/pq"
	"github.com/sriganeshres/WorkHub-Pro/Backend/models"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

type Database struct {
	DB *gorm.DB
}

func NewDatabase() *Database {
	db := &gorm.DB{}
	return &Database{DB: db}
}

func (db *Database) Init() error {
	DB, err := gorm.Open(postgres.Open(os.Getenv("DB_URL")), &gorm.Config{})
	if err != nil {
		return err
	}
	db.DB = DB
	fmt.Println("Database initialized")
	return nil
}

func (db *Database) GetUserByEmail(email string) (*models.UserData, error) {
	var user models.UserData
	err := db.DB.Where("email =?", email).First(&user).Error
	if err != nil {
		return nil, err
	}
	return &user, nil
}

func (db *Database) Migrate() error {
	err := db.DB.AutoMigrate(&models.WorkHub{}, &models.UserData{})
	if err != nil {
		return err
	}
	return nil
}

func (db *Database) CreateUser(userData *models.UserData) error {
	err := db.DB.Create(&userData).Error
	if err != nil {
		return err
	}
	return nil
}
