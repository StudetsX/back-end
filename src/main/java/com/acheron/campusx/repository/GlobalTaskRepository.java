package com.acheron.campusx.repository;

import com.acheron.campusx.entity.GlobalTask;
import com.acheron.campusx.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GlobalTaskRepository extends JpaRepository<GlobalTask,Long> {

    List<GlobalTask> findAllBySubject(Subject subject);
}
