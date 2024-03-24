package database

import (
	"fmt"

	_ "github.com/lib/pq"
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
	DB, err := gorm.Open(postgres.Open("postgres://root:password@localhost:5432/users?sslmode=disable"), &gorm.Config{})
	if err != nil {
		return err
	}
	db.DB = DB
	fmt.Println("Database initialized")
	return nil
}

func (db *Database) GetUserByEmail(email string) (*UserData, error) {
	var user UserData
	err := db.DB.Where("email =?", email).First(&user).Error
	if err != nil {
		return nil, err
	}
	return &user, nil
}

func (db *Database) Migrate() error {
	err := db.DB.AutoMigrate(&UserData{})
	if err != nil {
		return err
	}
	return nil
}
