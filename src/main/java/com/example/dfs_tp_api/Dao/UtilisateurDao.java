package com.example.dfs_tp_api.Dao;

import com.example.dfs_tp_api.Models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findByPseudo(String pseudo);
}
