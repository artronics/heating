package com.artronics;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.apache.commons.cli.CommandLine;

import java.io.IOException;
import java.io.OutputStream;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {
        CommandParser commandParser = CommandParser.getInstance();
        CommandLine commandLine = commandParser.Parse(args);
        OutputStream out = System.out;

        if (args.length == 0){
            commandParser.printUsage(out);
        }

        else if (commandLine.hasOption("help")){
            commandParser.printHelp(80, "heating help", "Athour: Seyed Jalal Hosseini (jalalhosseiny@gmail.com)",
                    3, 5, true, out);
        }
        else if (commandLine.hasOption("on")){
            turnHeatingOn();
        }
        else if (commandLine.hasOption("off")){
            turnHeatingOff();
        }
        else if (commandLine.hasOption("get")){
            getStatus();
        }
    }

    private static void getStatus()
    {
        String value=null;
        try {
            value = ReadFromFile.Read("data.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Current status of heating system is "
        + value);
    }

    private static void turnHeatingOn()
    {
        WriteString("on");
        System.out.println("Heatting is turned on");
    }

    private static void WriteString(String content)
    {
        try {
            WriteToFile.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void turnHeatingOff(){
        WriteString("off");
        System.out.println("Heating system is turned off");
    }
}
