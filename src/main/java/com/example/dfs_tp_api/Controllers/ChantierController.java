package com.example.dfs_tp_api.Controllers;

import com.example.dfs_tp_api.Dao.ChantierDao;
import com.example.dfs_tp_api.Models.Chantier;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ChantierController {
    @Autowired
    ChantierDao chantierDao;

    @GetMapping("/chantiers")
    @JsonView(Chantier.class)
    public List<Chantier> getChantiers() {
        return (List<Chantier>) chantierDao.findAll();
    }

    @GetMapping("/chantier/{id}")
    @JsonView(Chantier.class)
    public ResponseEntity<Chantier> getChantier(Integer id) {
        Optional<Chantier> optionalChantier = chantierDao.findById(id);
        if (optionalChantier.isPresent()) {
            return ResponseEntity.ok(optionalChantier.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/chantier/{nom}")
    @JsonView(Chantier.class)
    public ResponseEntity<Chantier> getByNom(@PathVariable String nom){

        Optional<Chantier> optionalChantier = chantierDao.findByNom(nom);

        if (optionalChantier.isPresent()) {
            return new ResponseEntity<>(optionalChantier.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/chantier/{id}")
    @JsonView(Chantier.class)
    public ResponseEntity<Chantier> deleteChantier(@PathVariable Integer id) {
        Optional<Chantier> optionalChantier = chantierDao.findById(id);

        if (optionalChantier.isPresent()) {
            chantierDao.delete(optionalChantier.get());
            return new ResponseEntity<>(optionalChantier.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/chantier")
    @JsonView(Chantier.class)
    public ResponseEntity<Chantier> createChantier(@RequestBody Chantier chantier) {
        chantierDao.save(chantier);
        return new ResponseEntity<>(chantier, HttpStatus.CREATED);
    }

    @PutMapping("/chantier/{id}")
    @JsonView(Chantier.class)
    public ResponseEntity<Chantier> updateChantier(@PathVariable Integer id, @RequestBody Chantier chantier) {
        Optional<Chantier> optionalChantier = chantierDao.findById(id);

        if (optionalChantier.isPresent()) {
            chantier.setId(id);
            chantierDao.save(chantier);
            return new ResponseEntity<>(chantier, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
