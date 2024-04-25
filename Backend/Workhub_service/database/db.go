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
	err := db.DB.AutoMigrate(&models.WorkHub{}, &models.Project{}, &models.UserData{}, &models.Task{})
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

func (db *Database) DeleteWorkHub(code int) error {
	var workhub models.WorkHub
	err := db.DB.Where("privacy_key = ?", code).First(&workhub).Error
	if err != nil {
		return err
	}
	err = db.DB.Delete(&workhub).Error
	if err != nil {
		return err
	}
	return nil
}

func (db *Database) CreateProject(Project *models.Project) error {
	err := db.DB.Create(&Project).Error
	if err != nil {
		return err
	}
	return nil
}
func (db *Database) UpdateWorkhub(id uint, User models.UserData) error {
	var workhub models.WorkHub
	err := db.DB.Where("id =?", id).First(&workhub).Error
	if err != nil {
		return err
	}
	workhub.Users = append(workhub.Users, User)
	err = db.DB.Save(&workhub).Error
	if err != nil {
		return err
	}
	return nil
}

func (db *Database) FindProject(code int) (*models.Project, error) {
	var Project models.Project
	err := db.DB.Where("project_id = ?", code).First(&Project).Error
	if err != nil {
		fmt.Println("Project not Found in the finproject")
		return nil, err
	}
	return &Project, nil
}

func (db *Database) DeleteProject(code int) error {
	var Project models.Project
	err := db.DB.Where("project_id = ?", code).First(&Project).Error
	if err != nil {
		fmt.Println("Project not Found")
		return err
	}
	fmt.Println("Project Found", Project)
	// db.DB.Delete(&Project).
	err1 := db.DB.Delete(&Project).Error
	if err1 != nil {
		fmt.Println("Error in deletion from DB")
		return err1
	}
	return nil
}

func (db *Database) GetProjectsForWorkhub(workhub_id int) ([]*models.Project, error) {
	var projects []*models.Project
	err := db.DB.Where("workhub_id = ?", workhub_id).Find(&projects).Error
	if err != nil {
		return nil, err
	}
	return projects, nil
}

func (db *Database) CreateTask(task *models.Task) error {
	err := db.DB.Create(&task).Error
	if err != nil {
		return err
	}
	return nil
}

func (db *Database) UpdateTask(code int, status models.StatusField) error {
	var task models.Task
	err := db.DB.Where("id =?", code).First(&task).Error
	if err != nil {
		return err
	}
	task.Status = status
	err = db.DB.Save(&task).Error
	if err != nil {
		return err
	}
	return nil
}

func (db *Database) GetAllTasksByProjectID(ProjectID int, tasks *[]models.Task) error {
	err := db.DB.Where("project_id =?", ProjectID).Find(tasks).Error
	if err != nil {
		return err
	}
	return nil
}

func (db *Database) GetWorkhubbyID(workhub_id int) (*models.WorkHub, error) {
	var workhub *models.WorkHub
	err := db.DB.Where("ID = ?", workhub_id).Find(&workhub).Error
	if err != nil {
		return nil, err
	}
	return workhub, nil
}

func (db *Database) GetAllTasksByWorkHubID(WorkHubID int, tasks *[]models.Task) error {
	err := db.DB.Where("work_hub_id =?", WorkHubID).Find(tasks).Error
	if err != nil {
		return err
	}
	return nil
}

func (db *Database) GetAllTasksByUserID(userID int, tasks *[]models.Task) error {
	err := db.DB.Where("assigned_to =?", userID).Find(tasks).Error
	if err != nil {
		return err
	}
	return nil
}

func (db *Database) DeleteTask(id int) error {
	var task models.Task
	err := db.DB.Where("ID = ?", id).First(&task).Error
	if err != nil {
		return err
	}
	err = db.DB.Delete(&task).Error
	if err != nil {
		return err
	}
	return nil
}
