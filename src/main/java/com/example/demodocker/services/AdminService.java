package com.example.demodocker.services;

import com.example.demodocker.entity.Interest;
import com.example.demodocker.entity.Role;
import com.example.demodocker.exceptions.InterestAlreadyPresentException;
import com.example.demodocker.exceptions.RoleAlreadyPresentException;
import com.example.demodocker.repositories.InterestRepository;
import com.example.demodocker.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired private RoleRepository roleRepository;
    @Autowired private InterestRepository interestRepository;

    public List<Role> listAllRoles(){
        return roleRepository.findAll();
    }
    public List<Interest> listAllInterests(){
        return interestRepository.findAll();
    }
    public Role addRole(Role role){
        Role checkIfRolePresent = roleRepository.findByName(role.getName());
        if(checkIfRolePresent != null){
            throw new RoleAlreadyPresentException("Role with name: "+role.getName()+" already present.");
        }
        return roleRepository.save(role);
    }
    public Interest addInterest(Interest interest){
        Interest checkIfInterestPresent = interestRepository.findByName(interest.getInterestArea());
        if(checkIfInterestPresent != null){
            throw new InterestAlreadyPresentException("Interest Area with name: "+interest.getInterestArea()+" already present.");
        }
        return interestRepository.save(interest);
    }
}
