package com.aptosstbbq.bbqapp;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;

/**
 * An EncryptedFile uses an admin password to access data. The admin password can also be used to
 * allow access to a settings menu. EncryptedFile objects assume that any necessary files will be
 * available. The user must make sure beforehand that the required files exist.
 */
public class EncryptedFile {

	public static final String DEFAULT_ADMIN_PASS_PATH = "admin.pw";

	private final String adminPassDecr;
	private boolean valid;
	public final String DATA_PATH;
	public final String ADMIN_PASS_PATH;

	/**
	 * Used for initial setup of files so that EncryptedFile can use them. This should only be used
	 * ONCE to set up an admin password for the VERY FIRST time.
	 */
	public static EncryptedFile firstTimeSetup(String adminPass, String dataPath, String adminPassPath) {
		Utils.writeFile(adminPassPath, new BasicPasswordEncryptor().encryptPassword(adminPass));
		return new EncryptedFile(adminPass, dataPath, adminPassPath);
	}

	/**
	 * Used for initial setup of files so that EncryptedFile can use them. This should only be used
	 * ONCE to set up the admin password for the VERY FIRST time.
	 */
	public static EncryptedFile firstTimeSetup(String adminPass, String dataPath) {
		return firstTimeSetup(adminPass, dataPath, DEFAULT_ADMIN_PASS_PATH);
	}

	/**
	 * If the adminPass is an invalid password, this EncryptedFile will be marked as invalid and all
	 * its member functions will do nothing.
	 */
	public EncryptedFile(String adminPass, String dataPath) {
		this(adminPass, dataPath, "admin.pw");
	}

	/**
	 * If the adminPass is an invalid password, this EncryptedFile will be marked as invalid and all
	 * its member functions will do nothing.
	 */
	public EncryptedFile(String adminPass, String dataPath, String adminPassPath) {
		adminPassDecr = adminPass;
		ADMIN_PASS_PATH = adminPassPath;
		DATA_PATH = dataPath;
		// check the password
		String adminPassEncr = Utils.readFile(ADMIN_PASS_PATH);
		valid = new BasicPasswordEncryptor().checkPassword(adminPass, adminPassEncr);
	}

	/** Returns true if this EncryptedFile was initialized with a valid password */
	public boolean isValid() {
		return valid;
	}

	/**
	 * Returns null if the password given at initialization was invalid.
	 */
	public String decryptFile() {
		return decryptFile(DATA_PATH);
	}

	public void encryptData(String data) {
		encryptFile(DATA_PATH, data);
	}

	/**
	 * Returns null if the password given at initialization was invalid.
	 */
	public String decryptFile(String encryptedFile) {
		if (!valid)
			return null;
		String encr = Utils.readFile(encryptedFile);
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
		encryptor.setPassword(adminPassDecr);
		return encryptor.decrypt(encr);
	}

	public void encryptFile(String path, String data) {
		if (!valid)
			return;
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
		encryptor.setPassword(adminPassDecr);
		Utils.writeFile(path, encryptor.encrypt(data));
	}

	/**
	 * Sets a new admin password, and re-encrypts the data file. This EncryptedFile will be
	 * invalidated and a new one will need to be created which uses the new password.
	 */
	public void setAdminPass(String newAdminPass) {
		if (!valid)
			return;
		// decrypt existing data
		String data = this.decryptFile();
		// re-encrypt data with new password
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
		encryptor.setPassword(newAdminPass);
		Utils.writeFile(DATA_PATH, encryptor.encrypt(data));
		// set new password in the admin password file
		BasicPasswordEncryptor passEncr = new BasicPasswordEncryptor();
		ThreadedWriter.write(ADMIN_PASS_PATH, passEncr.encryptPassword(newAdminPass));
		// make sure this EncryptedFile can't be used anymore.
		valid = false;
	}
}
