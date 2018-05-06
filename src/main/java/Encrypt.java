
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
 * @author Fredrik Hansen
 *
 * Simple 2048 bit RSA encryption
 */

public class Encrypt {
	private static  KeyPair keyPair;
	private static PublicKey pubKey;
	private static PrivateKey privateKey;
	private static String fileName = "C:\\aaa/RSAenc.crypto";

	/**
	 * Default Constructor for the encryption class
	 */
	public Encrypt() {
		init();
	}

	public void init() {
		try {
			Encrypt.keyPair = generateKeyPair();
			pubKey = keyPair.getPublic();
			privateKey = keyPair.getPrivate();
		}catch(Exception e) {System.out.println("Initiation failed"+e);}
	}
	public String getDecryptedContent() {
		try {
			return new String(decrypt(readEncryptionFromFile()));
		} catch (Exception e) {
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
	public static KeyPair generateKeyPair() throws NoSuchAlgorithmException{
		final int keySize = 2048;
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(keySize);      
		return keyPairGenerator.genKeyPair();

	}

	public static void generateEncryption(byte[] secPassw) throws IOException{

			File fil = new File(fileName);
			FileOutputStream outputStream = new FileOutputStream(fil);
			outputStream.write(secPassw);
			outputStream.close();
	}

	public byte[] readEncryptionFromFile() {
		byte[] inputBytes;
		try {
			File fil = new File(fileName);
			FileInputStream inputStream = new FileInputStream(fil);
			inputBytes = new byte[(int) fil.length()];
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
