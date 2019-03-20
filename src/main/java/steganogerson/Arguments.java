package steganogerson;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Arguments {

	boolean encryption;
	String passphrase;
	String imagePath;

	Options options = new Options();

	public static Arguments build(String[] args) throws ParseException {
		return new Arguments(args);
	}

	public Arguments(String[] args) throws ParseException {
		buildArgsOptions();
		filterArgs(args);
	}

	private void buildArgsOptions() {
		// TODO implement --verbose, -pass
		Option action = Option
				.builder("a")
				.longOpt("action")
				.hasArg()
				.desc("Define if the image will be encrypted or decrypted - in construction")
				.required()
				.build();

		Option path = Option
				.builder("path")
				.longOpt("filepath")
				.hasArg()
				.desc("Define the image in/out path")
				.required()
				.build();

		options.addOption(action);
		options.addOption(path);

	}

	private void filterArgs(String[] args) throws ParseException {
		if (args.length == 0) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("Steganogerson help:", options);
			return;
		}
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = parser.parse(options, args);

		saveActionArgValue(cmd.getOptionValue("a"));
		savePathArgValue(cmd.getOptionValue("p"));

	}

	private void saveActionArgValue(String value) throws ParseException {
		if (value.equalsIgnoreCase("e")) {
			this.encryption = true;
		}else if(value.equalsIgnoreCase("d")){
			this.encryption = false;
		} else {
			throw new ParseException("Action values must be \"e\" (Encryption) or \"d\" (Decryption)");
		}
	}

	private void savePathArgValue(String optionValue) {
		this.imagePath = optionValue;		
	}
	
	public boolean isEncryption() {
		return encryption;
	}

	public String getPassphrase() {
		return passphrase;
	}

	public String getImagePath() {
		return imagePath;
	}
}
