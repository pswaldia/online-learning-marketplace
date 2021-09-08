package com.example.demodocker.controllers;

import com.example.demodocker.entity.Interest;
import com.example.demodocker.entity.Role;
import com.example.demodocker.services.AdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired private AdminService adminService;

    @GetMapping("/admin/roles")
    @ApiOperation(
            value = "Get all the roles associated with the website users."
    )
    public ResponseEntity<List<Role>> listAllRoles(){
        return new ResponseEntity<List<Role>>(adminService.listAllRoles(), HttpStatus.OK);
    }


    @GetMapping("/admin/interests")
    @ApiOperation(
            value = "Get all the interest/category associated with the website users."
    )
    public ResponseEntity<List<Interest>> listAllInterests(){
        return new ResponseEntity<List<Interest>>(adminService.listAllInterests(), HttpStatus.OK);
    }

    @PostMapping("/admin/roles/add")
    @ApiOperation(
            value = "Add given role to the list of roles.",
            notes = "The API can be used by admins to add roles that can be associated to different users.",
            response = Role.class
    )
    public Role addNewRole(@RequestBody Role role){
        return adminService.addRole(role);
    }

    @PostMapping("/admin/interests/add")
    @ApiOperation(
            value = "Add given interest to the list of roles.",
            notes = "The API can be used by admins to add interest area that can be associated to different users.",
            response = Interest.class
    )
    public Interest addNewInterest(@RequestBody Interest interest){
        return adminService.addInterest(interest);
    }


}
