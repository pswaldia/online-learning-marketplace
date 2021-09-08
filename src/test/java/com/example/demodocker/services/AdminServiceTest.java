package com.example.demodocker.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demodocker.entity.Interest;
import com.example.demodocker.entity.Role;
import com.example.demodocker.exceptions.InterestAlreadyPresentException;
import com.example.demodocker.exceptions.RoleAlreadyPresentException;
import com.example.demodocker.repositories.InterestRepository;
import com.example.demodocker.repositories.RoleRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AdminService.class})
@ExtendWith(SpringExtension.class)
public class AdminServiceTest {
    @Autowired
    private AdminService adminService;

    @MockBean
    private InterestRepository interestRepository;

    @MockBean
    private RoleRepository roleRepository;

    @Test
    public void testListAllRoles() {
        ArrayList<Role> roleList = new ArrayList<Role>();
        when(this.roleRepository.findAll()).thenReturn(roleList);
        List<Role> actualListAllRolesResult = this.adminService.listAllRoles();
        assertSame(roleList, actualListAllRolesResult);
        assertTrue(actualListAllRolesResult.isEmpty());
        verify(this.roleRepository).findAll();
    }

    @Test
    public void testListAllInterests() {
        ArrayList<Interest> interestList = new ArrayList<Interest>();
        when(this.interestRepository.findAll()).thenReturn(interestList);
        List<Interest> actualListAllInterestsResult = this.adminService.listAllInterests();
        assertSame(interestList, actualListAllInterestsResult);
        assertTrue(actualListAllInterestsResult.isEmpty());
        verify(this.interestRepository).findAll();
    }

    @Test
    public void testAddRole() {
        Role role = new Role();
        role.setId(1);
        role.setName("Name");
        role.setDescription("Name is missing......");
        when(this.roleRepository.findByName(anyString())).thenReturn(role);

        Role role1 = new Role();
        role1.setId(1);
        role1.setName("Name");
        role1.setDescription("Name is missing......");
        assertThrows(RoleAlreadyPresentException.class, () -> this.adminService.addRole(role1));
        verify(this.roleRepository).findByName(anyString());
    }

    @Test
    public void testAddInterest() {
        Interest interest = new Interest();
        interest.setId(1);
        interest.setInterestArea("Interest Area");
        when(this.interestRepository.findByName(anyString())).thenReturn(interest);

        Interest interest1 = new Interest();
        interest1.setId(1);
        interest1.setInterestArea("Interest Area");
        assertThrows(InterestAlreadyPresentException.class, () -> this.adminService.addInterest(interest1));
        verify(this.interestRepository).findByName(anyString());
    }
}

