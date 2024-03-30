package utils

import (
	"crypto/sha256"
	"encoding/hex"
	"errors"
)

func HashString(s string) string {
	hasher := sha256.New()
	hasher.Write([]byte(s))
	return hex.EncodeToString(hasher.Sum(nil))
}

func VerifyPassword(password, hashedPassword string) (bool, error) {
	if len(password) == 0 {
		return false, errors.New("zero-length password")
	}
	if len(hashedPassword) == 0 {
		return false, errors.New("no user with the given email address")
	}
	return HashString(password) == hashedPassword, nil
}
