import { RecipeService } from './recipe.service';
import { Recipe } from './../models/recipe';
import { Component, OnInit, Input } from '@angular/core';
import { Ingredients } from '../models/ingredients';
import { Steps } from '../models/step';

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit {
  @Input() pageNumber: number;
  @Input() recipes: Recipe[];

  constructor(private recipeService: RecipeService) { }

  ngOnInit() {
  }

}
