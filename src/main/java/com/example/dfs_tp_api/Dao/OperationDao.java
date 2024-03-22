package com.example.dfs_tp_api.Dao;

import com.example.dfs_tp_api.Models.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OperationDao extends JpaRepository<Operation, Integer>{
    Optional<Operation> findByNom(String nom);

}
