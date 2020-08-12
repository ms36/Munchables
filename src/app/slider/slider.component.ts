import { ingredients } from './../mockDB';
import { RecipeIngredients } from './../models/recipeIngredients';
import { RecipeIngredientsService } from './../recipe-ingredients.service';
import { Ingredients } from './../models/ingredients';
import { StepsService } from './../steps.service';
import { IngredientsService } from './../ingredients.service';
import { RecipesService } from './../recipes.service';
import { Recipe } from './../models/recipe';
import { Component, OnInit } from '@angular/core';
import { Steps } from '../models/steps';
import { EditType } from './editType';

@Component({
  selector: 'app-slider',
  templateUrl: './slider.component.html',
  styleUrls: ['./slider.component.css']
})
export class SliderComponent implements OnInit {
  recipes: Recipe[] = [];
  steps: Steps[] = [];
  ingredient: Ingredients[] = [];
  recipeIngredients: RecipeIngredients[] = [];
  public editType = EditType;

  listOfIngredients = [];

  public showEditRecipeNameLeft = true;
  public showEditRecipeNameRight = true;
  public showEditIngredientsLeft = true;
  public showEditIngredientsRight = true;
  public showEditStepsLeft = true;
  public showEditStepsRight = true;

  public alpha = 1.0;
  public setIntervalID;

  public leftPageNumber = 1;
  public rightPageNumber = 2;
  public numberOfPagesAdded = 2;

  constructor(
    private recipesService: RecipesService,
    private ingredientsService: IngredientsService,
    private recipeIngredientsService: RecipeIngredientsService,
    private stepsService: StepsService
  ) {}

  ngOnInit() {
    this.getRecipes();
    this.getIngredient();
    this.getRecipeIngredients();
    this.getSteps();
  }
  myFunction() {
    console.log('double click');
    this.showEditRecipeNameLeft = !this.showEditRecipeNameLeft;
  }
  // Hides/Shows edit options
  toggleEdit(toggle: number, page: number) {
    const thisPointer = this;
    console.log('alpha: ', this.alpha);
    if (this.alpha > 0) {
      this.setIntervalID = setInterval(this.setAlpha, 1000, thisPointer, this.setIntervalID);
    } else {
      clearInterval(this.setIntervalID);
    }
    switch (toggle) {
      case EditType.RECIPENAME: {
        // Toggle left page recipe edit
        if (page === EditType.LEFTPAGE) {
          this.showEditRecipeNameLeft = !this.showEditRecipeNameLeft;
          // Toggle right page recipe edit
        } else {
          this.showEditRecipeNameRight = !this.showEditRecipeNameRight;
        }
        break;
      }
      case EditType.INGREDIENTS: {
        // Toggle left page ingredient edit
        if (page === EditType.LEFTPAGE) {
          this.showEditIngredientsLeft = !this.showEditIngredientsLeft;
        } else {
          // Toggle right page ingredient edit
          this.showEditIngredientsRight = !this.showEditIngredientsRight;
        }
        break;
      }
      case EditType.STEPS: {
        // Toggle left page step edit
        if (page === EditType.LEFTPAGE) {
          this.showEditStepsLeft = !this.showEditStepsLeft;
        } else {
          // Toggle right page step edit
          this.showEditStepsRight = !this.showEditStepsRight;
        }
        break;
      }
    }
  }
  turnPage(dir: number) {
    if (dir === 0) {
      if (this.leftPageNumber < 0) {
        // do nothing
      } else {
        this.leftPageNumber -= this.numberOfPagesAdded;
        this.rightPageNumber -= this.numberOfPagesAdded;
      }
    } else {
      if (this.rightPageNumber > this.recipes.length - 1) {
        // do nothing
      } else {
        this.leftPageNumber += this.numberOfPagesAdded;
        this.rightPageNumber += this.numberOfPagesAdded;
      }
    }
    // Ensure edits are closed when moving to new recipe
    this.showEditRecipeNameLeft = true;
    this.showEditRecipeNameRight = true;
    this.showEditIngredientsLeft = true;
    this.showEditIngredientsRight = true;
    this.showEditStepsLeft = true;
    this.showEditStepsRight = true;
  }
  setAlpha(t, i) {
    console.log('set alpha');
    t.alpha -= 0.1;
    console.log('alpha: ', t.alpha);
    document.getElementById('ingredientNameTextLeft').style.setProperty('--font-ingredient-text-lplc-alpha', t.alpha.toString());
    // tslint:disable-next-line: max-line-length
    console.log('style var: ', document.getElementById('ingredientNameTextLeft').style.getPropertyValue('--font-ingredient-text-lplc-alpha'));
    if ( t.alpha < 0) {
    clearInterval(i);
    }
  }
  // ~~~~~~~~~~~~~~~~ RECIPES ~~~~~~~~~~~~~~~~~~~~~~~~~~

  // tslint:disable-next-line: no-shadowed-variable
  genRecipeId(recipe: Recipe[]): number {
    return recipe.length > 0
      ? Math.max(...recipe.map(recipes => recipes.recipeId)) + 1
      : 1;
  }

  getRecipes(): void {
    // tslint:disable-next-line: no-shadowed-variable
    this.recipesService.getRecipes().subscribe(recipe => {
      this.recipes = recipe;
    });
  }

