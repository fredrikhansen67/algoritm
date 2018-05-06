import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

	public static void main(String [] args) throws Exception {
		/*
		String mess = "Måsen breder ut sina vingar och med ett flax sveper den iväg mot skyarna på jakt efter mat";
		Encrypt ee = new Encrypt();

		byte[] kodat = ee.encrypt(mess);
		System.out.println("KODAT :"+kodat);	
		File fil = new File("C://aaa/kodat.bin");
		FileWriter fw = new FileWriter(fil);
//		fw.write(kodat.toString());
		fw.write(new String(kodat));
		fw.close();


		System.out.println("BD :"+readFileAsString("C://aaa/kodat.bin"));
		System.out.println("===");
		System.out.println(new String(ee.decrypt(kodat)));
		System.out.println("===");
//		System.out.println(ee.decrypt(readFileAsString("C://aaa/kodat.txt").getBytes()));
*/
		String pw = "This is a secret";
		AESLogin al = new AESLogin(pw);
		al.generateEncryption("Superhemligt");
		System.out.println("PASSWORD:" + al.doLogin(pw));
		
//		String string = new String(byte[] bd, Charset charset);
//		System.out.println("BYTES :" +readFileAsString("C://aaa/kodat.txt").getBytes(StandardCharsets.UTF_8));
//		System.out.println(new String(ee.decrypt(br.readLine().getBytes())));
//		System.out.println(new String(ee.decrypt(kodat)));
		
	}
	public static String readFileAsString(String fileName)throws Exception
	  {
	    String data = "";
	    data = new String(Files.readAllBytes(Paths.get(fileName)));
	    return data;
	  }
}
