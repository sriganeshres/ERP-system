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

func (db *Database) Migrate() error {
	err := db.DB.AutoMigrate(&models.WorkHub{})
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

func (db *Database) FindWorkHub(code int) (*models.WorkHub, error) {
	var workhub models.WorkHub
	err := db.DB.Where("privacy_key = ?", code).First(&workhub).Error
	if err != nil {
		return nil, err
	}
	return &workhub, nil
}
