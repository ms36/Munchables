package com.skillstorm.munchables.beans;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name= "Steps")
public class Steps {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "StepId")
	public int stepId;
	
	@Column(name = "step")
	public String step;
	
	@ManyToOne
	@JoinColumn(name = "RecipeId", insertable = false, updatable = false)
	@JsonBackReference(value = "recipeSteps")
	private Recipe recipe;
	
	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Steps() {
		super();
	}

	public Steps(int stepId, String step) {
		super();
		this.stepId = stepId;
		this.step = step;
	}

	public int getRecipeId() {
		return recipe.getRecipeId();
	}

	public void setRecipeId(int recipeId) {
		recipe.setRecipeId(recipeId);
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	@Override
	public String toString() {
		return "Steps [stepId=" + stepId + ", step=" + step + "]";
	}

}
