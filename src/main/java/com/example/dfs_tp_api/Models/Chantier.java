package com.example.dfs_tp_api.Models;

import com.example.dfs_tp_api.Views.ChantierView;
import com.example.dfs_tp_api.Views.UtilisateurView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;
@Getter
@Setter
@Entity
public class Chantier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @JsonView({ChantierView.class})
    protected String nom;
    @JsonView({ChantierView.class})
    protected String adresse;

    // un chantier est dirigé par un ouvrier (un utilisateur)
    @ManyToOne(optional = false)
    @JsonView({ChantierView.class, UtilisateurView.class})
    protected Utilisateur utilisateur;
}
