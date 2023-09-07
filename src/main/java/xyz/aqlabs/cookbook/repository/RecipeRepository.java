package xyz.aqlabs.cookbook.repository;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import xyz.aqlabs.cookbook.model.Recipe;


import java.util.Optional;


@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {


    Optional<Recipe[]> findByCookBookId(Integer cookBookId);


    @Transactional
    @Modifying
    @Query("DELETE FROM Recipe r WHERE r.id = :recipeId")
    void deleteRecipe(@Param("recipeId") Integer recipeId);




}
