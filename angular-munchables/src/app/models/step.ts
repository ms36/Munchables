export class Steps {
  id: number;
  stepOrder: number;
  stepProcess: string;
  recipeId: number;

  constructor() {
    this.id = null;
    this.stepOrder = null;
    this.stepProcess = '';
    this.recipeId = null;
  }
}
