package com.efrei.JPAExample;

import java.util.ArrayList;
import java.util.Iterator;
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
public class CommandeService {

	private List<Commande> commandes = new ArrayList<Commande>();
	private CommandeRepository repository;
	
	@Autowired
	public CommandeService(CommandeRepository repository) {
		super();
		this.repository = repository;
		commandes = (List<com.efrei.JPAExample.Commande>) repository.findAll();
	}
	
	
	
	@RequestMapping(value = "/commande", method = RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void addCommande(@RequestBody Commande commande) throws Exception {
		System.out.println("POST Commande TENTATIVE");
		repository.save(commande);
		commandes.add(commande);
		System.out.println(commande.toString());
		
	}
	
	@RequestMapping(value = "/commandes", method = RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void addCommandes(@RequestBody List<Commande> commande) throws Exception {
		System.out.println("POST Commande TENTATIVE");
		for(Commande co: commande) {
			repository.save(co);
			commandes.add(co);
			System.out.println(commande.toString());
		
		}
	}
	
	
	@RequestMapping(value = "/commande/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Commande> Commande(@PathVariable(value = "name") String name){
		List<Commande> temporaire =new ArrayList<Commande>();
		for(Commande commande: commandes){
			if(commande.getName().equals(name)){
				temporaire = repository.findByName(name);
			}
		}
		return temporaire;
	}
	
	@RequestMapping(value="/commande", method=RequestMethod.GET) 
	@ResponseStatus(HttpStatus.OK) 
	public List<Commande> getListOfCommande(String name,String pizzas ,Model model){
		model.addAttribute("name",name);
		model.addAttribute("pizzas",pizzas);
		System.out.println("GET Commande TENTATIVE");
		commandes = (List<com.efrei.JPAExample.Commande>) repository.findAll();
		return commandes;
	}
	@RequestMapping(value = "/commandes/{name}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void DeleteCommande(@PathVariable("name") String name)throws Exception{
		List<Commande> temporaire = new ArrayList<Commande>();
		for(Commande commande: commandes){
			if(commande.getName().equals(name)){
				repository.delete(commande);
			}else {
				temporaire.add(commande);
			}
		}
		commandes = temporaire;
		
		
	}
	
	
}
