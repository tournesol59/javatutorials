package com.baeldung.login;
import org.springframework.stereotype.Component;

import com.baeldung.model.Customer;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.ForeignKey;

//@Component
@Entity
@Table( name="tbllogins" )
public class Login {

	public Login(int id, String name) {
	     super();
             this.loginId = id;
             this.name = name;
	}
        @Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	// @GenericGenerator( name="increment", generator="increment")
        private int loginId;
	public int getId() {
             return this.loginId;
	}
	
	public void setId(int id) {
             this.loginId = id;
	}

        @Column(name = "login_name" )  
        private String name;
	public String getName() {
             return this.name;
	}

	public void setName(String sname) {
             this.name = sname;
	}

	@ManyToOne
	@JoinColumn( name="customer_id", foreignKey=@ForeignKey(name="CUSTOMER_ID_FK"))
	private Customer customer;
	public Customer getCustomer() {
             return this.customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
