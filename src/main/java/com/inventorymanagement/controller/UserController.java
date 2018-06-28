package com.inventorymanagement.controller;

import com.inventorymanagement.dao.UserDao;
import com.inventorymanagement.model.db.User;
import com.inventorymanagement.model.ui.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable("id")final String id) {
        final User user = userDao.getOne(Integer.parseInt(id));
        return ResponseEntity.ok(UserDto.newBuilder().firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dob(user.getDob())
                .gender(user.getGender())
                .id(user.getId())
                .userName(user.getUserName())
                .build());
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> signUp(@RequestBody final UserDto userDto) throws NoSuchAlgorithmException {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setDob(userDto.getDob());
        user.setGender(userDto.getGender());
        user.setPassword(hashedPassword(userDto.getPassword()));
        return ResponseEntity.ok().build();
    }

    private String hashedPassword(final String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return encodeByteToString(digest);
    }

    private String encodeByteToString(final byte[] input) {
        return ENCODER.encodeToString(input);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<?> update() {

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete() {

        return ResponseEntity.ok().build();
    }

}
