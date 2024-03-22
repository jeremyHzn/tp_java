package com.example.dfs_tp_api.Dao;

import com.example.dfs_tp_api.Models.Consommable;
import com.example.dfs_tp_api.Models.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsommableDao extends JpaRepository<Consommable, Integer>{
    Optional<Consommable> findByNom(String nom);
}
