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

  recipe = new Recipe();

  constructor(private recipeService: RecipeService) { }

  ngOnInit() {
    this.getRecipe(this.pageNumber);
  }

  getRecipe(recipeId: number): void {
    this.recipeService.getRecipe(recipeId).subscribe(recipe => {
      this.recipe = recipe;
      console.log('recipe', this.recipe);
    });
  }

}
