package com.example.dfs_tp_api.Controllers;

import com.example.dfs_tp_api.Dao.ConsommableDao;
import com.example.dfs_tp_api.Models.Consommable;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ConsommableController {
    @Autowired
    ConsommableDao consommableDao;

    @GetMapping("/consommables")
    @JsonView(Consommable.class)
    public List<Consommable> getConsommables() {
        return (List<Consommable>) consommableDao.findAll();
    }

    @GetMapping("/consommable/{id}")
    @JsonView(Consommable.class)
    public Consommable getConsommable(Integer id) {
        return consommableDao.findById(id).orElse(null);
    }

    @GetMapping("/consommable/{nom}")
    @JsonView(Consommable.class)
    public ResponseEntity<Consommable> getByNom(@PathVariable String nom){

        Optional<Consommable> optionalConsommable = consommableDao.findByNom(nom);

        if (optionalConsommable.isPresent()) {
            return new ResponseEntity<>(optionalConsommable.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/consommable/{id}")
    @JsonView(Consommable.class)
    public ResponseEntity<Consommable> deleteConsommable(@PathVariable Integer id) {
        Optional<Consommable> optionalConsommable = consommableDao.findById(id);

        if (optionalConsommable.isPresent()) {
            consommableDao.delete(optionalConsommable.get());
            return new ResponseEntity<>(optionalConsommable.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/consommable")
    @JsonView(Consommable.class)
    public ResponseEntity<Consommable> createConsommable(@RequestBody Consommable consommable) {
        consommableDao.save(consommable);
        return ResponseEntity.ok(consommable);
    }

    @PutMapping("/consommable/{id}")
    @JsonView(Consommable.class)
    public ResponseEntity<Consommable> updateConsommable(@PathVariable Integer id, @RequestBody Consommable consommable) {
        Optional<Consommable> optionalConsommable = consommableDao.findById(id);
        if (optionalConsommable.isPresent()) {
            consommableDao.save(consommable);
            return ResponseEntity.ok(consommable);
        }
        return ResponseEntity.notFound().build();
    }
}
