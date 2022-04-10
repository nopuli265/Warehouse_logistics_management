package linh.model;

import java.util.Vector;

public class GroupProducts {
	private String IdGroup;
	private String nameGroup;
	private Vector<Product> products;
	
	public GroupProducts() {
		super();
	}
	
	public GroupProducts(String idGroup, String nameGroup) {
		super();
		IdGroup = idGroup;
		this.nameGroup = nameGroup;
		this.products= new Vector<Product>();//important
	}
	
	public String getIdGroup() {
		return IdGroup;
	}
	public void setIdGroup(String idGroup) {
		IdGroup = idGroup;
	}
	public String getNameGroup() {
		return nameGroup;
	}
	public void setNameGroup(String nameGroup) {
		this.nameGroup = nameGroup;
	}
	public Vector<Product> getProducts() {
		return products;
	}
	public void setProducts(Vector<Product> products) {
		this.products = products;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nameGroup;
	}
}
