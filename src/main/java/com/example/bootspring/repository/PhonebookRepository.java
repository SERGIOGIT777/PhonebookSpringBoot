package com.example.bootspring.repository;

import com.example.bootspring.entity.Phonebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhonebookRepository extends JpaRepository<Phonebook, Long> {

    @Query(value = "select c from Phonebook c where c.firstname = :firstname")
    List<Phonebook> findByName(@Param("firstname") String firstname);

    @Modifying
    @Query(value = "update Phonebook c set c.firstname=:firstname, c.lastname=:lastname," +
            "c.age=:age, c.phone_number=:phone_number, c.address=:address where c.id=:id")
    void updateApplication(@Param(value = "id") long id, @Param(value = "firstname") String firstname,
                           @Param(value = "lastname") String lastname, @Param(value = "age") Long age,
                           @Param(value = "phone_number") String phone_number,
                           @Param(value = "address") String address);

    @Query(value = "select c from Phonebook c where c.id = :id")
    Phonebook getId(@Param(value = "id") Long id);

}
