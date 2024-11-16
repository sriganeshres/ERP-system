package models

import (
	"time"

	"github.com/jinzhu/gorm"
)

type StatusField string

const (
	InProgress StatusField = "inProgress"
	Completed  StatusField = "completed"
	NotStarted StatusField = "notStarted"
)

type Task struct {
    gorm.Model
    Name        string      `gorm:"unique_index;not null" json:"name"`
    Description string      `json:"description"`
    AssignedBy  int         `gorm:"foreignKey:UserID" json:"assigned_by"`
    AssignedTo  int         `gorm:"foreignKey:UserID;index" json:"assigned_to"`
    ProjectID   int         `gorm:"foreignKey:ProjectID;index" json:"project_key"`
    WorkHubID   int         `gorm:"foreignKey:WorkHubID;index" json:"work_hub_id"`
    Status      StatusField `gorm:"default:'notStarted'" json:"status"`
	ScheduledAt *time.Time  `gorm:"index" json:"scheduled_at"`
}


type ScheduledTask struct {
    gorm.Model
    TaskName string    `json:"task_name"`
    LastRun  time.Time `json:"last_run"`
    NextRun  time.Time `json:"next_run"`
    Status   string    `json:"status"`
}

type UpdateTask struct {
	ID          int         ` json:"taskID"`
	StatusField StatusField `json:"status"`
}
