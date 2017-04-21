package athletics.model;

import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.CollectionUtils;

import athletics.validators.ValidEmail;
import athletics.validators.ValidPhone;

@Entity
// @Table(name="customers", uniqueConstraints = {@UniqueConstraint(columnNames =
// "email", name = "customers_unique_email_idx")})
@Table(name = "customers")
public class Customer extends NamedEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "email")
	@ValidEmail
	// @NotEmpty
	// @SafeHtml //Вызывает сбой !!!!!
	private String email;

	@Column(name = "password", nullable = false)
	// @NotEmpty
	@Length(min = 5)
	// @SafeHtml
	private String password;// указать в логин странице, что не может быть менее 5 символов
	
	//если работаю через token - false сначала, потом после пдтв. --  true
	@Column(name = "enabled", nullable = false)
	private boolean enabled = true;// требуется для аутентификации springSecurity

	@Column(name = "registered", columnDefinition = "timestamp default now()")
	// @Temporal(value = null)
	private Date registered = new Date();

	@Enumerated(EnumType.STRING)
	@CollectionTable(name = "customer_authorities", joinColumns = @JoinColumn(name = "customer_id"))
	@Column(name = "role")
	@ElementCollection(fetch = FetchType.EAGER)
	@BatchSize(size = 200)
	private Set<Role> roles;

	@Column(name = "address")
	// @NotEmpty
	private String address;

	@Column(name = "lastname")
	// @NotEmpty
	private String lastName;

	@Column(name = "city")
	// @NotEmpty
	private String city;

	@Column(name = "telephone")
	// @NotEmpty
	/* @Length(max=10, min=10, message="Только 10 цифр - без +")
	   @NumberFormat(style= Style.NUMBER)*/
	@ValidPhone
	private String telephone;

	@Column(name = "zip")
	@Length(min = 5, max = 5, message = "{zip.length}")
	private String zipCode;

	// optional=false = создавать customer ТОЛЬКО с уже созданным cart
	@OneToOne(cascade = CascadeType.ALL, optional = true, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "cart_id", nullable = false)
	// @PrimaryKeyJoinColumn - тогда id обеих сущностей будут идентичны
	private ShoppingCart cart;

	public Customer() {
	}

	/*public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}*/

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getRegistered() {
		return registered;
	}

	public void setRegistered(Date registered) {
		this.registered = registered;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public ShoppingCart getCart() {
		return cart;
	}

	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "Customer [email=" + email + ", password=" + password + ", enabled=" + enabled + ", registered="
				+ registered + ", roles=" + roles + ", address=" + address + ", lastName=" + lastName + ", city=" + city
				+ ", telephone=" + telephone + ", zipCode=" + zipCode + ", cart=" + cart + ", id=" + id + "]";
	}

}
