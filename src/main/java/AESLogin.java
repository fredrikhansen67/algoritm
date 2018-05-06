

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
	private static String pwkey = "This is a s3cr3t";
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
	
//	public static void fileProcessor(int cipherMode,String key,File inputFile,File outputFile){
//		System.out.println("fileprocessor");
//		try {
//			Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
//			Cipher cipher = Cipher.getInstance("AES");
//			cipher.init(cipherMode, secretKey);
//
//			FileInputStream inputStream = new FileInputStream(inputFile);
//			byte[] inputBytes = new byte[(int) inputFile.length()];
//			inputStream.read(inputBytes);
//
//			byte[] outputBytes = cipher.doFinal(inputBytes);
//
//			FileOutputStream outputStream = new FileOutputStream(outputFile);
//			outputStream.write(outputBytes);
//			System.out.println("BANG "+new String(outputBytes));
//
//			inputStream.close();
//			outputStream.close();
//
//		} catch (NoSuchPaddingException | NoSuchAlgorithmException 
//				| InvalidKeyException | BadPaddingException
//				| IllegalBlockSizeException | IOException e) {
//			e.printStackTrace();
//		}
//	}

//	public static void main(String[] args) {
//		String key = "This is a secret";
////			File inputFile = new File("C:\\aaa//a_sec_asectext.txt");
//		File encryptedFile = new File("C:\\aaa//text.encrypted");
//		File decryptedFile = new File("C:\\aaa//decrypted-text.txt");
//
//		try {
////			AESLogin.fileProcessor(Cipher.ENCRYPT_MODE,key,inputFile,encryptedFile);
//			AESLogin.fileProcessor(Cipher.DECRYPT_MODE,key,encryptedFile,decryptedFile);
//			
//			System.out.println("Sucess");
//		} catch (Exception ex) {
//			System.out.println(ex.getMessage());
//			ex.printStackTrace();
//		}
//	}

}