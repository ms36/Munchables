package com.skillstorm.munchables.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "RecipeIngredients")
public class RecipeIngredients implements Serializable{
	
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "JoinTableId")
//	private int joinTableId;
	
	@Id
	@Column(name = "RecipeId")
	private int RecipeId;
	
	@Id
	@Column(name = "IngredientId")
	private int IngredientId;
	
	@ManyToOne//(fetch = FetchType.EAGER)
	@JoinColumn(name = "RecipeId")
	@JsonBackReference(value = "recipeJoin")
	private Recipe recipe;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IngredientId")
	@JsonBackReference(value = "ingredientJoin")
	private Ingredients ingredients;

	public RecipeIngredients() {
		super();
	}

	public int getRecipeId() {
		return RecipeId;
	}

	public void setRecipeId(int recipeId) {
		RecipeId = recipeId;
	}

	public int getIngredientId() {
		return IngredientId;
	}

	public void setIngredientId(int ingredientId) {
		IngredientId = ingredientId;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Ingredients getIngredients() {
		return ingredients;
	}

	public void setIngredients(Ingredients ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public String toString() {
		return "RecipeIngredients [RecipeId=" + RecipeId + ", IngredientId=" + IngredientId + ", recipe=" + recipe
				+ ", ingredients=" + ingredients + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + IngredientId;
		result = prime * result + RecipeId;
		result = prime * result + ((ingredients == null) ? 0 : ingredients.hashCode());
		result = prime * result + ((recipe == null) ? 0 : recipe.hashCode());
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
		RecipeIngredients other = (RecipeIngredients) obj;
		if (IngredientId != other.IngredientId)
			return false;
		if (RecipeId != other.RecipeId)
			return false;
		if (ingredients == null) {
			if (other.ingredients != null)
				return false;
		} else if (!ingredients.equals(other.ingredients))
			return false;
		if (recipe == null) {
			if (other.recipe != null)
				return false;
		} else if (!recipe.equals(other.recipe))
			return false;
		return true;
	}
	
}
