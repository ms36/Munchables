import { Component, OnInit, Input } from '@angular/core';
import { Recipe } from '../models/recipe';
import { Steps } from '../models/step';
import { StepService } from './step.service';

@Component({
  selector: 'app-steps',
  templateUrl: './steps.component.html',
  styleUrls: ['./steps.component.css']
})
export class StepsComponent implements OnInit {
  @Input() pageNumber: number;
  @Input() recipes: Recipe[];

  steps: Steps[] = [];
  isAddStepEditable = false;
  showAddStep = false;
  constructor(private stepService: StepService) { }

  ngOnInit() {
  }

  toggleAddStep() {
    this.isAddStepEditable = !this.isAddStepEditable;
  }

  checkToHideInput() {
    if (this.showAddStep && !this.isAddStepEditable) {
      this.showAddStep = false;
    }
  }
}
