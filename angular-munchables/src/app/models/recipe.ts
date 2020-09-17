import { Ingredients } from './ingredients';
import { Steps } from './step';

export class Recipe {
  id: number;
  name: string;
  ingredients: Ingredients[];
  steps: Steps[];

  constructor() {
    this.id = null;
    this.name = '';
    this.ingredients = [];
    this.steps = [];
  }
}
