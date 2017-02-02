package io.digitalsynapse.classificationIndicator;



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

    public CommandLineParse(String[] args) {

        this.args = args;

        options.addOption("h", "help", false, "show help.");
        options.addOption("v", "version", false, "Display version information");

    }


    public void parse() {
        CommandLineParser parser = new BasicParser();

        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);

            if (cmd.hasOption("h"))
                help();

            if (cmd.hasOption("v")) {
                log.log(Level.INFO, "Using cli argument -v=" + cmd.getOptionValue("v"));
                // Whatever you want to do with the setting goes here
                DisplayVersion();
            } else {
                log.log(Level.SEVERE, "MIssing v option");
                help();
            }

        } catch (ParseException e) {
            log.log(Level.SEVERE, "Failed to parse comand line properties", e);
            help();
        }
    }



    private void help() {
        // This prints out some help
        HelpFormatter formater = new HelpFormatter();

        formater.printHelp("Main", options);
        System.exit(0);
    }


    private void DisplayVersion() {


        Package p = getClass().getPackage();
        String version = p.getImplementationVersion();

        System.out.println("Version:  " + version);

        System.exit(0);
    }
}
