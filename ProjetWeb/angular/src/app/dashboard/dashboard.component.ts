import { Component, OnInit } from '@angular/core';
import { Pizza } from '../pizza';
import {PizzaService} from '../pizza.service';
import { CommandeService } from '../commande.service';
import {Commande} from '../commande';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {
  pizzas: Pizza[] = [];
  commandes: Commande[] =[];
  commande: Pizza[] = [];
  var: string = "";
  compteur = 0;
  prixTotal = 0;
  constructor(private pizzaService: PizzaService, private commandeService: CommandeService) { }

  ngOnInit() {
    this.getPizzas();
  }

   getPizzas(): void {
    this.pizzaService.getPizzas()
      .subscribe(pizzas => this.pizzas = pizzas);
  }

    getCommandes(): void {
    this.commandeService.getCommandes()
      .subscribe(commandes => this.commandes = commandes);
  }
  add(pizza: Pizza){
   this.commande.push(pizza);
   this.compteur = this.compteur + 1;
   this.prixTotal = this.prixTotal + pizza.price;
  }
  commander(name: string){
    for (let i = 0; i < this.compteur; i++) {
      this.var = this.commande.pop().name;
      this.commandes.push({name,"pizza":this.var} as Commande);
    }
    this.commandeService.addMultipleCommande(this.commandes).subscribe();
    this.prixTotal = 0;
  }
  

}