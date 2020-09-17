import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular-munchables';
  leftPageNumber = 1;
  rightPageNumber = 2;
  numberOfRecipes = 5;

  turnPage(direction: string) {
    if (direction === 'left') {
      this.leftPageNumber -= 2;
      this.rightPageNumber -= 2;
    } else if (direction === 'right') {
      this.leftPageNumber += 2;
      this.rightPageNumber += 2;
    }
  }
}
