import { Component, OnInit } from '@angular/core';
import { Recipe } from './models/recipe';
import { RecipeService } from './recipe/recipe.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'angular-munchables';
  leftPageNumber = 1;
  rightPageNumber = 2;
  numberOfRecipes = 2;
  recipes: Recipe[];
  constructor(private recipeService: RecipeService) { }

  ngOnInit() {
    this.getAllRecipes();
  }

  getAllRecipes(): void {
    this.recipeService.getAllRecipes().subscribe(recipe => {
      this.recipes = recipe;
      this.numberOfRecipes = this.recipes.length + 1;
      console.log('recipes', this.recipes);
    });
  }

  turnPage(direction: string): void {
    if (direction === 'left') {
      this.leftPageNumber -= 2;
      this.rightPageNumber -= 2;
    } else if (direction === 'right') {
      this.leftPageNumber += 2;
      this.rightPageNumber += 2;
    }
  }

  receiveMessage($event: number) {
    console.log('receiveMessage', $event);
    this.numberOfRecipes = $event;
  }
}
