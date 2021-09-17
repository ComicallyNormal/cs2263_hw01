/**
 * The App.java program fulfills the Homework requirements for CS 2263's Homework One assignment.
 *
 * @author Alex Diviney
 * @version 1.0.0
 */



package edu.isu.cs2263.hw01;

import org.apache.commons.cli.*;

import java.io.PrintWriter;

public class App {
    /* note: Command line usage of gradle build will only work after removing the getGreeting() method if you ALSO remove the
     * reference to it in AppTest */
    //public String getGreeting() {
    //   return "Hello World!";
    //}

    //This is a list of options that the application can take.
    //ie. Help, Batch, and Output options.
    private static final Option ARG_HELP = new Option("h","help", false, "print usage message"); //help option

    private static final Option ARG_BATCH = Option.builder("b").argName("file").hasArg(true).longOpt("batch").desc("batch file containing" +
            " expressions to evaluate").build(); //batch option

    private static final Option ARG_OUT = Option.builder("o").argName("file").hasArg(true).longOpt("output").desc("output file").build(); //output option

    //pretty print function for the help option
    //took most of this piece of code from a Youtube video on the Apache Commons CLI : youtube.com/watch?v=w0Bckb9Znfg
    private static void printHelp(Options options) {
        //The HelpFormatter Library seems to just help you print things easier. It acts similarly to the built in system.out.print() function though.
        HelpFormatter formatter = new HelpFormatter();
        //creates a PrintWriter
        PrintWriter pw = new PrintWriter(System.out);
        //pretty prints
        formatter.printUsage(pw, 100, "eval [OPTIONS]");
        pw.println("Evaluation of simple mathematical expressions");
        pw.println();
        formatter.printOptions(pw, 100, options, 2, 5);
        pw.println();
        pw.println("Copyright (C) 2021 Alex Diviney");
        pw.close(); // ends the pretty print.
    }



    public static void main(String[] args) {

//        for (String arg : args) {
//            System.out.println("Got argument [" + arg + "]");
//        }
        //The command line parser will let us parse our options list.
        CommandLineParser clp = new DefaultParser();

        //The Options object allows us to store a bunch of option objects.
        Options options = new Options();
        options.addOption(ARG_BATCH);
        options.addOption(ARG_HELP);
        options.addOption(ARG_OUT);


        //Try Except block
        try {
            //Parses command line arguments.
            CommandLine cl = clp.parse(options, args);


            //if userInput includes -h or --help then a help message (in printHelp()) is printed. Then program ends.
            //this if comes first so that we can rely on short circuiting to avoid argument frequency errors. If an
            // -h option is found in a command, then we dont evaluate the rest of the command, just output the help print statement.
            if (cl.hasOption(ARG_HELP.getLongOpt())) {
                printHelp(options);
                System.exit(0);
            }
            //If userInput includes -b or --batch then the system echoes the filename given.
            else if(cl.hasOption(ARG_BATCH.getLongOpt())){
                String batchArg1 = cl.getOptionValue(ARG_BATCH.getLongOpt()); // gets the first argument passed in after -b or -batch (the filename)

                System.out.println("Batch Value: "+ batchArg1); //Displays the filename
                // System.out.println(cl.getArgList().size());
            }
            //If userInput includes -o or --output then the system echoes the filename given.
            else if(cl.hasOption(ARG_OUT.getLongOpt())){

                String outputArg1 = cl.getOptionValue(ARG_BATCH.getLongOpt()); //gets the first argument passed in after -o or -output (the filename).

                System.out.println("Output value: "+outputArg1);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }


    } //end main

}//EOF
