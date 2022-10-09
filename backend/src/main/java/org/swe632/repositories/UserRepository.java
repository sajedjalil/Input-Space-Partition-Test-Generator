package org.swe632.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.swe632.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    @NonNull
    User findByEmailAndPassword(@NonNull String email, @NonNull String password);

    @Query("select (count(u) > 0) from User u where u.email = :email")
    boolean existsByEmail(@Param("email") @NonNull String email);

}