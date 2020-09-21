package com.wallet.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.wallet.entity.User;

import lombok.Data;

/**
 * 
 * @author stephany
 *
 */
@Data
public class UserDTO {
	
	private Long id;
	@Email(message="Email Inválido.")
	private String email;
	@Length(min=3,max=50,message="O nome deve conter entre 3 e 50 caracteres.")
	private String name;
	@NotNull
	@Length(min=6,message="A senha deve conter no minimo 6 caracteres")
	private String password;


	public static User fromDTO(UserDTO dto) {
		User u = new User();
		u.setId(dto.getId());
		u.setEmail(dto.getEmail());
		u.setName(dto.getName());
		u.setPassword(dto.getPassword());
		
		return u;
	}
	
	
	public static UserDTO toDTO(User u) {
		UserDTO dto = new UserDTO();
		dto.setId(u.getId());
		dto.setEmail(u.getEmail());
		dto.setName(u.getName());
		dto.setPassword(u.getPassword());
		
		return dto;
	}
	
}
