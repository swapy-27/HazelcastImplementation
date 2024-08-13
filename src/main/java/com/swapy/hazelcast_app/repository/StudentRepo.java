package com.swapy.hazelcast_app.repository;


import com.swapy.hazelcast_app.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Integer> {

}
