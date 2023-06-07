package com.xapp.xjava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xapp.xjava.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
