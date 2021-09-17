package edu.isu.cs2263.hw01;

import java.util.LinkedList;

public class Expression {

    private String numString;
    private LinkedList<Integer> listNum1;
    private LinkedList<Integer> evaluatedList;
    private LinkedList<String> listExp;


    public Expression(){

    }
    public Expression(String exp) {
        numString = exp;
        //initializes our 3 linked lists.
        listNum1 = new LinkedList<Integer>(); //all of the numbers in our expression
        evaluatedList = new LinkedList<Integer>(); // the tail of this will be our final answer
        listExp = new LinkedList<String>(); //the list of all the operations in our expression
        String tempStr = "";
        int tempNum = 0;
        //stolen from https://www.tutorialspoint.com/how-to-check-if-a-given-character-is-a-number-letter-in-java
        //checks if a char is a natural number/
        for (int i = 0; i < numString.length(); i++) {
            char currentChar = numString.charAt(i);
            boolean isNum = Character.isDigit(currentChar);

            if (isNum) { //checks if a symbol other than an integer has been entered. If not, adds the symbol to the current string.
                tempStr += numString.charAt(i);

            }
            else if (!isNum) {

                //symbol has been found.
                // System.out.println(listNum1.peekLast());
                String charStr = Character.toString(currentChar);
                //add symbol to list.
                listExp.addLast(charStr);

                //reset temp string
                tempNum = Integer.parseInt(tempStr);
                listNum1.addLast(tempNum);


                tempStr = "";

                //System.out.println(listNum1.peekFirst()+ " " +listNum1.peekLast());

            }
            if(currentChar == numString.length()-1){

                tempNum = Integer.parseInt(tempStr);
                listNum1.addLast(tempNum);
            }

        }
        System.out.println(listNum1.peekLast());
    }





    public void eval(){

        //evaluates the full expression.
        try {
            int evaluate = 0;
            int lSize = listNum1.size();
            int list1Head1 =listNum1.pollFirst();
            int list1Head2 = listNum1.pollFirst();
            String listExpHead = listExp.pollFirst();
            //System.out.println(listNum1.peekLast());
            for(int i = 0; i<lSize;i++) {
                //System.out.println(list1Head1 + " " + list1Head2);

                evaluate = simpleMath(list1Head1,list1Head2, listExpHead);
                System.out.println(evaluate);
                evaluatedList.addLast(evaluate);
                listExpHead = listExp.pollFirst();
                list1Head1 = evaluatedList.peekLast();
                list1Head2 = listNum1.pollFirst();

            }
            //System.out.println(evaluatedList.peekLast());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private int simpleMath(int num1,int num2,String symb){
        int eval = 0;
        switch(symb){
            case "+": eval = num1+num2;
                break;
            case "-": eval = num1-num2;
                break;
            case "/": eval = num1/num2;
                break;
            case "*": eval = num1*num2;
                break;
        }
        return eval;
    }
}
