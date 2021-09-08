package com.example.demodocker.services;

import com.example.demodocker.entity.User;
import com.example.demodocker.enums.Experience;
import com.example.demodocker.enums.Profession;
import com.example.demodocker.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    void listAll() {
        // when
        userService.listAll();
        // then
        verify(userRepository).findAll();
    }

    @Test
    void save() {
        // when
        User user = new User("pswaldia1@gmail.com", "Pradeep", "Singh", "Password01", "A strong engineering professional from UIET Panjab University. Now an SDE @Hashedin", Experience.ZERO_TWO, Profession.PROFESSIONAL);
        userService.save(user);
        //then
        verify(userRepository).save(user);
    }

    @Test
    void update() {
        // when
        User user = new User(1L, "pswaldia1@gmail.com", "Pradeep", "Singh", "Password01", "A strong engineering professional from UIET Panjab University. Now an SDE @Hashedin", Experience.ZERO_TWO, Profession.PROFESSIONAL);
        User userUpdated = new User("pswaldia1@gmail.com", "Pradeep", "Singh", "Password02", "A strong engineering professional from UIET Panjab University. Now an SDE @Hashedin", Experience.ZERO_TWO, Profession.PROFESSIONAL);
        userUpdated.setId(1L);
        userService.update(user);
        // then
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(argumentCaptor.capture());
    }

    @Test
    void uploadImage() {
        User user = new User(1L, "pswaldia1@gmail.com", "Pradeep", "Singh", "Password01", "A strong engineering professional from UIET Panjab University. Now an SDE @Hashedin", Experience.ZERO_TWO, Profession.PROFESSIONAL);
        userService.uploadImage("main.png", 1L);
        verify(userRepository).uploadImage("main.png", 1L);
    }

}