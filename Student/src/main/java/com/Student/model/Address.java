package com.Student.model;



import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int AddressId;
	public String street;
	public String state;
	
	@OneToOne(mappedBy ="address", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "roll_id")
	@JsonManagedReference
	private StudentDetails studentDetails;

}
