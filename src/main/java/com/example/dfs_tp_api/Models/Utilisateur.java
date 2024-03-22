package com.example.dfs_tp_api.Models;

import com.example.dfs_tp_api.Views.ChantierView;
import com.example.dfs_tp_api.Views.UtilisateurView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(UtilisateurView.class)
    protected Integer id;

    @JsonView(ChantierView.class)
    protected String pseudo;
    protected String motDePasse;

    @ManyToOne(optional = false)
    protected Role role;

    @OneToMany(mappedBy = "utilisateur")
    @JsonView(UtilisateurView.class)
    protected List<Chantier> chantiers = new ArrayList<>();
}
