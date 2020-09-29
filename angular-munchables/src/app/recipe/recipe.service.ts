import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Recipe } from '../models/recipe';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {
  private url = 'http://localhost:8080/recipe/';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  constructor(private http: HttpClient) { }

  getRecipe(recipeId: number): Observable<any> {
    return this.http.get<any>(this.url + recipeId);
  }

  getAllRecipes(): Observable<any> {
    return this.http.get<any>(this.url);
  }

  addRecipe(recipe: Recipe): Observable<Recipe> {
    return this.http.post<Recipe>(this.url, recipe, this.httpOptions);
  }

  saveRecipe(recipe: Recipe): Observable<Recipe> {
    return this.http.put<Recipe>(this.url, recipe, this.httpOptions);
  }

  deleteRecipe(recipeId: number): Observable<Recipe> {
    return this.http.delete<Recipe>(this.url + recipeId, this.httpOptions);
  }
}
