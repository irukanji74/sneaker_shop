package athletics.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="shopping_carts")
public class ShoppingCart extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@OneToMany
	private Set<Item> items;

}
