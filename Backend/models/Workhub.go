package models

import "gorm.io/gorm"

type WorkHub struct {
	gorm.Model
	UserDataID  uint   `gorm:"unique;not null" json:"-"`
	Name        string `gorm:"unique_index;not null" json:"name"`
	Description string `gorm:"not null" json:"description"`
	Domain      string `gorm:"not null" json:"domain"`
	PrivacyKey  int    `gorm:"not null" json:"privacy_key"`
}


