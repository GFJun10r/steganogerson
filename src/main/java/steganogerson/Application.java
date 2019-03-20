package steganogerson;

import org.apache.commons.cli.ParseException;

public class Application {

	public static void main(String[] args) {
		
		// parsear args
		try {
			Arguments arguments = Arguments.build(args);
			System.out.println(arguments.getPassphrase());
			System.out.println(arguments.getImagePath());
			System.out.println(arguments.isEncryption());
			
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		
//		if(arguments.isEncription()) {
//			Encryption.encrypt(arguments);
//		}else {
//			Decryption.decrypt(arguments);
//		}
	}

}
