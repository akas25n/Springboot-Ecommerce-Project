package com.lot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="slider_images")
public class SliderImages {

	@Id
	@NotNull
	@Column(name="id", length= 20)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name= "image1", columnDefinition = "LONGTEXT", nullable=true)
	private String image1;
	
	@Column(name= "image2", columnDefinition = "LONGTEXT", nullable=true)
	private String image2;
	
	@Column(name= "image3", columnDefinition = "LONGTEXT", nullable=true)
	private String image3;

	public SliderImages() {
		
	}

	public SliderImages(int id, String image1, String image2, String image3) {
	
		this.id = id;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}
	
	
}
