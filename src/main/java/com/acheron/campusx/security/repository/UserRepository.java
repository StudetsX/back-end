package com.acheron.campusx.security.repository;

import com.acheron.campusx.security.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

//    @Query("select a from User a where a.group.name like %?1% or a.lastName like %?2%  and a.role='STUDENT' ")
//    List<User> findAll(String group,String lastName);
@Query("SELECT a FROM User a WHERE (:group IS NULL OR a.group.name LIKE %:group%) AND (:lastName IS NULL OR a.lastName LIKE %:lastName%) AND a.role = 'STUDENT'")
List<User> findAll(String group, String lastName);


}
