import { Injectable } from '@angular/core';
import { MessageService } from './message.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import {Commande} from './commande';


@Injectable({
  providedIn: 'root'
})
export class CommandeService {
   private commandeUrl = 'http://localhost:8080/commande';
   private commandesURL = 'http://localhost:8080/commandes';
   commandes: Commande[] = [];
   httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json',
     'Access-Control-Allow-Origin':'*',
     'Access-Control-Allow-Methods': 'GET, POST, DELETE, PUT, OPTIONS',
     'Access-Control-Allow-Headers': 'Origin, Content-Type, Accept, Authorization, X-Request-With',
     'Access-Control-Allow-Credentials': 'true'
   })
  };

  constructor(private http: HttpClient, private messageService: MessageService) { }

 getCommandes(): Observable<Commande[]> {
    return this.http.get<Commande[]>(this.commandeUrl)
      .pipe(
        tap(_ => this.log('fetched Commande')),
        catchError(this.handleError<Commande[]>('getCommandes', []))
      );
  }


  getCommande(name: string): Observable<Commande> {
    const url = `${this.commandeUrl}/${name}`;
    return this.http.get<Commande>(url).pipe(
      tap(_ => this.log(`fetched Commande name=${name}`)),
      catchError(this.handleError<Commande>(`getCommande name=${name}`))
    );
  }

 addCommande(commande: Commande){
    this.commandes.push(commande);
  }

  commander(){
    this.addMultipleCommande(this.commandes);
    this.commandes = [];
  }

  addMultipleCommande(commandes: Commande[]): Observable<Commande[]> {
    return this.http.post<Commande[]>(this.commandesURL, commandes, this.httpOptions);
  }

 deleteCommande(name: string | string): Observable<Commande[]> {
    const url = `${this.commandesURL}/${name}`;

    return this.http.delete<Commande[]>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted commande name=${name}`)),
      catchError(this.handleError<Commande[]>('deletecommande'))
    );
  }








    private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  } private log(message: string) {
    this.messageService.add(`Commande: ${message}`);
  }



}
