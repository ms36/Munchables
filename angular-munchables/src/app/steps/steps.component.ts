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
  showDeleteStep = [false, false, false, false, false, false];
  isUpdateStepEditable = [false, false, false, false, false, false];

  constructor(private stepService: StepService) { }

  ngOnInit() {
  }

  addStepProcess(addStepProcess = '') {
    // If addStepProcess does not contain all spaces or is not empty
    if (addStepProcess.search('^\\s*$') !== 0) {
      let step: Steps;
      const stepLength = this.recipes[this.pageNumber - 1].steps.length;
      addStepProcess = this.capitalizeFirstLetter(addStepProcess);
      const newStep = {id: null, stepOrder: stepLength, stepProcess: addStepProcess};
      const recipeId =  this.recipes[this.pageNumber - 1].id;

      this.stepService.addStep(newStep, recipeId).subscribe(s => {
        step = s;
        this.recipes[this.pageNumber - 1].steps.push(step); });
      }
  }

  updateStepProcess(index: number, stepOrder: number, stepProcess: string) {
    const recipeId = this.recipes[this.pageNumber - 1].id;
    const stepId = this.recipes[this.pageNumber - 1].steps[index].id;

    this.isUpdateStepEditable[index] = false;
    this.recipes[this.pageNumber - 1].steps[index].stepProcess = stepProcess;

    this.stepService.saveStep({id: stepId, stepOrder, stepProcess}, recipeId).subscribe();
  }

  deleteStepProcess(stepId: number) {
    const steps = this.recipes[this.pageNumber - 1].steps;
    this.stepService.deleteStep(stepId).subscribe(() => {
      this.recipes[this.pageNumber - 1].steps = steps.filter(
      step => step.id !== stepId); });
  }

  toggleUpdateStepProcess(index: number) {
    this.isUpdateStepEditable[index] = !this.isUpdateStepEditable[index];
    if (this.isUpdateStepEditable[index]) {
      setTimeout(() => document.getElementById('input-edit-step').focus(), 0);
    }
  }

  toggleAddStepProcess(stepProcess = '') {
    this.isAddStepEditable = !this.isAddStepEditable;
    if (this.isAddStepEditable) {
      setTimeout(() => document.getElementById('input-add-step').focus(), 0);
    }
    if (!this.isAddStepEditable) {
      this.addStepProcess(stepProcess);
    }
  }

  checkToHideInput() {
    if (this.showAddStep && !this.isAddStepEditable) {
      this.showAddStep = false;
    }
  }

  capitalizeFirstLetter(str: string): string {
    const firstLetterUpperCase = str.substring(0, 1).toUpperCase();
    const stringWithoutFirstLetter = str.substring(1, str.length);
    return firstLetterUpperCase + stringWithoutFirstLetter;
  }
}
