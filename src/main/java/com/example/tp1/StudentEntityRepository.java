package com.example.tp1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentEntityRepository extends JpaRepository<Student,Integer> {
    public Student findByEmail(String email);
    public Optional<Student> findById(Integer id);
    public List<Student> findByFirstName(String firstname);
    @Query("SELECT s FROM Student s WHERE s.age > 20")
    public List<Student> findByAgeGreaterThan20();
}
