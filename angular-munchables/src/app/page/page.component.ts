import { Component, OnInit, Input } from '@angular/core';
import { Recipe } from '../models/recipe';

@Component({
  selector: 'app-page',
  templateUrl: './page.component.html',
  styleUrls: ['./page.component.css']
})
export class PageComponent implements OnInit {
  @Input() pageNumber: number;
  @Input() recipes: Recipe[];

  constructor() { }

  ngOnInit() {
  }

}
