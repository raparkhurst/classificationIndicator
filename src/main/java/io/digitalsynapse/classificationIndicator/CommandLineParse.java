package io.digitalsynapse.classificationIndicator;



import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;



public class CommandLineParse {
    private static final Logger log = Logger.getLogger(CommandLineParse.class.getName());
    private String[] args = null;
    private Options options = new Options();

    private ClassificationParameters Parameters = new ClassificationParameters();

    public CommandLineParse(String[] args) {

        this.args = args;

        options.addOption("h", "help", false, "show help.");
        options.addOption("v", "version", false, "Display version information");
        options.addOption("f", "classification-file", true, "Path to classification file to read in");
        options.addOption("C", "classification", true, "Set classification, see help for more");
        options.addOption("c", "caveats", true, "String of additional caveats");
        options.addOption("G", "gui-full", false, "if set, bar spans whole screen");
        options.addOption("X", "noX", false, "if set, runs in console mode");

    }


    public ClassificationParameters parse() {
        CommandLineParser parser = new BasicParser();

        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);

            if (cmd.hasOption("v")) {
                DisplayVersion();
            } else if (cmd.hasOption("f")) {
                Parameters.setClassificationFileName(cmd.getOptionValue("f"));
            } else if (cmd.hasOption("C")) {
                Parameters.setClassificationLevel(cmd.getOptionValue("C"));
            } else if (cmd.hasOption("c")) {
                Parameters.setClassificationCaveats("c");
            } else if (cmd.hasOption("G")) {
                Parameters.setGuiFullScreenMode(true);
            } else if (cmd.hasOption("X")) {
                Parameters.setConsoleMode(true);
            } else if (cmd.hasOption("h")) {
                DisplayHelp();
            }

        } catch (ParseException e) {
            DisplayHelp();
        }

        return Parameters;
    }


    private void OptionNotImplimentedYet() {
        System.out.println(" This function has not yet been implimented yet...");
        System.exit(0);
    }


    private void DisplayHelp() {
        // This prints out some help
        HelpFormatter formater = new HelpFormatter();

        formater.printHelp("Main", options);
        System.exit(0);
    }


    private void DisplayVersion() {
        Package p = getClass().getPackage();
        //String version = p.getImplementationVersion();
        String version = "";
        String timeStamp = "";


        // try to load from maven properties first
        try {
            Properties versionProperties = new Properties();
            InputStream is = getClass().getResourceAsStream("/version.txt");
            if (is != null) {
                versionProperties.load(is);
                version = versionProperties.getProperty("version", "");
                timeStamp = versionProperties.getProperty("build.date", "");
            }
        } catch (Exception e) {
            // ignore
        }

        System.out.println("version:  " + version);
        System.out.println("build time:  " + timeStamp);

        System.exit(0);
    }
}
