
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;


/**
 * 
 * @author Fredrik
 *
 * Simple 2048 bit RSA encryption
 */

public class Encrypt {
	private static  KeyPair keyPair;
	private static PublicKey pubKey;
	private static PrivateKey privateKey;

	/**
	 * Default Constructor for the encryption class
	 */
	public Encrypt() {
		Encrypt.keyPair = generateKeyPair();
		pubKey = keyPair.getPublic();
		privateKey = keyPair.getPrivate();
	}
	public String getDecryptedContent() {
		try {
			return new String(decrypt(readEncryptionFromFile()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 *  
	 * @param message
	 * @return
	 * @throws Exception
	 * Encode the String 
	 */
	public byte[] encrypt(String message) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");  
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);  
		return cipher.doFinal(message.getBytes());  
	}
	/**
	 * 
	 * @param encrypted
	 * @return
	 * @throws Exception
	 * Decode the Byte String
	 */
	public byte[] decrypt(byte [] encrypted) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");  
		cipher.init(Cipher.DECRYPT_MODE, pubKey);
		return cipher.doFinal(encrypted);
	}

	/**
	 * Generate keypair
	 * @return keypair or null
	 */
	public static KeyPair generateKeyPair(){
		try {
			final int keySize = 2048;
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(keySize);      
			return keyPairGenerator.genKeyPair();
		}catch(NoSuchAlgorithmException ne) {System.out.println("Failed to generate keypair" +ne);return null;}
		catch(Exception e) {System.out.println("Failed to generate keypair, something went really wrong here" +e);return null;}
	}
	
	public static void generateEncryption(byte[] secPassw) {
		try {
			File fil = new File("C:\\aaa/RSAenc.crypto");
			FileOutputStream outputStream = new FileOutputStream(fil);
			outputStream.write(secPassw);
			outputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public byte[] readEncryptionFromFile() {
		try {
			File fil = new File("C:\\aaa/RSAenc.crypto");
			FileInputStream inputStream = new FileInputStream(fil);
			byte[] inputBytes = new byte[(int) fil.length()];
			inputStream.read(inputBytes);
			inputStream.close();
			return inputBytes;

		} catch (IOException e) {
			e.printStackTrace(); return null;
		}
	}

	public static void main(String [] args) throws Exception {
		String mess = "mynewcoolerpassword";
		Encrypt ee = new Encrypt();
		byte[] kodat = ee.encrypt(mess);
		generateEncryption(kodat);
		System.out.println(ee.getDecryptedContent());
	}
}
