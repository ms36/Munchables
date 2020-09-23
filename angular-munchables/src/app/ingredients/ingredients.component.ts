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
  isAddIngredientEditable = false;
  isUpdateLeftIngredientEditable = [false, false, false, false, false, false];
  isUpdateRightIngredientEditable = [false, false, false, false, false, false];
  showAddIngredient = false;
  // TODO: the size of these arrays should dependant on half
  // the number ingredients in a recipe and not hard coded
  showLeftDeleteIngredient = [false, false, false, false, false, false];
  showRightDeleteIngredient = [false, false, false, false, false, false];
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
    // If ingredientName does not contain all spaces or is not empty
    if (ingredientName.search('^\\s*$') !== 0) {
      let ingredient: Ingredients;
      this.ingredientsService.addIngredient({id: null, name: ingredientName},
                                            this.recipes[this.pageNumber - 1].id)
                                            .subscribe(i => {ingredient = i;
                                                             this.recipes[this.pageNumber - 1].ingredients.push(ingredient); });
    }
  }

  toggleAddIngredient(ingredientName = '') {
    this.isAddIngredientEditable = !this.isAddIngredientEditable;
    if (this.isAddIngredientEditable) {
      setTimeout(() => document.getElementById('textInput').focus(), 0);
    }
    if (!this.isAddIngredientEditable) {
      this.add(ingredientName);
    }
  }

  updateIngredient(index: number, ingredientName: string, leftOrRightColumn: number) {
    if (leftOrRightColumn === 0) {
      this.recipes[this.pageNumber - 1].ingredients[index * 2].name = ingredientName;
      this.isUpdateLeftIngredientEditable[index] = false;
    } else {
      this.recipes[this.pageNumber - 1].ingredients[(index * 2) + 1].name = ingredientName;
      this.isUpdateRightIngredientEditable[index] = false;
    }
  }

  checkToHideInput() {
    if (this.showAddIngredient && !this.isAddIngredientEditable) {
      this.showAddIngredient = false;
    }
  }
}
