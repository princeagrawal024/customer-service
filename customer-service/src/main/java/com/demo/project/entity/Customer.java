package com.demo.project.entity;



import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.demo.project.converter.DateEncryptDecryptConverter;
import com.demo.project.converter.StringEncryptDecryptConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Convert(converter = StringEncryptDecryptConverter.class)
    @Column(name="first_name")
    private String firstName;

    @Convert(converter = StringEncryptDecryptConverter.class)
    @Column(name="last_name")
    private String lastName;

    @Convert(converter = StringEncryptDecryptConverter.class)
    @Column(name = "address")
    private String address;

    @Convert(converter = DateEncryptDecryptConverter.class)
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Convert(converter = StringEncryptDecryptConverter.class)
    @Column(name = "ssn", unique=true)
    private String ssn;

}
