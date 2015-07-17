package com.artronics;

//import org.apache.commons.cli.Option;


import org.apache.commons.cli.*;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * This class will parse the command line options
 * It is a singleton pattern
 */
public final class CommandParser
{
    private static CommandParser instance=null;
    private static Options options= new Options();
    private String applicationNbame= "heating";

    private CommandParser() {
    }

    public static CommandParser getInstance(){
        if (instance == null){
            return new CommandParser();
        }
        return instance;
    }

    private Options CreateOptions(){
        this.options.addOption("h", "help",false, "Print this message");
        this.options.addOption("o", "on", false, "Trun heating system on");
        this.options.addOption("f", "off", false, "Turn heating system off");
        this.options.addOption("g", "get", false, "Show current status of system");
        return options;
    }
    public CommandLine Parse(String[] args){
        CreateOptions();
        CommandLine line=null;
        CommandLineParser parser = new DefaultParser();
        try {
            // parse the command line arguments
            line = parser.parse(this.options, args );
        }
        catch( ParseException exp ) {
            // oops, something went wrong
            System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
        }

        return line;
    }

    public void printUsage(final OutputStream out){
        final PrintWriter writer = new PrintWriter(out);
        final HelpFormatter usageFormatter = new HelpFormatter();
        usageFormatter.printUsage(writer, 80, this.applicationNbame, this.options);
        writer.close();
    }

    /**
     * Write "help" to the provided OutputStream.
     */
    public void printHelp(
            final int printedRowWidth,
            final String header,
            final String footer,
            final int spacesBeforeOption,
            final int spacesBeforeOptionDescription,
            final boolean displayUsage,
            final OutputStream out)
    {
        final String commandLineSyntax = "java -cp ApacheCommonsCLI.jar";
        final PrintWriter writer = new PrintWriter(out);
        final HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp(
                writer,
                printedRowWidth,
                commandLineSyntax,
                header,
                this.options,
                spacesBeforeOption,
                spacesBeforeOptionDescription,
                footer,
                displayUsage);
        writer.close();
    }
}
