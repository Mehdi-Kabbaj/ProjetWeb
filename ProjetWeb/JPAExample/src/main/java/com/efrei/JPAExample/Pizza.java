package com.efrei.JPAExample;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pizza {
	long id;
	String name;
	int price;
	
	public Pizza() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pizza(String name,int price) {
		super();
		this.name = name;
		this.price = price;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
		
	@Override
	public String toString() {
		return "Pizza [name=" + name +" "+price+" $" + "]";
	}
}
