package com.energyapp.usermanagement.repositories;

import com.energyapp.usermanagement.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Integer> {

    /**
     * Example: JPA generate Query by Field
     */
    //List<Person> findByName(String name);

    //Optional<AppUser> findById(UUID id);

    Optional<AppUser> findByUsernameAndPassword(String username, String password);
    Optional<AppUser> findByUsername(String username);

    Optional<AppUser> findByRole(String role);

    /**
     * Example: Write Custom Query
     */
    /*@Query(value = "SELECT p " +
            "FROM Person p " +
            "WHERE p.name = :name " +
            "AND p.age >= 60  ")*/
    //Optional<Person> findSeniorsByName(@Param("name") String name);

}
