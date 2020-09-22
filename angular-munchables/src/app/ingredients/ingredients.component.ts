import { IngredientsService } from './ingredients.service';
import { Component, OnInit, Input } from '@angular/core';
import { Ingredients } from '../models/ingredients';
import { Recipe } from '../models/recipe';

@Component({
  selector: 'app-ingredients',
  templateUrl: './ingredients.component.html',
  styleUrls: ['./ingredients.component.css']
})
export class IngredientsComponent implements OnInit {
  @Input() pageNumber: number;
  @Input() recipes: Recipe[];

  ingredients: Ingredients[] = [];
  isEditable = false;
  constructor(private ingredientsService: IngredientsService) { }

  ngOnInit() {
  }

  getHalfIngredientsFromRecipe(half: number) {
    let ingredientTotal = 0;
    const ingredientList = [];

    for (const ingredient of this.recipes[this.pageNumber - 1].ingredients) {
      if (ingredientTotal % 2 === half) {
        ingredientList.push(ingredient);
      }
      ingredientTotal++;
    }
    return ingredientList;
  }

  delete(ingredientName: string) {
    const ingredients = this.recipes[this.pageNumber - 1].ingredients;
    this.recipes[this.pageNumber - 1].ingredients = ingredients.filter(
                                                    ingredient => ingredient.name !== ingredientName);
    const ingredientId = ingredients.filter(ingredient => ingredient.name === ingredientName);

    if (ingredientId[0].id !== null) {
      this.ingredientsService.deleteIngredient(ingredientId[0].id).subscribe();
    }
  }

  add(ingredientName = '') {
    // If ingredientName does not contains all spaces or is not empty
    if (ingredientName.search('^\\s*$') !== 0) {
      this.recipes[this.pageNumber - 1].ingredients.push({id: null, name: ingredientName});
      this.ingredientsService.addIngredient({id: null, name: ingredientName},
                                            this.recipes[this.pageNumber - 1].id).subscribe();
    }
  }

  toggleEdit(ingredientName = '') {
    this.isEditable = !this.isEditable;
    if (this.isEditable) {
      setTimeout(() => document.getElementById('textInput').focus(), 0);
    }
    if (!this.isEditable) {
      this.add(ingredientName);
    }
  }
}
