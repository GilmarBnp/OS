package com.os.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.os.model.OS;

@Repository
public interface OsRepository extends JpaRepository<OS, Integer> {

}
