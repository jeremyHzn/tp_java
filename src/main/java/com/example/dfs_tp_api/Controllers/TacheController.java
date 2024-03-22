package com.example.dfs_tp_api.Controllers;

import com.example.dfs_tp_api.Dao.TacheDao;
import com.example.dfs_tp_api.Models.Tache;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TacheController {
    @Autowired
    TacheDao tacheDao;

    @GetMapping("/taches")
    @JsonView({Tache.class})
    public List<Tache> getTaches() {
        return (List<Tache>) tacheDao.findAll();
    }

    @GetMapping("/tache/{id}")
    @JsonView(Tache.class)
    public ResponseEntity<Tache> getTache(Integer id) {
        Optional<Tache> optionalTache = tacheDao.findById(id);
        if (optionalTache.isPresent()) {
            return ResponseEntity.ok(optionalTache.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/tache/{nom}")
    @JsonView(Tache.class)
    public ResponseEntity<Tache> getByNom(String nom) {
        Optional<Tache> optionalTache = tacheDao.findByNom(nom);
        if (optionalTache.isPresent()) {
            return ResponseEntity.ok(optionalTache.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/tache/{id}")
    @JsonView(Tache.class)
    public ResponseEntity<Tache> deleteTache(Integer id) {
        Optional<Tache> optionalTache = tacheDao.findById(id);
        if (optionalTache.isPresent()) {
            tacheDao.delete(optionalTache.get());
            return ResponseEntity.ok(optionalTache.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/tache")
    @JsonView(Tache.class)
    public ResponseEntity<Tache> createTache(Tache tache) {
        tacheDao.save(tache);
        return ResponseEntity.ok(tache);
    }

    @PostMapping("/tache/{id}")
    @JsonView(Tache.class)
    public ResponseEntity<Tache> updateTache(@PathVariable Integer id, @RequestBody Tache tache) {
        Optional<Tache> optionalTache = tacheDao.findById(id);
        if (optionalTache.isPresent()) {
            tacheDao.save(tache);
            return ResponseEntity.ok(tache);
        }
        return ResponseEntity.notFound().build();
    }
}
