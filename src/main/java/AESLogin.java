

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
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
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = generateIV();

			cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

			FileInputStream inputStream = new FileInputStream(inputFile);
			byte[] inputBytes = new byte[(int) inputFile.length()];
			inputStream.read(inputBytes);

			byte[] outputBytes = cipher.doFinal(inputBytes);
			inputStream.close();
			return new String(outputBytes);

		} catch (NoSuchPaddingException | NoSuchAlgorithmException 
				| InvalidKeyException | BadPaddingException
				| IllegalBlockSizeException | IOException | InvalidAlgorithmParameterException e) {
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
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = generateIV();
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

			byte[] inputBytes = secPassw.getBytes();
			byte[] outputBytes = cipher.doFinal(inputBytes);

			FileOutputStream outputStream = new FileOutputStream(inputFile);
			outputStream.write(outputBytes);
			outputStream.close();

		} catch (NoSuchPaddingException | NoSuchAlgorithmException 
				| InvalidKeyException | BadPaddingException
				| IllegalBlockSizeException | IOException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
	}
	
	private static IvParameterSpec generateIV() {
		int ivSize = 16;
        byte[] iv = new byte[ivSize];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec IV = new IvParameterSpec(iv);
		        
        return IV;
    }
	
	 public void generateKey() throws Exception {
		 Scanner scan = new Scanner(System.in);
	 
	        try {
	            KeyGenerator gen = KeyGenerator.getInstance("AES");
	            gen.init(256);

	            SecretKey key = gen.generateKey();
	            byte[] keyBytes = key.getEncoded();
	            System.out.print("Ge nyckeln ett filnamn: ");
	            String filename = scan.next();
	            System.out.println("Genererar nyckeln :"+filename+" ...");
	            FileOutputStream fileOut = new FileOutputStream(filename);
	            fileOut.write(keyBytes);
	            fileOut.close();
	            System.out.println("Nyckeln är genererad med filnamnet: " + filename + "...");
//	            System.exit(1);
	        } catch (NoSuchAlgorithmException e) {
	        }

	 }
}