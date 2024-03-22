package com.example.dfs_tp_api.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @Length(min= 3, max = 255, message = "L'opération doit avoir entre 3 et 255 caractères")
    @NotNull(message = "Le nom de l'opération est obligatoire")
    protected String nom;

    protected LocalDate date;

    @ManyToOne(optional = false)
    protected Chantier chantier;

    @ManyToOne(optional = false)
    protected Utilisateur ouvrier;

    @ManyToOne(optional = false)
    protected Tache tache;




}
