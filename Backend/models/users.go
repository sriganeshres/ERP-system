package models

import "github.com/jinzhu/gorm"

type RoleField string

const (
	User        RoleField = "user"
	Admin       RoleField = "admin"
	ProjectLead RoleField = "project_lead"
)

type UserData struct {
	gorm.Model
	Username  string    `gorm:"unique_index;not null" json:"username"`
	Password  string    `json:"password"`
	Email     string    `gorm:"unique_index;not null" json:"email"`
	Role      RoleField `json:"role" gorm:"default:'user'"`
	WorkhubID uint      `gorm:"foreignKey:WorkHubID" json:"id"`
	ProjectID uint      `gorm:"foreignKey:ProjectID" json:"project_id"`
}

type LoginUser struct {
	Email    string `json:"email"`
	Password string `json:"password"`
}
