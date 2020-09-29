import { RecipeService } from './recipe.service';
import { Recipe } from './../models/recipe';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit {
  @Input() pageNumber: number;
  @Input() recipes: Recipe[];

  isUpdateRecipeNameEditable = false;
  showDeleteRecipeName = false;
  numberOfRecipes: number;

  constructor(private recipeService: RecipeService) { }

  ngOnInit() {
    this.numberOfRecipes = this.recipes.length;
  }

  updateStepProcess() {
    const recipe = this.recipes[this.pageNumber - 1];
    this.isUpdateRecipeNameEditable = false;
    this.recipeService.saveRecipe(recipe).subscribe();
  }

  deleteRecipe() {
    const recipe = this.recipes[this.pageNumber - 1];
    this.recipeService.deleteRecipe(recipe.id).subscribe(() => {
      this.recipes = this.recipes.filter(
      r => r.id !== recipe.id); });
  }

  toggleUpdateRecipeName() {
    this.isUpdateRecipeNameEditable = !this.isUpdateRecipeNameEditable;
    if (this.isUpdateRecipeNameEditable) {
      setTimeout(() => document.getElementById('input-recipe-name').focus(), 0);
    }
  }

}
