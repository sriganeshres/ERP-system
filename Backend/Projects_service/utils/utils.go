package utils

import (
	"math/rand"
	"time"
)

// Define a map to store generated codes
var generatedCodes = make(map[int]bool)

// GenerateRandomCode generates a unique random code
func GenerateRandomCode() int {
	rand.Seed(time.Now().UnixNano())

	for {
		code := rand.Intn(9000) + 1000
		if _, exists := generatedCodes[code]; !exists {
			// If code is unique, store it and return
			generatedCodes[code] = true
			return code
		}
	}
}
