import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Steps } from '../models/step';

@Injectable({
  providedIn: 'root'
})
export class StepService {
  private url = 'http://localhost:8080/step/';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  constructor(private http: HttpClient) { }

  getSteps(recipeId: number): Observable<any> {
    return this.http.get<any>(this.url + recipeId);
  }

  addStep(step: Steps, recipeId: number): Observable<Steps> {
    return this.http.post<Steps>(this.url + recipeId, step, this.httpOptions);
  }

  saveStep(step: Steps, recipeId: number): Observable<Steps> {
    return this.http.put<Steps>(this.url + recipeId, step, this.httpOptions);
  }

  deleteStep(stepId: number): Observable<Steps> {
    return this.http.delete<Steps>(this.url + stepId, this.httpOptions);
  }
}
