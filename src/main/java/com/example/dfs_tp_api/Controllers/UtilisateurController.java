package com.example.dfs_tp_api.Controllers;

import com.example.dfs_tp_api.Dao.UtilisateurDao;
import com.example.dfs_tp_api.Models.Role;
import com.example.dfs_tp_api.Models.Utilisateur;
import com.example.dfs_tp_api.Security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UtilisateurController {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    UtilisateurDao utilisateurDao;

    @PostMapping("/sign-in")
    public void signIn(@RequestBody Utilisateur user) {
        Role role = new Role();
        role.setId(4);
        user.setRole(role);

        user.setMotDePasse(bCryptPasswordEncoder.encode(user.getMotDePasse()));

        utilisateurDao.save(user);

    }

    @PostMapping("/login")
    public String login(@RequestBody Utilisateur utilisateur) {

        try {

            UserDetails userDetails = (UserDetails) authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                    utilisateur.getPseudo(),
                    utilisateur.getMotDePasse()
            )).getPrincipal();

            return jwtUtils.generateJwt(userDetails);

        } catch (Exception ex) {
            return "Pseudo ou mot de passe incorrect";
        }
    }

    @PutMapping("/utilisateur/{id}")
    public ResponseEntity<Utilisateur> updateProfile(@RequestBody Utilisateur id, @RequestBody Utilisateur utilisateur) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findById(id.getId());

        if (optionalUtilisateur.isPresent()) {
            utilisateur.setPseudo(utilisateur.getPseudo());
            utilisateur.setMotDePasse(bCryptPasswordEncoder.encode(utilisateur.getMotDePasse()));
            utilisateur.setRole(utilisateur.getRole());

            utilisateurDao.save(utilisateur);

            return new ResponseEntity<>(utilisateur, HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/utilisateur/{id}")
    public ResponseEntity<Utilisateur> deleteUtilisateur(@PathVariable Integer id) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findById(id);

        if (optionalUtilisateur.isPresent()) {
            utilisateurDao.delete(optionalUtilisateur.get());
            return new ResponseEntity<>(optionalUtilisateur.get(), HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }

    @Autowired
    JwtUtils jwtUtils;
}
