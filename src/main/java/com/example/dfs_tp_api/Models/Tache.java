package com.example.dfs_tp_api.Models;

import com.example.dfs_tp_api.Views.ChantierView;
import com.example.dfs_tp_api.Views.TacheView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @Length(min= 3, max = 255, message = "La tâche doit avoir entre 3 et 255 caractères")
    @JsonView({TacheView.class, ChantierView.class})
    protected String nom;
    protected int temps;

    @ManyToMany()
    @JoinTable(name = "tache_consommable",
            joinColumns = @JoinColumn(name = "tache_id"),
            inverseJoinColumns = @JoinColumn(name = "consommable_id"))
    protected List<Consommable> consommables = new ArrayList<>();
}
