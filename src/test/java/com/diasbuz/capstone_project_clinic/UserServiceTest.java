package com.diasbuz.capstone_project_clinic;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.diasbuz.capstone_project_clinic.model.User;
import com.diasbuz.capstone_project_clinic.repository.UserRepository;
import com.diasbuz.capstone_project_clinic.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setUserId(1);
        user.setLogin("testuser");
        user.setPassword("password");
        user.setBalance(BigDecimal.valueOf(100.00));
    }

    @Test
    public void testFindByLogin() {
        when(userRepository.findByLogin("testuser")).thenReturn(user);

        User foundUser = userService.findByLogin("testuser");

        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getLogin());
    }

    @Test
    public void testTopUpBalance() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        userService.topUpBalance(1, BigDecimal.valueOf(50.00));

        assertEquals(BigDecimal.valueOf(150.00), user.getBalance());
    }

    @Test
    public void testUpdateUserInformation() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        userService.updateUserInformation(1, "newName", "newPhone", "newEmail");

        assertEquals("newName", user.getName());
        assertEquals("newPhone", user.getPhone());
        assertEquals("newEmail", user.getEmail());
    }
}