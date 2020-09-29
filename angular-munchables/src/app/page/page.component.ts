import { Component, OnInit, Input } from '@angular/core';
import { Recipe } from '../models/recipe';
import { RecipeService } from '../recipe/recipe.service';

@Component({
  selector: 'app-page',
  templateUrl: './page.component.html',
  styleUrls: ['./page.component.css']
})
export class PageComponent implements OnInit {
  @Input() pageNumber: number;
  @Input() recipes: Recipe[];
  isAddRecipeEditable = false;

  constructor(private recipeService: RecipeService) { }

  ngOnInit() {
  }

  addRecipe(recipeName = '') {
    this.isAddRecipeEditable = false;
    console.log('recipeName ', recipeName);
    if (recipeName.search('^\\s*$') !== 0) {
      const recipe = new Recipe();
      recipe.name = recipeName;
      console.log('recipe ', recipe);
      this.recipeService.addRecipe(recipe).subscribe();
    }
  }

  toggleUpdateRecipeName() {
    this.isAddRecipeEditable = !this.isAddRecipeEditable;
    if (this.isAddRecipeEditable) {
      setTimeout(() => document.getElementById('input-recipe-name').focus(), 0);
    }
  }

}
