package com.example.dfs_tp_api.Dao;

import com.example.dfs_tp_api.Models.Chantier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChantierDao extends JpaRepository<Chantier, Integer>{
    Optional<Chantier> findByNom(String nom);
}
