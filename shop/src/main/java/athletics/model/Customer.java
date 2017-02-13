package athletics.model;

import java.util.Date;
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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;


@Entity
@Table(name="customers", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class Customer extends NamedEntity {

	private static final long serialVersionUID = 1L;
	
	    @Column(name = "email", nullable = false, unique = true)
	    @Email
	    @NotEmpty
	    @SafeHtml
	    private String email;
	
	    @Column(name = "password", nullable = false)
	    @NotEmpty
	    @Length(min = 5)
	    @SafeHtml
	    private String password;//указать в логин странице, что не может быть менее 5 символов
	    
	    @Column(name = "enabled", nullable = false)
	    private boolean enabled = true;// требуется для аутентификации springSecurity
	    
	    @Column(name = "registered")//columnDefinition="timestamp default now()"
	    private Date registered = new Date();
	    
	    @Enumerated(EnumType.STRING)
	    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
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
	    //@NotEmpty
	    @Digits(fraction = 0, integer = 10)
	    private String telephone;
	    
	    @Column(name="cart")
	     //optional-создавать customer ТОЛЬКО с уже созданным cart 
	    @OneToOne(cascade = CascadeType.ALL, optional=false, orphanRemoval=true, fetch = FetchType.EAGER)
	    private ShoppingCart cart;
	 

		public Customer() {
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
			this.roles = roles;
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
	    
	    
}
