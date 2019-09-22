package com.example.pawrior;

public class Vaccines {
    String vaccineName, vaccinationDate, doctorName, immunityAgainst, dogAge;

    public Vaccines(String vaccineName, String vaccinationDate, String doctorName, String immunityAgainst, String dogAge) {
        this.vaccineName = vaccineName;
        this.vaccinationDate = vaccinationDate;
        this.doctorName = doctorName;
        this.immunityAgainst = immunityAgainst;
        this.dogAge = dogAge;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(String vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getImmunityAgainst() {
        return immunityAgainst;
    }

    public void setImmunityAgainst(String immunityAgainst) {
        this.immunityAgainst = immunityAgainst;
    }

    public String getDogAge() {
        return dogAge;
    }

    public void setDogAge(String dogAge) {
        this.dogAge = dogAge;
    }
}
