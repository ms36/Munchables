import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StepService {
  private stepUrl = 'http://localhost:8080/step/';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  constructor(private http: HttpClient) { }

  getSteps(recipeId: number): Observable<any> {
    return this.http.get<any>(this.stepUrl + recipeId);
  }
}
