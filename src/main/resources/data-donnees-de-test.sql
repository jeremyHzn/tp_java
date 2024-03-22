INSERT INTO role (designation)
VALUES ("admin"),
       ("Chef de chantier"),
       ("Ouvrier"),
       ("Utilisateur");

INSERT INTO utilisateur (pseudo, mot_de_passe, role_id)
VALUES ("jérémy", "$2a$10$JPMO7rPShGPb/Nwf/JOcM.qwf464i4j9TAf8vDPHn3yKFdJwXpvgi", 1),
       ("Jacques", "2a$10$OT6TgAfVBuYuxKEsq4sbGO3n0jyG5s0rMVf39CQgmxLbK9YOMeFdC", 2),
       ("Jean", "$2a$10$JPMO7rPShGPb/Nwf/JOcM.qwf464i4j9TAf8vDPHn3yKFdJwXpvgi", 3),
       ("Jules", "$2a$10$JPMO7rPShGPb/Nwf/JOcM.qwf464i4j9TAf8vDPHn3yKFdJwXpvgi", 4);

INSERT INTO chantier (nom, adresse, utilisateur_id)
VALUES ("Chantier 48654", "Zac oni", 2),
       ("Chantier 946853", "Paris", 3);

INSERT INTO tache (nom, temps)
VALUES ("peindre", 3),
       ("creuser", 5),
       ("forer", 10);

INSERT INTO consommable (nom)
VALUES ("Pelle"),
       ("Perceuse"),
       ("Peinture"),
       ("Pinceau"),
       ("Marteau");

INSERT INTO operation (nom, date, chantier_id, ouvrier_id, tache_id)
VALUES ("486548", "2008-04-08", 2, 2, 1),
       ("86452185", "2020-12-13", 1, 1, 2);

INSERT INTO tache_consommable (tache_id, consommable_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (3, 4),
       (3, 5);