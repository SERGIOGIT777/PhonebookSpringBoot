package com.example.bootspring.repository;

import com.example.bootspring.entity.Phonebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhonebookRepository extends JpaRepository<Phonebook, Long> {
}
