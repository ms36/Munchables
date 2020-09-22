import { Ingredients } from './../models/ingredients';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IngredientsService {
  private url = 'http://localhost:8080/ingredients/';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  constructor(private http: HttpClient) { }

  getIngredients(recipeId: number): Observable<any> {
    return this.http.get<any>(this.url + recipeId);
  }

  addIngredient(ingredient: Ingredients, recipeId: number): Observable<Ingredients> {
    return this.http.post<Ingredients>(this.url + recipeId, ingredient, this.httpOptions);
  }

  deleteIngredient(ingredientId: number): Observable<Ingredients> {
    return this.http.delete<Ingredients>(this.url + ingredientId, this.httpOptions);
  }
}
