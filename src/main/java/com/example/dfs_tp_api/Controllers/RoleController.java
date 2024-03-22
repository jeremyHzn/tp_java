package com.example.dfs_tp_api.Controllers;

import com.example.dfs_tp_api.Dao.RoleDao;
import com.example.dfs_tp_api.Models.Role;
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
public class RoleController {
    @Autowired
    RoleDao roleDao;

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return (List<Role>) roleDao.findAll();
    }
    @GetMapping("/role/{id}")
    public Role getRole(Integer id) {
        return roleDao.findById(id).orElse(null);
    }

    @GetMapping("/role/{designation}")
    public ResponseEntity<Role> getByDesignation(String designation){
        Optional<Role> optionalRole = roleDao.findByDesignation(designation);
        if (optionalRole.isPresent()) {
            return new ResponseEntity<>(optionalRole.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/role/{id}")
    public ResponseEntity<Role> deleteRole(Integer id) {
        Optional<Role> optionalRole = roleDao.findById(id);
        if (optionalRole.isPresent()) {
            roleDao.delete(optionalRole.get());
            return new ResponseEntity<>(optionalRole.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/role")
    public ResponseEntity<Role> createRole(Role role) {
        roleDao.save(role);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping("/role/{id}")
    public ResponseEntity<Role> updateRole(Integer id, Role role) {
        Optional<Role> optionalRole = roleDao.findById(id);
        if (optionalRole.isPresent()) {
            roleDao.save(role);
            return new ResponseEntity<>(role, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
