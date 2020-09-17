package com.skillstorm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.skillstorm.beans.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer>{

}
