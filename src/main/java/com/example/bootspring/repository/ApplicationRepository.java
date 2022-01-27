package com.example.bootspring.repository;

import com.example.bootspring.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    @Query(value = "select c from Application c where c.firstname = :firstname")
    List<Application> findByName(@Param("firstname") String firstname);

    @Query(value = "select c from Application c where c.firstname = :firstname and c.age = :age")
    List<Application> findByNameAge(@Param("firstname") String firstname, @Param("age") Long age);

    @Query(value = "select c from Application c where c.firstname = :firstname and c.age = :age and c.address = :address")
    List<Application> findByNameAgeAddress(@Param("firstname") String firstname, @Param("age") Long age,
                                           @Param("address") String address);

    @Modifying
    @Query(value = "update Application c set c.firstname=:firstname, c.lastname=:lastname," +
            "c.age=:age, c.address=:address, c.status=:status where c.id=:id")
    void updateApplication(@Param(value = "id") long id, @Param(value = "firstname") String firstname,
                           @Param(value = "lastname") String lastname, @Param(value = "age") Long age,
                           @Param(value = "address") String address, @Param(value = "status") Long status);

    @Query(value = "select c from Application c where c.id = :id")
    Application getId(@Param(value = "id") Long id);
}
