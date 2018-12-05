//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.trade.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Arrays;
import javax.persistence.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "manager")
public class Manager {

	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();


	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	private String name;
	private String password;
	private String[] roles;
	private @ManyToOne
	Domain domain;

	protected Manager() {
	}

	public Manager(String name, String password, Domain domain, String... roles) {
		this.name = name;
		this.setPassword(password);
		this.roles = roles;
		this.domain = domain;
	}
//	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@JsonIgnore
	@Column(name = "password")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = PASSWORD_ENCODER.encode(password);
	}

	@Column(name = "roles")
	public String[] getRoles() {
		return this.roles;
	}

	public void setRoles(final String[] roles) {
		this.roles = roles;
	}

}
