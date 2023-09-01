package xyz.aqlabs.cookbook.repository;

/*
This Class contains the implementations, anything that will interact with the
employee database will need to have.
*/

import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import xyz.aqlabs.cookbook.model.User;



import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    // --------|Update, Delete Operations |--------
    @Modifying @Transactional @Query("UPDATE User user SET user.username = :username, user.email = :email WHERE user.userId = :userId")
    void updateUser(@Param(value = "userId") Integer userId,
                        @Param(value = "username") String username,
                        @Param(value = "email") String email
    );


    @Modifying @Transactional @Query("DELETE FROM User user WHERE user.userId = :userId")
    void deleteUser(
            @Param(value = "userId") Integer userId
    );


    //---------| Query Methods [Default] |--------

    User findByUserId(Integer id);


    Optional<User> findByEmail(String email);

    @NonNull
    Optional<User> findByUsername(String username);

}

