import { Ingredients } from './ingredients';
import { Steps } from './step';

export class Recipe {
  id: number;
  name: string;
  ingredients: Ingredients[];
  steps: Steps[];

  constructor() {
    const ingredient = new Ingredients();
    const step = new Steps();
    this.id = null;
    this.name = 'New Recipe';
    this.ingredients = [ingredient];
    this.steps = [step];
  }
}
