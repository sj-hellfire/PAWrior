package com.example.pawrior.auth;

public class Dog {
    String dogFirstName, dogSecondName, dogAge, dogBreed, dogWeight;
    String ownerName, ownerPhone, ownerEmail;
    Boolean dogGender;

    public Dog(String dogFirstName, String dogSecondName, String dogAge, String dogBreed, String dogWeight, String ownerName, String ownerPhone, String ownerEmail, Boolean dogGender) {
        this.dogAge = dogAge;
        this.dogBreed = dogBreed;
        this.dogWeight = dogWeight;
        this.ownerName = ownerName;
        this.ownerPhone = ownerPhone;
        this.ownerEmail = ownerEmail;
        this.dogGender = dogGender;
        this.dogFirstName = dogFirstName;
        this.dogSecondName = dogSecondName;
    }

    public String getDogFirstName() {
        return dogFirstName;
    }

    public void setDogFirstName(String dogFirstName) {
        this.dogFirstName = dogFirstName;
    }

    public String getDogSecondName() {
        return dogSecondName;
    }

    public void setDogSecondName(String dogSecondName) {
        this.dogSecondName = dogSecondName;
    }

    public String getDogAge() {
        return dogAge;
    }

    public void setDogAge(String dogAge) {
        this.dogAge = dogAge;
    }

    public String getDogBreed() {
        return dogBreed;
    }

    public void setDogBreed(String dogBreed) {
        this.dogBreed = dogBreed;
    }

    public String getDogWeight() {
        return dogWeight;
    }

    public void setDogWeight(String dogWeight) {
        this.dogWeight = dogWeight;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public Boolean getDogGender() {
        return dogGender;
    }

    public void setDogGender(Boolean dogGender) {
        this.dogGender = dogGender;
    }
}
