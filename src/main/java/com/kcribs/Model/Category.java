package com.kcribs.Model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "category_code", nullable = false, unique = true)
	private String categoryCode;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private Set<OfferedServices> offeredServices;

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<OfferedServices> getOfferedServices() {
		return offeredServices;
	}

	public void setOfferedServices(Set<OfferedServices> offeredServices) {
		this.offeredServices = offeredServices;
	}

}