  addRecipes(recipeName: string): void {
    if (!recipeName) {
      return;
    }
    this.recipesService
      .addRecipes({ recipeName } as Recipe)
      // tslint:disable-next-line: no-shadowed-variable
      .subscribe(recipe => {
        recipe.recipeId = this.genRecipeId(this.recipes);
        this.recipes.push(recipe);
      });
  }
  // tslint:disable-next-line: no-shadowed-variable
  updateRecipe(name: string, recipe: Recipe) {
    if (!name) {
      return;
    }
    this.recipes[recipe.recipeId - 1].recipeName = name;
    this.recipesService.updateRecipes(this.recipes[recipe.recipeId - 1]);

    // Close the edit windows
    this.showEditRecipeNameLeft = true;
    this.showEditRecipeNameRight = true;
  }
  // tslint:disable-next-line: no-shadowed-variable
  deleteRecipe(recipe: Recipe) {
    this.recipes = this.recipes.filter(r => r !== recipe);
    this.recipesService.deleteRecipes(recipe).subscribe();

    // Close the edit windows
    this.showEditRecipeNameLeft = true;
    this.showEditRecipeNameRight = true;
  }
  // ~~~~~~~~~~~~~~~~ INGREDIENTS ~~~~~~~~~~~~~~~~~~~~~~~~~~

  genIngredientId(temp: Ingredients[]): number {
    return temp.length > 0
      ? Math.max(...temp.map(ingredient => ingredient.ingredientId)) + 1
      : 1;
  }
  getIngredient(): void {
    // tslint:disable-next-line: no-shadowed-variable
    this.ingredientsService.getIngredients().subscribe(ingredients => {
      this.ingredient = ingredients;
    });
  }
  /**
   * Gets all ingredients from a recipe and returns every other ingredient
   * (half == 0 or half == 1) determines which half
   * Used for a two column table
   */
  getHalfIngredientsFromRecipe(recipeId: number, half: number) {
    let ingredientTotal = 0;
    const ingredientList = [];
    for (const recipeIngredient of this.recipeIngredients) {
      if (recipeId === recipeIngredient.recipeId) {
        // tslint:disable-next-line: prefer-for-of
        for (let i = 0; i < this.ingredient.length; i++) {
          if (
            recipeIngredient.ingredientId === this.ingredient[i].ingredientId
          ) {
            if (ingredientTotal % 2 === half) {
              ingredientList.push(this.ingredient[i]);
            }
            ingredientTotal++;
          }
        }
      }
    }
    return ingredientList;
  }
  addIngredient(name: string, recipeId: number): void {
    if (!name) {
      return;
    }
    this.ingredientsService
      .addIngredient({ name } as Ingredients)
      .subscribe(temp => {
        temp.ingredientId = this.genIngredientId(this.ingredient);
        this.ingredient.push(temp);
        this.addRecipeIngredientRecipe(recipeId);
      });
  }
  deleteIngredient(ingredient: Ingredients, recipeId: number) {
    for (let i = 0; this.recipeIngredients.length; i++) {
      if (recipeId === this.recipeIngredients[i].recipeId) {
        if (
          ingredient.ingredientId === this.recipeIngredients[i].ingredientId
        ) {
          this.recipeIngredients.splice(i, 1);
          console.log(this.recipeIngredients);
        }
      }
    }
  }
  // deleteIngredientElement(column: number, row: number, ingredient: Ingredients, recipeId: number) {
  //   // document.getElementsByTagName('th').item(column).getElementsByTagName('td')[row].remove();
  //   // document.getElementsByTagName('th').item(column).getElementsByTagName('td')[row].hidden = true;
  //   this.deleteIngredient(ingredient, recipeId);
  // }
  // ~~~~~~~~~~~~~~~~ RECIPE INGREDIENTS ~~~~~~~~~~~~~~~~~~~~~~~~~~

  getRecipeIngredients(): void {
    this.recipeIngredientsService
      .getRecipeIngredients()
      .subscribe(recipeIngredient => {
        this.recipeIngredients = recipeIngredient;
            });
  }
  addRecipeIngredientRecipe(id: number): void {
    const tempRecipeIngredient = new RecipeIngredients();

    length = this.ingredient.length - 1;
    tempRecipeIngredient.ingredientId = this.ingredient[length].ingredientId;

    tempRecipeIngredient.recipeId = this.recipes[id].recipeId;

    this.recipeIngredients.push(tempRecipeIngredient);
  }

  // ~~~~~~~~~~~~~~~~~~~~ STEPS ~~~~~~~~~~~~~~~~~~~~~~~~~~

  genStepId(temp: Steps[]): number {
    return temp.length > 0
      ? Math.max(...temp.map(steps => steps.stepId)) + 1
      : 1;
  }
  getSteps(): void {
    this.stepsService.getSteps().subscribe(step => {
      this.steps = step;
    });
  }
  addSteps(step: string, recipeId: number): void {
    this.stepsService.addSteps({ step } as Steps).subscribe(temp => {
      temp = new Steps();
      temp.step = step;
      temp.stepId = this.genStepId(this.steps);
      temp.recipeId = this.recipes[recipeId].recipeId;
      this.steps.push(temp);
    });
  }
  getStepsFromRecipe(recipeId: number) {
    const stepsList = [];
    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < this.steps.length; i++) {
      if (recipeId === this.steps[i].recipeId) {
        stepsList.push(this.steps[i]);
      }
    }
    return stepsList;
  }
  updateSteps(name: string, step: Steps) {
    if (!name) {
      return;
    }
    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < this.steps.length; i++) {
      if (step.stepId === this.steps[i].stepId) {
        this.steps[i].step = name;
        this.stepsService.updateSteps(this.steps[i]);
      }
    }
    // this.toggleEdit(this.editType.STEPS);
  }
  deleteStep(step: Steps) {
    console.log('deleteStep: ', step);
    for (let i = 0; i < this.steps.length; i++) {
      if (step.stepId === this.steps[i].stepId) {
        this.steps.splice(i, 1);
        this.stepsService.deleteSteps(this.steps[i]);
      }
    }
    console.log('deleteStep: ', this.steps);
  }
}
