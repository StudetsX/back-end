package com.acheron.campusx.repository;

import com.acheron.campusx.entity.Rating;
import com.acheron.campusx.entity.Subject;
import com.acheron.campusx.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    Optional<Rating> findByUserAndSubject(User user, Subject subject);
}
