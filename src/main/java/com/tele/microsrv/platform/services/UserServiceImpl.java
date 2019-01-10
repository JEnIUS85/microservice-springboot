package com.tele.microsrv.platform.services;

/*import com.hellokoding.auth.model.User;
import com.hellokoding.auth.repository.RoleRepository;
import com.hellokoding.auth.repository.UserRepository;*/
import com.tele.microsrv.platform.dal.impl.dummy_UserDALImpl;
import com.tele.microsrv.platform.model.Dummy_User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private dummy_UserDALImpl userRepository;

//    @Autowired
//    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(Dummy_User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        //user.setRoles(new HashSet<>(roleRepository.findAll()));
        //userRepository.save(user);
        userRepository.addNewUser(user);
    }

    @Override
    public Dummy_User findByUsername(String username) {
        //return userRepository.findByUsername(username);
        return userRepository.getEmail(username);
    }
}