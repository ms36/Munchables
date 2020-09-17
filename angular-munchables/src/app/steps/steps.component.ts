import { Component, OnInit, Input } from '@angular/core';
import { GlobalVariables } from '../models/globalVariables';
import { Steps } from '../models/step';
import { StepService } from './step.service';

@Component({
  selector: 'app-steps',
  templateUrl: './steps.component.html',
  styleUrls: ['./steps.component.css']
})
export class StepsComponent implements OnInit {
  @Input() pageNumber: number;

  steps: Steps[] = [];
  constructor(private stepService: StepService) { }

  ngOnInit() {
    this.getSteps(this.pageNumber);
  }

  getSteps(recipeId: number): void {
    this.stepService.getSteps(recipeId).subscribe(step => {
      this.steps = step;
      console.log('steps', this.steps);
    });
  }

}
