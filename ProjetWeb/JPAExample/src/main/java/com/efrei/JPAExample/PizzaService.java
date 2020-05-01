package com.efrei.JPAExample;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class PizzaService {

	private List<Pizza> pizzas = new ArrayList<Pizza>();
	private PizzaRepository repository;
	public PizzaService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public PizzaService(PizzaRepository repository) {
		super();
		this.repository = repository;
		pizzas = (List<com.efrei.JPAExample.Pizza>) repository.findAll();
	}
	
	
	@RequestMapping(value = "/pizza", method = RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void addPizza(@RequestBody Pizza pizza) throws Exception {
		System.out.println("POST TENTATIVE");
		repository.save(pizza);
		pizzas.add(pizza);
		System.out.println(pizza.toString());
		
	}
	
	
	@RequestMapping(value = "/pizza/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public Pizza Pizza(@PathVariable(value = "name") String name){
		for(Pizza pizza: pizzas){
			if(pizza.getName().equals(name)){
				return pizza;
			}
		}
		return null;
	}
	@RequestMapping(value = "/pizza/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Pizza PizzabyId(@PathVariable(value = "id") int id){
		for(Pizza pizza: pizzas){
			if(pizza.getId() == id ){
				return pizza;
			}
		}
		return null;
	}
	
	
	
	
	@RequestMapping(value="/pizza", method=RequestMethod.GET) 
	@ResponseStatus(HttpStatus.OK) 
	public List<Pizza> getListOfPizza(String name,Model model){
		model.addAttribute("name",name);
		System.out.println("GET TENTATIVE");
		return pizzas;
	}
	
	
	/*@RequestMapping(value = "/pizza/{name}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void DeletePizza(@PathVariable("name") String name)throws Exception{
		List<Pizza> temporaire = new ArrayList<Pizza>();
		for(Pizza pizza: pizzas){
			if(pizza.getName().equals(name)){
				repository.delete(pizza);
			}else {
				temporaire.add(pizza);
			}
		}
		pizzas = temporaire;
		
		
	}*/
	@RequestMapping(value = "/pizza/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void DeleteByID(@PathVariable("id") int id)throws Exception{
		List<Pizza> temporaire = new ArrayList<Pizza>();
		for(Pizza pizza: pizzas){
			if(pizza.getId() == id){
				repository.delete(pizza);
			}else {
				temporaire.add(pizza);
			}
		}
		pizzas = temporaire;
		
		
	}
	
	
	
	
}
