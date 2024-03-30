package models

import "gorm.io/gorm"

type WorkHub struct {
	gorm.Model
	Name        string    `gorm:"unique_index;not null" json:"name"`
	Description string    `gorm:"not null" json:"description"`
	Domain      string    `gorm:"not null" json:"domain"`
	Admin       UserData  `gorm:"not null" json:"admin"`
	PrivacyKey  uint      `gorm:"not null" json:"privacy_key"`
	Projects    []Project `gorm:"not null" json:"projects"`
}
