package steganogerson;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class Test {

	public static void main(String[] args) {
		Options options = new Options();
	      options.addOption("p", "print", false, "Send print request to printer.")
	         .addOption("g", "gui", false, "Show GUI Application")
	         .addOption("n", true, "No. of copies to print");

	      HelpFormatter formatter = new HelpFormatter();
	      formatter.printHelp("CLITester", options);
	}

}
