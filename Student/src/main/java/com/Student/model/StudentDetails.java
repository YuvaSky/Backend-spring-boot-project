package com.Student.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@Getter
@Entity
public class StudentDetails {
	
	@Id
	private int rollId;
	private String subject;
	private String section;
	private String imageName;
	private Date addedDate;
	@OneToOne
	@JsonBackReference
	@JoinColumn(name = "id")
	private Student student;
	@OneToOne
	@JsonBackReference
	@JoinColumn(name = "Addressid")
	private Address address;
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name ="sid")
	@JsonBackReference
	private Streamm streamm;

     
}
