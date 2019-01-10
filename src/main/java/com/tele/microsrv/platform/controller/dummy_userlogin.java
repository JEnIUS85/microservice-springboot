package com.tele.microsrv.platform.controller;

import com.tele.microsrv.platform.dal.impl.dummy_UserDALImpl;
import com.tele.microsrv.platform.dao.Dummy_UserRepository;
import com.tele.microsrv.platform.dto.ResponseGenerator;
import com.tele.microsrv.platform.model.Dummy_LoginUser;
import com.tele.microsrv.platform.model.Dummy_User;
import com.tele.microsrv.platform.security.JwtAuthenticationResponse;
import com.tele.microsrv.platform.security.JwtConfig;
import com.tele.microsrv.platform.services.UserDetailsServiceImpl;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(value = "/")
public class dummy_userlogin {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private JwtConfig jwtTokenUtil = null;//new JwtConfig();

   @Autowired
    Dummy_UserRepository userRepository;

   @Autowired
   dummy_UserDALImpl userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsServiceImpl  userDetailsService;

   /* @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;*/

   /*@Autowired
    ResponseGenerator response;*/

    public dummy_userlogin(Dummy_UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "dummy_user_login/getAll", method = RequestMethod.GET)
    public List<Dummy_User> getAllUsers() {
        LOG.info("Getting all users.");
        return userRepository.findAll();
    }

    @RequestMapping(value = "dummy_user_login/{userId}", method = RequestMethod.GET)
    public Dummy_User getUser(@PathVariable String userId) {
        LOG.info("Getting user with ID: {}.", userId);
         Optional<Dummy_User> usr = userRepository.findById(userId);
         return usr.orElse(null);
    }
    /*@RequestMapping(value = "dummy_user_login/create", method = RequestMethod.POST)
    public ResponseGenerator addNewUsers(@Valid @RequestBody Dummy_User user) {
        //return getRegistrationResponse(userRepository.save(user));

        ResponseGenerator response = new ResponseGenerator();
        if(userRepo.getEmail(user.getEmail()) ==null) {
            try {
                LOG.info("Saving user.");
                userRepository.save(user);
                response.setSucess_message("Registration form submitted successfully..");
                return response;
            }catch(Exception e){
                response.setError_message("Failed to save Registration form");
                return  response;
            }
        }
        response.setError_message("Given User Name Already Exists");
        return response;
    }*/



    @RequestMapping(value = "dummy_user_login/create", method = RequestMethod.POST)
    public ResponseEntity<ResponseGenerator> addNewUsers(@RequestBody Dummy_User user) {
        //return getRegistrationResponse(userRepository.save(user));

        ResponseGenerator response = new ResponseGenerator();
        if(userRepo.getEmail(user.getEmail()) ==null) {
            try {
                LOG.info("Saving user.");
                //encode the password received from request..
                user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
                userRepository.save(user);
                response = generatorResponse(response,"/dummy_user_login/create","Registration form submitted successfully","200");
                //response.setSucess_message("Registration form submitted successfully..");
                return new ResponseEntity<ResponseGenerator>(response, HttpStatus.OK);
            }catch(Exception e){
                response=generatorResponse(response,"/dummy_user_login/create","Failed to save Registration form","500");
                response.setError("Internal Server Error");
                //response.setError_message("Failed to save Registration form");
                return new ResponseEntity<ResponseGenerator>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        response = generatorResponse(response,"/dummy_user_login/create","Given User Name Already Exists","202");

        //response.setError_message("Given User Name Already Exists");
        return new ResponseEntity<ResponseGenerator>(response, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateUser(@RequestBody Dummy_LoginUser user)  {
        //return getRegistrationResponse(userRepository.save(user));
        ResponseGenerator response = new ResponseGenerator();
        try {
            authenticate(user.getEmail(), user.getPassword());
        } catch(Exception e){
            response=generatorResponse(response,"/login","Bad Credentials","401");
            /*response.setMessage("Bad Credentials");
            response.setStatus("401");
            response.setPath("/login");*/
            response.setError("Unauthorized");

            //return new ResponseEntity<ResponseGenerator>(response, HttpStatus.UNAUTHORIZED);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        // Reload password post-security so we can generate the token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        jwtTokenUtil = new JwtConfig();
        final String token = jwtTokenUtil.generateToken(userDetails);

        // Return the token
        //return ResponseEntity.ok(new JwtAuthenticationResponse(token));
        response = generatorResponse(response,"/login","Authenticated Successful","200");
        /*response.setStatus("200");
        response.setPath("/login");*/
        response.setToken(token);
        return ResponseEntity.ok(response);
    }


    private String getRegistrationResponse(Dummy_User user){
        String response = null;
        if(user !=null){
            response="{'message': 'Registration form submitted successfully..'}";
        }
        return response;
    }


    @RequestMapping(value = "/auth/**", method = RequestMethod.GET)
    public String getDummyResponse() {
            return "Hello";
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    /**
     * Authenticates the user. If something is wrong, an {@link AuthenticationException} will be thrown
     */
    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("User is disabled!", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Bad credentials!", e);
        }
    }


    private ResponseGenerator generatorResponse(ResponseGenerator response, String path, String message,String Status){
        response.setPath(path);
        response.setMessage(message);
        response.setStatus(Status);
        return response;
    }

}
