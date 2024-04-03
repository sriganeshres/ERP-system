package database

import (
	"fmt"

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
	DB, err := gorm.Open(postgres.Open("host=localhost port=5432 user=root password=password dbname=SE sslmode=disable"), &gorm.Config{})
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
func (db *Database) CreateWorkhub(WorkHub *models.WorkHub) error {
	err := db.DB.Create(&WorkHub).Error
	if err != nil {
		return err
	}
	return nil
}
func (db *Database) FindWorkHub(code int) (models.WorkHub, error) {
	var workhub models.WorkHub
	err := db.DB.Where("code = ?", code).First(&workhub).Error
	if err != nil {
		return models.WorkHub{}, err
	}
	return workhub, nil
}
