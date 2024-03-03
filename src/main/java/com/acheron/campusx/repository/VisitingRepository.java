package com.acheron.campusx.repository;

import com.acheron.campusx.entity.Visiting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitingRepository extends JpaRepository<Visiting,Long> {
}
