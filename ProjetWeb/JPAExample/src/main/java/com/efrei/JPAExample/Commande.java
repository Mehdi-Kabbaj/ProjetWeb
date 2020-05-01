package com.efrei.JPAExample;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Commande {
	long id;
	String name;
	String pizza;
	
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
		return "Commande [Commande=" + pizza + "]";
	}
	
	public String getPizza() {
		return pizza;
	}
	public void setPizza(String pizza) {
		this.pizza = pizza;
	}
	public Commande(String name,String commande) {
		super();
		this.name = name;
		this.pizza = commande;
	}
	public Commande() {
		super();
		// TODO Auto-generated constructor stub
	}
}
