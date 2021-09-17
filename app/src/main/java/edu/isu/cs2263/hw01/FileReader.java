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

            str_arr = str_built.split("(\r\n|\r|\n)");
            for (int x = 0;x<str_arr.length;x++) {
                //System.out.println(str_arr[x]);
            }
            //System.out.println(result);
            //return result.toString();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    public void fileExp(){

       // System.out.println(str_arr[2]);
        for(int x = 0;x<str_arr.length;x++) {

           // System.out.println(str_arr[x]);
           // str_arr[str_arr.length-1] = "";
            Expression lineExp = new Expression(str_arr[x]);
            System.out.println(lineExp.toString());
            System.out.println(lineExp.eval());

        }


            }

        }