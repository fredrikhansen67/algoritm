

public class Main {

	public static void main(String [] args) throws Exception {
		
		/**
		 * RSA Encrypt
		 */
		String mess = "mynewcoolestpassword2";
		Encrypt ee = new Encrypt();
		byte[] kodat = ee.encrypt(mess);
		Encrypt.generateEncryption(kodat);
		System.out.println(ee.getDecryptedContent());
		
		/**
		 * AESLogin
		 */
		AESLogin al = new AESLogin();
		//Generate encryption file when new password is needed
		al.generateEncryption("myNewPassword");
		System.out.println("PASSWORD:" + al.doLogin());
		

		
	}

}
