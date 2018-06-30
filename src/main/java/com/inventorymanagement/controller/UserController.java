package com.inventorymanagement.controller;

import com.inventorymanagement.dao.UserDao;
import com.inventorymanagement.model.db.User;
import com.inventorymanagement.model.ui.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created by sampathkatari on 18/06/18.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    static final Base64.Encoder ENCODER = Base64.getEncoder();
    @Autowired
    private UserDao userDao;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(HttpServletRequest request, @RequestBody UserDto userDto) {
        User byUsername = userDao.findByUsername(userDto.getUsername());
        if(byUsername == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if(byUsername.getPassword().equals(userDto.getPassword())){
            HttpSession session = request.getSession();
            session.setAttribute("username", byUsername.getUserName());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("username", null);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/ping", method = RequestMethod.POST)
    public ResponseEntity<?> checkSession(HttpServletRequest request) {
        if(request.getSession().getAttribute("username") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok().build();
    }

}
