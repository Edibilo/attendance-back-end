package iki.attendance.management.attendance.entity;

import iki.attendance.management.attendance.enumerator.GenderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private GenderStatus genderStatus;
    private String phoneNumber;
    private String region;
    private String district;
    private String ward;
    private String parentName;
    @PrePersist
    void PrePersist(){
        if(genderStatus==null){
            genderStatus=GenderStatus.MALE;
        }
    }

}
