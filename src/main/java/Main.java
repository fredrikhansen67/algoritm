

public class Main {

	public static void main(String [] args) throws Exception {
		String mess = "M�sen breder ut sina vingar och med ett flax sveper den iv�g mot skyarna p� jakt efter mat";
		Encrypt ee = new Encrypt();

		byte[] kodat = ee.encrypt(mess);
		System.out.println(kodat);		 
		System.out.println(new String(ee.decrypt(kodat)));

	}
}
