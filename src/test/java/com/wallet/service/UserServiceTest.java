package com.wallet.service;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.wallet.entity.User;
import com.wallet.repository.UserRepository;
import com.wallet.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class UserServiceTest {
	
	
	@InjectMocks
	private UserServiceImpl service;
	
	@Mock
	private UserRepository repository;	
	
	
	@BeforeEach
	void setUp() {		
		Mockito.when(repository.findByEmailEquals(Mockito.anyString())).thenReturn(Optional.of(new User()));
	}
	
	@Test
	void testFindByEmail() {
		Optional<User> user = service.findByEmail("email@test.com");
		
		assertTrue(user.isPresent());		
		
	}

}
