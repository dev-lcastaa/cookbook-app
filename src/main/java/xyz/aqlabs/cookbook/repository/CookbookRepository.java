package xyz.aqlabs.cookbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.aqlabs.cookbook.model.Cookbook;

import java.util.Optional;

@Repository
public interface CookbookRepository extends JpaRepository<Cookbook, Integer> {

    Optional<Cookbook[]> findByUserId(Integer userId);
}
