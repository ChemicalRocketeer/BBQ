package com.aptosstbbq.bbqapp;

public class EncryptedPassword {

	public static final String DEFAULT_WEB_PASS_PATH = "web.pw";

	private EncryptedFile encryptor;

	/**
	 * Used for initial setup of files so that EncryptedPassword can use them. This should only be
	 * used ONCE to set up an admin password for the VERY FIRST time.
	 */
	public static EncryptedPassword firstTimeSetup(String adminPass) {
		EncryptedFile setup = EncryptedFile.firstTimeSetup(adminPass, DEFAULT_WEB_PASS_PATH);
		EncryptedPassword ep = new EncryptedPassword();
		ep.encryptor = setup;
		return ep;
	}

	private EncryptedPassword() {
	}

	public EncryptedPassword(String adminPass) {
		encryptor = new EncryptedFile(adminPass, DEFAULT_WEB_PASS_PATH);
	}

	public boolean isValid() {
		return encryptor.isValid();
	}

	public String decryptPassword() {
		return encryptor.decryptFile();
	}

	public void setPassword(String newPass) {
		encryptor.encryptData(newPass);
	}

	public void setAdminPass(String newPass) {
		encryptor.setAdminPass(newPass);
	}
}
