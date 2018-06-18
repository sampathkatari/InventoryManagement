package com.inventorymanagement.controller;

import com.inventorymanagement.dao.UserDao;
import com.inventorymanagement.model.db.User;
import com.inventorymanagement.model.ui.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * Created by sampathkatari on 18/06/18.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

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
    public ResponseEntity<?> create() {

        return ResponseEntity.ok().build();
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
