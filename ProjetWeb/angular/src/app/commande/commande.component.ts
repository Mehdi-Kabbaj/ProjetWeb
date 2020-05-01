import { Component, OnInit } from '@angular/core';
import {Commande} from '../commande';
import {CommandeService} from '../commande.service';


@Component({
  selector: 'app-commande',
  templateUrl: './commande.component.html',
  styleUrls: ['./commande.component.css']
})
export class CommandeComponent implements OnInit {

   commandes: Commande[];
   products = [];

  constructor(private commandeService: CommandeService) { }

  ngOnInit(): void {
    this.getCommandes();

  }
  getCommandes(): void {
    this.commandeService.getCommandes()
    .subscribe(commandes => this.commandes = commandes);
  }

  add(commande : Commande[]): void {
    this.commandeService.addMultipleCommande(commande);
  }

  delete(name: string): void {
    this.commandeService.deleteCommande(name).subscribe();
  }

}
