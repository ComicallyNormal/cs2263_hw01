/**
 * The App.java program fulfills the Homework requirements for CS 2263's Homework One assignment.
 *
 * @author Alex Diviney
 * @version 1.0.5
 */

package edu.isu.cs2263.hw01;

import org.apache.commons.cli.*;

// Buffered Reader imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.PrintWriter;
import java.util.Scanner;

public class App {

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

        String eval;
        String expression;


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


            if (cl.hasOption(ARG_HELP.getLongOpt())) {
                printHelp(options);
                System.exit(0);
            }

            if (cl.getArgList().size() >0 && cl.getArgList().get(0).equals("eval") && !
                    (cl.hasOption(ARG_HELP.getLongOpt()) || cl.hasOption(ARG_BATCH.getLongOpt()) || cl.hasOption(ARG_OUT.getLongOpt()))) { //should short circuit passing in empty statement.
                eval = cl.getArgList().get(0);

                //user input loop
                System.out.print("Input an Expression: ");
                // Scanner scan = new Scanner(System.in);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(System.in));

                String flag = "c";
                String inputString = "foobar";
                while (!inputString.equals("c")) {
                    inputString = reader.readLine();
                    if(!inputString.equals("c")) {
                        Expression evaluatedString = new Expression(inputString);
                        System.out.println(evaluatedString.eval());
                    }
                }

            }
            //if userInput includes -h or --help then a help message (in printHelp()) is printed. Then program ends.
            //this if comes first so that we can rely on short circuiting to avoid argument frequency errors. If an
            // -h option is found in a command, then we dont evaluate the rest of the command, just output the help print statement.



            //If userInput includes -b or --batch then the system echoes the filename given.
            else if(cl.hasOption(ARG_BATCH.getLongOpt())){

                String batchArg1 = cl.getOptionValue(ARG_BATCH.getLongOpt()); // gets the first argument passed in after -b or -batch (the filename)
                System.out.println("Batch Value: "+ batchArg1); //Displays the filename

                FileReader fileEval = new FileReader(batchArg1);
                fileEval.fileExp();
                // System.out.println(cl.getArgList().size());
            }
            //If userInput includes -o or --output then the system echoes the filename given.
            else if(cl.hasOption(ARG_OUT.getLongOpt())){

                String outputArg1 = cl.getOptionValue(ARG_BATCH.getLongOpt()); //gets the first argument passed in after -o or -output (the filename).

                System.out.println("Output value: "+outputArg1);
            }

            else{
                System.out.println("Invalid input, try -h for help"); //checks for inputs other than eval
                System.exit(0); //exits if bad input found.
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }


    } //end main

}//EOF
