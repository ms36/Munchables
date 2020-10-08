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
  showAddIngredient = false;
  // TODO: the size of these arrays should dependant on half
  // the number ingredients in a recipe and not hard coded
  isUpdateLeftIngredientEditable = [false, false, false, false, false, false];
  isUpdateRightIngredientEditable = [false, false, false, false, false, false];
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

  addIngredient(ingredientName = '') {
    // If ingredientName does not contain all spaces or is not empty
    if (ingredientName.search('^\\s*$') !== 0) {
      let ingredient: Ingredients;
      const newIngredient = {id: null, name: ingredientName};
      const recipeId =  this.recipes[this.pageNumber - 1].id;

      this.ingredientsService.addIngredient(newIngredient, recipeId)
        .subscribe(i => {ingredient = i;
                         this.recipes[this.pageNumber - 1].ingredients.push(ingredient); });
    }
  }

  delete(ingredientId: number) {
    const ingredients = this.recipes[this.pageNumber - 1].ingredients;
    this.ingredientsService.deleteIngredient(ingredientId).subscribe(() => {
      this.recipes[this.pageNumber - 1].ingredients = ingredients.filter(
      ingredient => ingredient.id !== ingredientId); });
  }

  toggleAddIngredient(ingredientName = '') {
    this.isAddIngredientEditable = !this.isAddIngredientEditable;
    if (this.isAddIngredientEditable) {
      setTimeout(() => document.getElementById('textInput').focus(), 0);
    }
    if (!this.isAddIngredientEditable) {
      this.addIngredient(ingredientName);
    }
  }

  toggleUpdateIngredient(index: number, leftOrRightColumn: string) {
    if (leftOrRightColumn === 'left') {
      this.isUpdateLeftIngredientEditable[index] = !this.isUpdateLeftIngredientEditable[index];
      if (this.isUpdateLeftIngredientEditable[index]) {
        setTimeout(() => document.getElementById('input-edit-ingredient').focus(), 0);
      }
    } else {
      this.isUpdateRightIngredientEditable[index] = !this.isUpdateRightIngredientEditable[index];
      if (this.isUpdateRightIngredientEditable[index]) {
        setTimeout(() => document.getElementById('input-edit-ingredient').focus(), 0);
      }
    }
  }

  updateIngredient(index: number, ingredientName: string, leftOrRightColumn: string) {
    let ingredientId: number;
    let indexColumn = (index * 2) + 1;
    const recipeId = this.recipes[this.pageNumber - 1].id;

    if (leftOrRightColumn === 'left') {
      indexColumn = index * 2;
      this.isUpdateLeftIngredientEditable[index] = false;
    } else {
      this.isUpdateRightIngredientEditable[index] = false;
    }
    this.recipes[this.pageNumber - 1].ingredients[indexColumn].name = ingredientName;
    ingredientId = this.recipes[this.pageNumber - 1].ingredients[indexColumn].id;

    const updatedIngredient = {id: ingredientId, name: ingredientName};
    this.ingredientsService.saveIngredient(updatedIngredient, recipeId).subscribe();
  }

  checkToHideInput() {
    if (this.showAddIngredient && !this.isAddIngredientEditable) {
      this.showAddIngredient = false;
    }
  }
}
