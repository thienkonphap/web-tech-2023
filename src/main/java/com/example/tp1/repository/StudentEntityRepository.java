package com.example.tp1.repository;

import com.example.tp1.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentEntityRepository extends JpaRepository<StudentEntity, Integer> {
    Optional<StudentEntity> findById(UUID id);

    public StudentEntity findByEmail(String email);

    public List<StudentEntity> findByFirstName(String firstname);

    @Query("SELECT s FROM StudentEntity s WHERE s.age > 20")
    public List<StudentEntity> findByAgeGreaterThan20();

    void deleteById(UUID id);
}
