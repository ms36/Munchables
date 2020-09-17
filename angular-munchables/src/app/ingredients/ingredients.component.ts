import { IngredientsService } from './ingredients.service';
import { Component, OnInit, Input } from '@angular/core';
import { Ingredients } from '../models/ingredients';

@Component({
  selector: 'app-ingredients',
  templateUrl: './ingredients.component.html',
  styleUrls: ['./ingredients.component.css']
})
export class IngredientsComponent implements OnInit {
  @Input() pageNumber: number;

  ingredients: Ingredients[] = [];
  constructor(private ingredientsService: IngredientsService) { }

  ngOnInit() {
    this.getIngredients(this.pageNumber);
  }

  getIngredients(recipeId: number): void {
    this.ingredientsService.getIngredients(recipeId).subscribe(ingredient => {
      this.ingredients = ingredient;
      console.log('ingredients', this.ingredients);
    });
  }

  getHalfIngredientsFromRecipe(half: number) {
    let ingredientTotal = 0;
    const ingredientList = [];

    for (const ingredient of this.ingredients) {
      if (ingredientTotal % 2 === half) {
        ingredientList.push(ingredient);
      }
      ingredientTotal++;
    }
    return ingredientList;
  }

}
