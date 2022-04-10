package linh.model;

import java.util.Vector;

public class Product {
	private String Id;
	private String nameProduct;
	private String number;
	private String price;
	private String exp;
	private String mfg;
	private GroupProducts groupPr;

	
	public Product() {
		super();
	}
	
	public Product(String id, String nameProduct, String number, String price) {
		super();
		Id = id;
		this.nameProduct = nameProduct;
		this.number = number;
		this.price = price;
	}

	public Product(String Id, String nameProduct, String number, String price, String mfg, String exp) {
		super();
		this.Id = Id;
		this.nameProduct = nameProduct;
		this.number = number;
		this.price = price;
		this.exp = exp;
		this.mfg = mfg;
	}

	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getNameProduct() {
		return nameProduct;
	}
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String string) {
		this.number = string;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	public String getMfg() {
		return mfg;
	}
	public void setMfg(String mfg) {
		this.mfg = mfg;
	}

	public GroupProducts getGroupPr() {
		return groupPr;
	}

	public void setGroupPr(GroupProducts groupPr) {
		this.groupPr = groupPr;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nameProduct;
	}
	
	


}
