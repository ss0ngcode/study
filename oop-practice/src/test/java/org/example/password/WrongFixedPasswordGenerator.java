package org.example.password;

public class WrongFixedPasswordGenerator implements PasswordGenerator {
	@Override
	public String generatePassword() {
		return "as";
	}
}
