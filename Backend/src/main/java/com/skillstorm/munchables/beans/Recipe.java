package com.skillstorm.munchables.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Recipe")
public class Recipe{
	
	@Id
	@Column(name = "RecipeId")
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int recipeId;
	
	@Column(name = "RecipeName")
	private String recipeName;
	
	@OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY)
	@JsonManagedReference(value = "recipeJoin")
	private List<RecipeIngredients> recipeIngredients;

	@OneToMany(mappedBy = "recipe")
	@JsonManagedReference(value = "recipeSteps")
	private Set<Steps>steps;

	public Recipe() {
		super();
	}

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public List<RecipeIngredients> getRecipeIngredients() {
		return recipeIngredients;
	}

	public void setRecipeIngredients(List<RecipeIngredients> recipeIngredients) {
		this.recipeIngredients = recipeIngredients;
	}

	public Set<Steps> getSteps() {
		return steps;
	}

	public void setSteps(Set<Steps> steps) {
		this.steps = steps;
	}

	@Override
	public String toString() {
		return "Recipe [recipeId=" + recipeId + ", recipeName=" + recipeName + ", recipeIngredients="
				+ recipeIngredients + ", steps=" + steps + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + recipeId;
		result = prime * result + ((recipeIngredients == null) ? 0 : recipeIngredients.hashCode());
		result = prime * result + ((recipeName == null) ? 0 : recipeName.hashCode());
		result = prime * result + ((steps == null) ? 0 : steps.hashCode());
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
		Recipe other = (Recipe) obj;
		if (recipeId != other.recipeId)
			return false;
		if (recipeIngredients == null) {
			if (other.recipeIngredients != null)
				return false;
		} else if (!recipeIngredients.equals(other.recipeIngredients))
			return false;
		if (recipeName == null) {
			if (other.recipeName != null)
				return false;
		} else if (!recipeName.equals(other.recipeName))
			return false;
		if (steps == null) {
			if (other.steps != null)
				return false;
		} else if (!steps.equals(other.steps))
			return false;
		return true;
	}
	
//	@ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//        name = "RecipeIngredients", 
//        joinColumns = { @JoinColumn(name = "RecipeId") }, 
//        inverseJoinColumns = { @JoinColumn(name = "IngredientId") })
//	@JsonManagedReference(value = "recipeJoin")
//	private Set<Ingredients> ingredients;
	
//	public Set<Ingredients> getIngredients() {
//		return ingredients;
//	}
//	
//	public void setIngredients(Set<Ingredients> ingredients) {
//		this.ingredients = ingredients;
//	}
	
}
