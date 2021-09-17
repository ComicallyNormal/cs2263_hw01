package edu.isu.cs2263.hw01;
import java.io.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;


public class FileReader {
    private String fileName;
    private String str_built;
    private File parseFile;
    private  String[] str_arr;
    private StringBuilder str_builder;
    //private Expression lineExp;

    public FileReader(){}

    public FileReader(String file) {


        //InputStream is = parseFile.getResourceAsStream("/test.csv");
        try {
            //The hell is this boilerplate code. ( I <3 Stackexchange)
           // String test = new String(this.getClass().getResourceAsStream("/batch.txt").readAllBytes());
            InputStream is = this.getClass().getResourceAsStream("/batch.txt");
            String newLine = System.getProperty("line.separator");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
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