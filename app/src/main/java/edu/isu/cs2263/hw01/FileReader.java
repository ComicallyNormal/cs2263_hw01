/**
 * The FileReader.java program turns a file into a string that is then passed into Expression.java and evaluated.
 *
 * @author Alex Diviney
 * @version 1.1.0
 */


package edu.isu.cs2263.hw01;
import java.io.*;


public class FileReader {
    private String str_built;
    private  String[] str_arr;
    private StringBuilder str_builder;

    public FileReader(){}
//File Reader grabs a file, turns it into a bytestream, then into an array, cleans the array, then sends it line
    //by line to Expression to be evaluated.
    public FileReader(String file) {
        //InputStream is = parseFile.getResourceAsStream("/test.csv");
        try {
            //The hell is this boilerplate code. ( I <3 Stackexchange)

            //Grab a file as a resource stream
            InputStream is = this.getClass().getResourceAsStream("/batch.txt");
            String newLine = System.getProperty("line.separator"); //separates lines
            BufferedReader reader = new BufferedReader(new InputStreamReader(is)); //wraps as a buffered reader (dont ask me bro)
            //turns buffered reader into string
            str_builder = new StringBuilder();
            for (String line; (line = reader.readLine()) != null; ) {
                if (str_builder.length() > 0) {
                    str_builder.append(newLine);
                }
                str_builder.append(line);
                str_built = str_builder.toString();

            }
        //gets rid of line ends
            str_arr = str_built.split("(\r\n|\r|\n)");

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

//evaluates the file, which is now a string array, by sending it to Expression
    public void fileExp(){

       // System.out.println(str_arr[2]);
        for(int x = 0;x<str_arr.length;x++) {

            Expression lineExp = new Expression(str_arr[x]);
            System.out.println(lineExp.toString());
            System.out.println(lineExp.eval());

        }

            }

        }