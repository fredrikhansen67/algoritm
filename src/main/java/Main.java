

public class Main {

	public static void main(String [] args) throws Exception {
		
		/**
		 * RSA Encrypt
		 */
		String mess = "userpw=mynewcoolestpasswords\nother=omygod";
		Encrypt ee = new Encrypt();
		byte[] kodat = ee.encrypt(mess);
		Encrypt.generateEncryption(kodat);
//		System.out.println(ee.getDecryptedContent());
		
		/**
		 * AESLogin
		 */
		AESLogin al = new AESLogin();
		//Generate encryption file when new password is needed
//		al.generateKey();
		al.generateEncryption("xxxxxxxxxxxxxxxxUser=Kalle\nPassword=myNewPassword\nOtheruser=solvig\nOtherpwd=helangår");
		System.out.println("PASSWORD:" + al.doLogin().substring(16));
		

		
	}

}
