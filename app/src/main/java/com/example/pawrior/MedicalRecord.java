package com.example.pawrior;

public class MedicalRecord {
    String doctorName, clinicName, diagnosis, symptoms, medicines, nextConsultation, visitDate;

    public MedicalRecord(String doctorName, String clinicName, String diagnosis, String symptoms, String medicines, String nextConsultation, String visitDate) {
        this.doctorName = doctorName;
        this.clinicName = clinicName;
        this.diagnosis = diagnosis;
        this.symptoms = symptoms;
        this.medicines = medicines;
        this.nextConsultation = nextConsultation;
        this.visitDate = visitDate;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public String getNextConsultation() {
        return nextConsultation;
    }

    public void setNextConsultation(String nextConsultation) {
        this.nextConsultation = nextConsultation;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }
}
