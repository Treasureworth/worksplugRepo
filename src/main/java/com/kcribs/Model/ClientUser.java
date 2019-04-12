package com.kcribs.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ClientUser {

		@Id
		@JsonIgnore
		@GeneratedValue(strategy = GenerationType.SEQUENCE)
		@Column(name = "client_id", nullable = false, unique = true)
		private long id;
		
		@Column
	    private String firstName;
	    
	    @Column
	    private String lastName;
	    
	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "user_code", unique = true, nullable = false, insertable = true, updatable = true)
	    private User user;

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}
}
