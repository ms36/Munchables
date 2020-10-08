import { Component, OnInit, Input, Output, EventEmitter  } from '@angular/core';
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
  @Output() messageEvent = new EventEmitter<number>();

  isAddRecipeEditable = false;
  numberOfRecipes: number;

  constructor(private recipeService: RecipeService) { }

  ngOnInit() {
    this.numberOfRecipes = this.recipes.length;
  }

  addRecipe(recipeName = '') {
    this.isAddRecipeEditable = false;
    if (recipeName.search('^\\s*$') !== 0) {
      const recipe = new Recipe();
      recipe.name = recipeName;

      this.recipeService.addRecipe(recipe).subscribe(newRecipe => {
        this.messageEvent.emit(this.numberOfRecipes + 1);
        this.recipes.push(newRecipe); });
    }
  }

  toggleUpdateRecipeName() {
    this.isAddRecipeEditable = !this.isAddRecipeEditable;
    if (this.isAddRecipeEditable) {
      setTimeout(() => document.getElementById('input-recipe-name').focus(), 0);
    }
  }
}
