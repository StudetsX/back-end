package com.acheron.campusx.security.repository;

import com.acheron.campusx.entity.Chair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChairRepository extends JpaRepository<Chair,Long> {
}
