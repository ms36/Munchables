import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IngredientsService {
  private url = 'http://localhost:8080/ingredient/';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  constructor(private http: HttpClient) { }

  getIngredients(recipeId: number): Observable<any> {
    return this.http.get<any>(this.url + recipeId);
  }
}
