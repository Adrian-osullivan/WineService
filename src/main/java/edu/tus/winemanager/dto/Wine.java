package edu.tus.winemanager.dto;

// import jdk.vm.ci.meta.Local;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDate;


@Entity
public class Wine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotBlank(message = "Name is required")
	private String name;
	@Positive(message = "Year must be positive")
	private int year;
	private String grapes;
	private String country;
	private String region;
	@Enumerated(EnumType.STRING)
	private Rating rating;
	private LocalDate expiryDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getGrapes() {
		return grapes;
	}

	public void setGrapes(String grapes) {
		this.grapes = grapes;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Wine() {

	}


	public Wine(Long id, String name, String grapes, String country, String region, Rating rating, LocalDate expiryDate) {
		this.id = id;
		this.name = name;
		this.grapes = grapes;
		this.country = country;
		this.region = region;
		this.rating = rating;
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return "Wine [id=" + id + ", name=" + name + ", grapes=" + grapes + ", country=" + country + ", region="
				+ region + ", rating=" + rating + ", expiryDate=" + expiryDate.toString() + "]";
	}
}


