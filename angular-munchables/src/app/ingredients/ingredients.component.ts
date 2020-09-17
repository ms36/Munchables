import { IngredientsService } from './ingredients.service';
import { Component, OnInit, Input } from '@angular/core';
import { Ingredient } from '../models/ingredient';

@Component({
  selector: 'app-ingredients',
  templateUrl: './ingredients.component.html',
  styleUrls: ['./ingredients.component.css']
})
export class IngredientsComponent implements OnInit {
  @Input() pageNumber: number;

  ingredients: Ingredient[] = [];
  constructor(private ingredientsService: IngredientsService) { }

  ngOnInit() {
    this.getSteps(this.pageNumber);
  }

  getSteps(recipeId: number): void {
    this.ingredientsService.getIngredients(recipeId).subscribe(step => {
      this.ingredients = step;
      console.log('ingredients', this.ingredients);
    });
  }

}
