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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Ingredients")
public class Ingredients {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IngredientId")
	public int ingredientId;
	
	@Column(name="IngredientName")
	public String name;
	
	
	@OneToMany(mappedBy = "ingredients", fetch = FetchType.LAZY)
	@JsonManagedReference(value = "ingredientJoin")
	private List<RecipeIngredients> recipeIngredients;


	public Ingredients() {
		super();
	}


	public int getIngredientId() {
		return ingredientId;
	}


	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<RecipeIngredients> getRecipeIngredients() {
		return recipeIngredients;
	}


	public void setRecipeIngredients(List<RecipeIngredients> recipeIngredients) {
		this.recipeIngredients = recipeIngredients;
	}


	@Override
	public String toString() {
		return "Ingredients [ingredientId=" + ingredientId + ", name=" + name + ", recipeIngredients="
				+ recipeIngredients + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ingredientId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((recipeIngredients == null) ? 0 : recipeIngredients.hashCode());
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
		Ingredients other = (Ingredients) obj;
		if (ingredientId != other.ingredientId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (recipeIngredients == null) {
			if (other.recipeIngredients != null)
				return false;
		} else if (!recipeIngredients.equals(other.recipeIngredients))
			return false;
		return true;
	}
	
//	@ManyToMany(mappedBy = "ingredients")
//	@JsonBackReference(value = "ingredientsJoin")
//	private Set<Recipe> recipe;
		
//	public Set<Recipe> getRecipe() {
//		return recipe;
//	}
//
//	public void setRecipe(Set<Recipe> recipe) {
//		this.recipe = recipe;
//	}

}
