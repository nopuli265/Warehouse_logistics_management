package linh.model;

import java.util.ArrayList;

public class Client {
private String nameClient;
private String idClient;
private String address;
private String phone;
private ArrayList<Product>pr;

public Client(String idClient,String nameClient,  String phone, String address) {
	super();
	this.nameClient = nameClient;
	this.idClient = idClient;
	this.address = address;
	this.phone = phone;
	this.pr = new ArrayList<Product>();
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}

public ArrayList<Product> getPr() {
	return pr;
}
public void setPr(ArrayList<Product>pr) {
	this.pr = pr;
}
public Client() {
	super();
}
public Client(String idClient, String nameClient, String phone) {
	super();
	this.nameClient = nameClient;
	this.idClient = idClient;
	this.phone = phone;
}
public String getNameClient() {
	return nameClient;
}
public void setNameClient(String nameClient) {
	this.nameClient = nameClient;
}
public String getIdClient() {
	return idClient;
}
public void setIdClient(String idClient) {
	this.idClient = idClient;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}

@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nameClient;
	}
}
