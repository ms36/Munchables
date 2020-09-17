package com.skillstorm.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "STEP")
public class Step {
	
	@Id
	@Column(name = "STEP_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "STEP_ORDER")
	private int stepOrder;
	
	@Column(name = "STEP_PROCESS")
	private String stepProcess;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RECIPE_ID")
	@JsonBackReference(value = "recipeSteps")
	private Recipe recipe;
	
	Step() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStepOrder() {
		return stepOrder;
	}

	public void setStepOrder(int stepOrder) {
		this.stepOrder = stepOrder;
	}

	public String getStepProcess() {
		return stepProcess;
	}

	public void setStepProcess(String stepProcess) {
		this.stepProcess = stepProcess;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	@Override
	public String toString() {
		return "Step [id=" + id + ", stepOrder=" + stepOrder + ", stepProcess=" + stepProcess + ", recipe=" + recipe
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((recipe == null) ? 0 : recipe.hashCode());
		result = prime * result + stepOrder;
		result = prime * result + ((stepProcess == null) ? 0 : stepProcess.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Step other = (Step) obj;
		if (id != other.id)
			return false;
		if (recipe == null) {
			if (other.recipe != null)
				return false;
		} else if (!recipe.equals(other.recipe))
			return false;
		if (stepOrder != other.stepOrder)
			return false;
		if (stepProcess == null) {
			if (other.stepProcess != null)
				return false;
		} else if (!stepProcess.equals(other.stepProcess))
			return false;
		return true;
	}	
}
