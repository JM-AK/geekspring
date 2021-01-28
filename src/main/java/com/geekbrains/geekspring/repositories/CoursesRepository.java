package com.geekbrains.geekspring.repositories;


import com.geekbrains.geekspring.entities.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends CrudRepository<Course, Long> {
}
