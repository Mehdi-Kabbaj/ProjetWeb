import { Injectable } from '@angular/core';
import { MessageService } from './message.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import{Pizza} from './pizza';


@Injectable({
  providedIn: 'root'
})
export class PizzaService {

   private pizzaUrl = 'http://localhost:8080/pizza';

   httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json',
     'Access-Control-Allow-Origin':'*',
     'Access-Control-Allow-Methods': 'GET, POST, DELETE, PUT, OPTIONS',
     'Access-Control-Allow-Headers': 'Origin, Content-Type, Accept, Authorization, X-Request-With',
     'Access-Control-Allow-Credentials': 'true'
   })
  };

 constructor( private http: HttpClient, private messageService: MessageService) { }

 getPizzas(): Observable<Pizza[]> {
    return this.http.get<Pizza[]>(this.pizzaUrl)
      .pipe(
        tap(_ => this.log('fetched Pizza')),
        catchError(this.handleError<Pizza[]>('getPizzas', []))
      );
  }



  getPizza(id: number): Observable<Pizza> {
    const url = `${this.pizzaUrl}/${id}`;
    return this.http.get<Pizza>(url).pipe(
      tap(_ => this.log(`fetched Pizza id=${id}`)),
      catchError(this.handleError<Pizza>(`getPizza id=${id}`))
    );
  }

 addPizza(pizza: Pizza): Observable<Pizza> {
    return this.http.post<Pizza>(this.pizzaUrl, pizza, this.httpOptions);
  }

 deletePizza(pizza: Pizza | number): Observable<Pizza> {
    const id = typeof pizza === 'number' ? pizza : pizza.id;
    const url = `${this.pizzaUrl}/${id}`;

    return this.http.delete<Pizza>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted pizza id=${id}`)),
      catchError(this.handleError<Pizza>('deletePizza'))
    );
  }






 /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
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
    this.messageService.add(`Pizza: ${message}`);
  }

  
 
}
