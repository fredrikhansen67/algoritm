import java.security.Key;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESkrypto {

	private static String key="";
	private static String initVector="";
	private static String krypto;
	
	public static String encrypt(String key, String initVector, String value) {
        try {
        	byte[] b= Arrays.copyOf(key.getBytes("UTF-8"), 16);
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(b, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            System.out.println("encrypted string: "
                    + Base64.getEncoder().encodeToString(encrypted));
            krypto =  Base64.getEncoder().encodeToString(encrypted);

            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) { ex.printStackTrace();
        }

        return null;
    }
	
	public static String decrypt(String value) {
        try {
//            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
//            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); 
            cipher.init(Cipher.DECRYPT_MODE, getAesKey());
            
            String decrypted = new String(cipher.doFinal(value.getBytes()));
            System.out.println("encrypted string: "+ decrypted);
            return decrypted;
        } catch (Exception ex) { ex.printStackTrace();
        }

        return null;
    }
    private static Key getAesKey() throws Exception {
    	return new SecretKeySpec(Arrays.copyOf(key.getBytes("UTF-8"), 16), "AES");
    }

//	public static void main(String[] args) {
//		key = "aaaabbbbaaaabbbb";
//		initVector = "ssssddddssssdddd";
//		encrypt(key,initVector, "fredde");
////		decryspt(krypto);
//		// TODO Auto-generated method stub
//
//	}
}
