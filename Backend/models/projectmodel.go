package models

import "gorm.io/gorm"

type Project struct {
	gorm.Model
	Name        string     `gorm:"unique_index;not null" json:"name"`
	Description string     `json:"description"`
	Members     []UserData `json:"members"`
	ProjectLead UserData   `json:"project_leader"`
}
