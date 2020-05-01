import { Component, OnInit } from '@angular/core';
import {Pizza} from '../pizza';
import {PizzaService} from '../pizza.service';



@Component({
  selector: 'app-pizza',
  templateUrl: './pizza.component.html',
  styleUrls: ['./pizza.component.css']
})
export class PizzaComponent implements OnInit {

  pizzas: Pizza[];
  products = [];

  constructor(private pizzaService: PizzaService) { }

  ngOnInit(): void {
     this.getPizzas();
  }

 getPizzas(): void {
    this.pizzaService.getPizzas()
    .subscribe(pizzas => this.pizzas = pizzas);
  }

add(name: string, price: number): void {
    name = name.trim();
    if (!name) { return; }
    this.pizzaService.addPizza({name, price} as Pizza)
      .subscribe(pizza => {
        this.pizzas.push(pizza);
      });
  }

  delete(pizza: Pizza): void {
    this.pizzas = this.pizzas.filter(h => h !== pizza);
    this.pizzaService.deletePizza(pizza).subscribe();
  }


}
