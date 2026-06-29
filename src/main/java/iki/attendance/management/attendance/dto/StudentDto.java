package iki.attendance.management.attendance.dto;

import iki.attendance.management.attendance.enumerator.GenderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class StudentDto {
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String middleName;
    @NotBlank
    private String lastName;
    private LocalDate birthDate;
    @NotNull
    private GenderStatus genderStatus;
    private String phoneNumber;
    private String region;
    private String district;
    private String ward;
    private String parentName;
    @NotNull
    private Long classId;

    public StudentDto(Long id, String firstName, String middleName, String lastName,
                      LocalDate birthDate, GenderStatus genderStatus, String phoneNumber,
                      String region, String district, String ward, String parentName, Long classId) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.genderStatus = genderStatus;
        this.phoneNumber = phoneNumber;
        this.region = region;
        this.district = district;
        this.ward = ward;
        this.parentName = parentName;
        this.classId = classId;
    }

    public StudentDto(){

    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName.toUpperCase();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName.toUpperCase();
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName.toUpperCase();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public GenderStatus getGenderStatus() {
        return genderStatus;
    }

    public void setGenderStatus(GenderStatus genderStatus) {
        this.genderStatus = genderStatus;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward.toUpperCase();
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getParentName() {
        return parentName.toUpperCase();
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
