package athletics.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="items")
public class Item extends NamedEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name="total_quantity")
	private Integer quantity;
	 
	@Column(name="item_price")
	private String price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cart_id", referencedColumnName="id")
	private ShoppingCart cart;
	
	@Enumerated(EnumType.STRING)
	@Column(name="sex")
	private Sex sex;
	
	@Enumerated(EnumType.STRING)
	@Column(name="clothes_type")
	private Type type;
	
	@Column(name="item_size")
	private String size;
	
	@Column(name="description")
	private String description;
	
	@Column(name="vendor_code")
	private String vendorCode;

	public Item(){}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public ShoppingCart getCart() {
		return cart;
	}

	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
