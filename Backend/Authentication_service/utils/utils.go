package utils

import (
	"crypto/sha256"
	"encoding/hex"
	"errors"
	"fmt"
	"os"

	"gopkg.in/gomail.v2"
)

func HashString(s string) string {
	hasher := sha256.New()
	hasher.Write([]byte(s))
	return hex.EncodeToString(hasher.Sum(nil))
}
var loginAttempts = make(map[string]int)
func VerifyPassword(email,password, hashedPassword string) (bool, error,bool) {
	if len(password) == 0 {
		return false, errors.New("zero-length password"),false
	}
	if len(hashedPassword) == 0 {
		return false, errors.New("no user with the given email address"),false
	}
	fmt.Println(loginAttempts)
	    // Check if the email has reached the maximum number of attempts
	if attempts, exists := loginAttempts[email]; exists && attempts >= 5 {
		fmt.Println("Account locked due to too many failed login attempts")
		err:=fmt.Errorf("account locked due to too many failed login attempts")
		fmt.Println(err.Error()) 
		return false,nil,true
	}
	if HashString(password) != hashedPassword {
		fmt.Println("Wrong password")
		loginAttempts[email]++
		return false, nil,false
	}
	if HashString(password) == hashedPassword {
		delete(loginAttempts, email)
		return true, nil,false
	}
	return HashString(password) == hashedPassword, nil,false
}

func SendEmail(to string, code int) error {
	// Load SMTP configuration from environment variables
	smtpHost := "smtp.gmail.com"
	smtpPort := 587
	smtpUsername := os.Getenv("Gmail")
	smtpPassword := os.Getenv("password")

	if smtpHost == "" || smtpUsername == "" || smtpPassword == "" {
		return fmt.Errorf("SMTP configuration not set")
	}

	// Initialize SMTP dialer
	dialer := gomail.NewDialer(smtpHost, smtpPort, smtpUsername, smtpPassword)

	// Compose email message
	msg := gomail.NewMessage()
	msg.SetHeader("From", "rahulreddypurmani123@gmail.com")
	msg.SetHeader("To", to)
	msg.SetHeader("Subject", "Your Privacy Code")
	msg.SetBody("text/plain", fmt.Sprintf("Your privacy code is: %d", code))

	// Send email
	err := dialer.DialAndSend(msg)
	if err != nil {
		return err
	}
	fmt.Println("Email sent to", to)
	return nil
}
