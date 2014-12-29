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
		// only used internally by EncryptedPassword
	}

	public EncryptedPassword(String adminPass) {
		encryptor = new EncryptedFile(adminPass, DEFAULT_WEB_PASS_PATH);
	}

	/** Returns true if this EncryptedFile was initialized with a valid password */
	public boolean isValid() {
		return encryptor.isValid();
	}

	/**
	 * Returns true if the file containing the encrypted admin password exists. If this returns
	 * false, you may need to call {@link #firstTimeSetup(String)}.
	 */
	public boolean adminPassPathExists() {
		return encryptor.adminPassPathExists();
	}

	/**
	 * Returns null if the password given at initialization was invalid, or firstTimeSetup has not
	 * been called.
	 */
	public String decryptPassword() {
		return encryptor.decryptFile();
	}

	public void setPassword(String newPass) {
		encryptor.encryptData(newPass);
	}

	public void setAdminPass(String newPass) {
		encryptor.setAdminPass(newPass);
	}

	public static String getSoldOutFTPPass() {
		return new EncryptedFile("File_Transfer_Protocol_JSON_Menu_4.txt", "sold_out.pw", "sold_out_admin.pw").decryptFile();
	}

	public static void setSoldOutFTPPass(String pass) {
		new EncryptedFile("File_Transfer_Protocol_JSON_Menu_4.txt", "sold_out.pw", "sold_out_admin.pw").encryptData(pass);
	}

	public static void setSoldOutFTPPassFirstTime(String pass) {
		EncryptedFile.firstTimeSetup("File_Transfer_Protocol_JSON_Menu_4.txt", "sold_out.pw", "sold_out_admin.pw").encryptData(pass);
	}
}
