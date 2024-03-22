package com.example.dfs_tp_api.Security;

import com.example.dfs_tp_api.Dao.UtilisateurDao;
import com.example.dfs_tp_api.Models.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    UtilisateurDao utilisateurDao;

    @Override
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {

        Optional<Utilisateur> optionalUser = utilisateurDao.findByPseudo(pseudo);

        if(optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("Pseudo introuvable");
        }

        return new AppUtilisateurDetail(optionalUser.get());
    }
}
