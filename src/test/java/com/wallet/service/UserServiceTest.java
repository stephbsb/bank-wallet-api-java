package com.wallet.service;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.wallet.entity.User;
import com.wallet.repository.UserRepository;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
class UserServiceTest {
	
	@MockBean
	UserRepository repository;
	
	@Autowired
	UserService service;
	
	@BeforeAll
	public void setUp() {
		System.out.println();
		BDDMockito.given(repository.findByEmailEquals(Mockito.anyString())).willReturn(Optional.of(new User()));
	}
	
	@Test
	public void testFindByEmail() {
		Optional<User> user = service.findByEmail("email@test.com");
		
		assertTrue(user.isPresent());		
		
	}

}
