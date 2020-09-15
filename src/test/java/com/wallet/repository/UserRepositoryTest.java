package com.wallet.repository;



import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.wallet.entity.User;


@DataJpaTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
public class UserRepositoryTest {
	
	private static final String EMAIL = "email@test.com";
	
	@Autowired
	UserRepository repository;

	@BeforeAll
	public void setUp() {
		User u = new User();
		u.setName("Set Up User");
		u.setPassword("Senha123");
		u.setEmail(EMAIL);
		
		repository.save(u);
		System.out.println("Before");
	}
	
	
	@AfterAll
	public void tearDown() {
		repository.deleteAll();
		System.out.println("After");
	}
	
	@Test
	public void testSave() {
		User u = new User();
		u.setName("Test user");
		u.setPassword("123456");
		u.setEmail("test@test.com");
		
		User response = repository.save(u);
		
		
		System.out.println(response);
		
		assertNotNull(response);
	}
	
	@Test
	public void testFindByEmail() {
				
		Optional<User> response = repository.findByEmailEquals(EMAIL);
		
		assertTrue(response.isPresent());
		assertEquals(response.get().getEmail(), EMAIL);
	}

}
