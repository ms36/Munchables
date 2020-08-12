import { Ingredients } from './ingredients';
import { Steps } from './steps';

export class Recipe {
  recipeId: number;
  recipeName: string;

  constructor() {
    this.recipeId = null;
    this.recipeName = null;
  }
}
