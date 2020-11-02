package com.eg.controller;

import com.eg.config.JwtTokenUtil;
import com.eg.model.LoginUser;
import com.sun.xml.internal.ws.server.sei.MessageFiller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import com.eg.dao.UserDao;
import com.eg.model.ApiResponse;
import com.eg.model.User;
import com.eg.model.UserDto;
import com.eg.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserDao userdao;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;


    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUp(@RequestBody UserDto udto) throws AuthenticationException {
        User user = userdao.findByUsername(udto.getUsername());
        if(user == null) {
            userService.save(udto);
            return "Done";
        }

        return "This username is already existing, change the username";
    }


    @PostMapping("/login")
    public User getUser(@RequestBody LoginUser loginUser){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        return userService.findOne(loginUser.getUsername());
    }


    @GetMapping("/{id}")
    public ApiResponse<User> getOne(@PathVariable int id){
        return new  ApiResponse<>(200,"Fetcing User",userService.findById(id));
    }

    @PutMapping("/{id}")
    public UserDto update(@RequestBody UserDto userDto) {
        return userService.update(userDto);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
        userService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "User deleted successfully.", null);
    }


    @GetMapping("/auth")
    public ResponseEntity<String> getRole(@RequestHeader("Authorization") String header){
        String username = jwtTokenUtil.getUsernameFromToken(header);
        User user = userdao.findByUsername(username);

        return new ResponseEntity<>(user.getRole(),HttpStatus.OK);
    }




}
