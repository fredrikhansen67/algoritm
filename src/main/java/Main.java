

public class Main {

	public static void main(String [] args) throws Exception {
		String mess = "Måsen breder ut sina vingar och med ett flax sveper den iväg mot skyarna på jakt efter mat";
		Encrypt ee = new Encrypt();

		byte[] kodat = ee.encrypt(mess);
		System.out.println(kodat);		 
		System.out.println(new String(ee.decrypt(kodat)));

	}
}
