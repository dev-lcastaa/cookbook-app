package xyz.aqlabs.cookbook.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import xyz.aqlabs.cookbook.model.Cookbook;

import java.util.Optional;

@Repository
public interface CookbookRepository extends JpaRepository<Cookbook, Integer> {

    Optional<Cookbook[]> findByUserId(Integer userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Cookbook c WHERE c.id = :id")
    void deleteCookbook(@Param("id") Integer id);

}
