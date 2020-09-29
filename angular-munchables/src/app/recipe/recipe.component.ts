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

  constructor(private recipeService: RecipeService) { }

  ngOnInit() {
  }

  updateStepProcess() {
    const recipe = this.recipes[this.pageNumber - 1];
    this.isUpdateRecipeNameEditable = false;
    this.recipeService.saveRecipe(recipe).subscribe();
  }

  deleteRecipe() {
    const recipeId = this.recipes[this.pageNumber - 1].id;
    this.recipeService.deleteRecipe(recipeId).subscribe();
  }

  toggleUpdateRecipeName() {
    this.isUpdateRecipeNameEditable = !this.isUpdateRecipeNameEditable;
    if (this.isUpdateRecipeNameEditable) {
      setTimeout(() => document.getElementById('input-recipe-name').focus(), 0);
    }
  }

}
