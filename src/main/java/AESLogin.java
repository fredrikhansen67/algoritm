

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AESLogin {
	private static String pwkey = "This is a s3cr3t"; //16 byte long
	private static File inputFile = new File("C:\\aaa//text.encrypted");
	public AESLogin() {}
	public AESLogin(String pw) {
		AESLogin.pwkey = pw;
	}

	/**
	 * Get the password
	 * @param pw
	 * @return
	 */
	public String doLogin() {
		return getTheSecretPassword();
	}

	
	/**
	 * Retrieves the encrypted password
	 * @return
	 */
	static String getTheSecretPassword() {
		try {
			Key secretKey = new SecretKeySpec(pwkey.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);

			FileInputStream inputStream = new FileInputStream(inputFile);
			byte[] inputBytes = new byte[(int) inputFile.length()];
			inputStream.read(inputBytes);

			byte[] outputBytes = cipher.doFinal(inputBytes);
			inputStream.close();
			return new String(outputBytes);

		} catch (NoSuchPaddingException | NoSuchAlgorithmException 
				| InvalidKeyException | BadPaddingException
				| IllegalBlockSizeException | IOException e) {
			e.printStackTrace();return null;
		}

	}
	
	/**
	 * Generate a new encryption when you need to change the password
	 * @param secPassw
	 */
	public void generateEncryption(String secPassw) {
		try {
			Key secretKey = new SecretKeySpec(pwkey.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);

			byte[] inputBytes = secPassw.getBytes();
			byte[] outputBytes = cipher.doFinal(inputBytes);

			FileOutputStream outputStream = new FileOutputStream(inputFile);
			outputStream.write(outputBytes);
			outputStream.close();

		} catch (NoSuchPaddingException | NoSuchAlgorithmException 
				| InvalidKeyException | BadPaddingException
				| IllegalBlockSizeException | IOException e) {
			e.printStackTrace();
		}
	}
	


}