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
            //System.out.println(numString.charAt(i));
            if (isNum) { //checks if a symbol other than an integer has been entered. If not, adds the symbol to the current string.
                tempStr += numString.charAt(i);
                //System.out.println(tempStr);

            }
            if (!isNum && currentChar!= ' ' ) {


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
            if(i == numString.length()-1){
                tempNum = Integer.parseInt(tempStr);

                //System.out.println("CASE REACHED");
                listNum1.addLast(tempNum);
            }

        }
        //System.out.println(listNum1.peekLast());
    }


//Next step, when iterating, if the expression list is empty, evaluate the last two numbers with the latest expression
    //otherwise continue to iterate.


    public int eval() {

        int eval = 0;
        if (listNum1.peekFirst() != null) {
            eval = simpleMath(listNum1.pollFirst(), listNum1.pollFirst(), listExp.pollFirst());
        }

        int size = listNum1.size();
        String currentExp = "";
        for (int x = 0; x < size; x++) {

            if (listNum1.peekFirst() != null && listExp.peekFirst() != null) {
               // System.out.print(eval);
                currentExp = listExp.peekFirst();
                eval = simpleMath(eval, listNum1.pollFirst(), listExp.pollFirst());
            }

            else if (listNum1.peekFirst() != null && listExp.peekFirst() == null) {

            eval = simpleMath(eval,listNum1.pollFirst(),currentExp);

            }
        }
        return eval;
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

    public String toString(){
        String toString = numString;
        return toString;
    }
}
