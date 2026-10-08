package com.brayan_guilherme.repositories;

import com.brayan_guilherme.models.Sapz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SapzRepository extends JpaRepository<Sapz, Long> {

    List<Sapz> findByUser_Id(Long id);

}