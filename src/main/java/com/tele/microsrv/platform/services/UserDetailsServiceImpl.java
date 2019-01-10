package com.tele.microsrv.platform.services;

//import com.hellokoding.auth.model.Role;
//import com.hellokoding.auth.model.User;
//import com.hellokoding.auth.repository.UserRepository;
import com.tele.microsrv.platform.dal.impl.dummy_UserDALImpl;
import com.tele.microsrv.platform.model.Dummy_User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private dummy_UserDALImpl userRepository;

   /* @Autowired
    private BCryptPasswordEncoder encoder;
*/
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Dummy_User user = userRepository.getEmail(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        for (Role role : user.getRoles()){
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//        }

        //Hard code ROLE as USER for every call for now..
        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));

        if(username.equalsIgnoreCase(user.getEmail())) {
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
        }

        // If user not found. Throw this exception.
        throw new UsernameNotFoundException("Username: " + username + " not found");
    }
}
