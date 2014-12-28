package org;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;

public class Password implements StringEncryptor{

	@Override
	public String encrypt(String message) {
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		String encryptedPassword = passwordEncryptor.encryptPassword(message);
		return encryptedPassword;
	}
	@Override
	public String decrypt(String encryptedMessage) {
		
		return null;
	}

}
