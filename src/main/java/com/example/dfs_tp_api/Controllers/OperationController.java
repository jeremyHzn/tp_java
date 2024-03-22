package com.example.dfs_tp_api.Controllers;

import com.example.dfs_tp_api.Dao.OperationDao;
import com.example.dfs_tp_api.Models.Operation;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class OperationController {
    @Autowired
    OperationDao operationDao;

    @GetMapping("/operations")
    @JsonView(Operation.class)
    public List<Operation> getOperations() {
        return (List<Operation>) operationDao.findAll();
    }

    @GetMapping("/operation/{id}")
    @JsonView(Operation.class)
    public ResponseEntity<Operation> getOperation(Integer id) {
        Optional<Operation> optionalOperation = operationDao.findById(id);
        if (optionalOperation.isPresent()) {
            return ResponseEntity.ok(optionalOperation.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/operation/{nom}")
    @JsonView(Operation.class)
    public ResponseEntity<Operation> getByNom(String nom){

        Optional<Operation> optionalOperation = operationDao.findByNom(nom);

        if (optionalOperation.isPresent()) {
            return new ResponseEntity<>(optionalOperation.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/operation/{id}")
    @JsonView(Operation.class)
    public ResponseEntity<Operation> deleteOperation(Integer id) {
        Optional<Operation> optionalOperation = operationDao.findById(id);

        if (optionalOperation.isPresent()) {
            operationDao.delete(optionalOperation.get());
            return new ResponseEntity<>(optionalOperation.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/operation")
    @JsonView(Operation.class)
    public ResponseEntity<Operation> createOperation(Operation operation) {
        operationDao.save(operation);
        return new ResponseEntity<>(operation, HttpStatus.OK);
    }

    @PostMapping("/operation/{id}")
    @JsonView(Operation.class)
    public ResponseEntity<Operation> updateOperation(Integer id, Operation operation) {
        Optional<Operation> optionalOperation = operationDao.findById(id);
        if (optionalOperation.isPresent()) {
            operationDao.save(operation);
            return new ResponseEntity<>(operation, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
