/*
 * Author: Vinay Sastha R
 */
package spellsort;

import java.io.*;
import java.util.*;

public class SpellSort {

    static ArrayList<Integer> numbersA = new ArrayList<>();
    static ArrayList<String>  numberNamesB = new ArrayList<>(); 
    static ArrayList<String>  sortedNumberNamesC = new ArrayList<>();
    static ArrayList<Integer> sortedNumbersD = new ArrayList<>();
    
    static HashMap<String,Integer> resultNum = new HashMap<String,Integer>();
    
    static HashMap<Integer,String> numWord1to19 = new HashMap<Integer,String>();
    static HashMap<Integer,String> numTys = new HashMap<Integer,String>();
    
    public static void assignOneToNineteen()
    {
        numWord1to19.put(1,"One");
        numWord1to19.put(2,"Two");
        numWord1to19.put(3,"Three");
        numWord1to19.put(4,"Four");
        numWord1to19.put(5,"Five");
        numWord1to19.put(6,"Six");
        numWord1to19.put(7,"Seven");
        numWord1to19.put(8,"Eight");
        numWord1to19.put(9,"Nine");
        numWord1to19.put(10,"Ten");
        numWord1to19.put(11,"Eleven");
        numWord1to19.put(12,"Twelve");
        numWord1to19.put(13,"Thirteen");
        numWord1to19.put(14,"Fourteen");
        numWord1to19.put(15,"Fifteen");
        numWord1to19.put(16,"Sixteen");
        numWord1to19.put(17,"Seventeen");
        numWord1to19.put(18,"Eighteen");
        numWord1to19.put(19,"Nineteen");
    }
    public static void assignNumTys()
    {
        numTys.put(2,"Twenty");
        numTys.put(3,"Thirty");
        numTys.put(4,"Forty");
        numTys.put(5,"Fifty");
        numTys.put(6,"Sixty");
        numTys.put(7,"Seventy");
        numTys.put(8,"Eighty");
        numTys.put(9,"Ninety");
    }
    
    public static void setResultNumString(int num1,String num1Name, int num2,String num2Name)
    {
        resultNum.clear();
        resultNum.put(num1Name,num1);
        resultNum.put(num2Name,num2);
    }
    
    public static String returnUnder20(int number)
    {
        return numWord1to19.get(number);
    }
    
    public static String returnTys(int number)
    {
        return numTys.get(number);
    }
    
    public static void addNumberNames(String name1,String name2)
    {
        numberNamesB.clear();
        numberNamesB.add(name1);
        numberNamesB.add(name2);
    }
    
    public static void sortedNameList(String name1, String name2)
    {
        sortedNumberNamesC.clear();
        sortedNumberNamesC.add(name1);
        sortedNumberNamesC.add(name2);
        Collections.sort(sortedNumberNamesC);
    }
    
    public static void sortedNumbersD()
    {
        sortedNumbersD.clear();
        sortedNumbersD.add(resultNum.get(sortedNumberNamesC.get(0)));
        sortedNumbersD.add(resultNum.get(sortedNumberNamesC.get(1)));
    }
    
    public static void readNumbers()
    {
        System.out.println("Enter two numbers separated by space: ");
        Scanner input = new Scanner(System.in);
        String in = input.nextLine();
        String[] num = in.split("\\s+");
        numbersA.add(Integer.parseInt(num[0]));
        numbersA.add(Integer.parseInt(num[1]));
    }
    
    public static String decideProcess(int input)
    {
        String numberWord = "\0";
        int num = input;
        while(num!=0)
        {
            if(num%100 <= 20)
            {    
                numberWord = numberWord + returnUnder20(num%100);
                if(num!=0) num/=100;
                if(num!=0 && num%10!=0) numberWord = returnUnder20(num%10) + " Hundred " + numberWord;    //hundred digits
                if(num!=0) num/=10;
                if(num%100 < 20)    //upto 19
                {    
                    if(num!=0) numberWord = returnUnder20(num%100) + " Thousand " + numberWord;   //ten-th and thousand digits
                    num/=100;
                }
                else
                {
                    if(num!=0) numberWord = returnUnder20(num%10) + " Thousand " + numberWord;
                    if(num!=0) numberWord = returnTys(num/10) + " " + numberWord;
                    num/=100;
                }
            }
            else
            {
                numberWord = numberWord + returnUnder20(num%10);  //unit digit
                num/=10;
                if(num!=0 && (num%10)!=0) numberWord = returnTys(num%10) + " " + numberWord;   //tens digits
                if(num!=0) num/=10;
                if(num!=0 && num%10!=0) numberWord = returnUnder20(num%10) + " Hundred " + numberWord;    //hundred digits
                if(num!=0) num/=10;
                if(num%100 <= 20)
                {    
                    if(num!=0) numberWord = returnUnder20(num%100) + " Thousand " + numberWord;   //ten-th and thousand digits
                    num/=100;
                }
                else
                {
                    if(num!=0) numberWord = returnUnder20(num%10) + " Thousand " + numberWord;
                    if(num!=0) numberWord = returnTys(num/10) + " " + numberWord;
                    num/=100;
                }
            }    
        }
        String result = numberWord.replaceAll("null", "").trim();
        return result;
    }
    
    public static void main(String[] args) {
        
        int converge = 0, convergeValue = 0;
        String number1Name, number2Name;
        int temp = 0;
        
        readNumbers();
        assignOneToNineteen();
        assignNumTys();
        
        while(converge==0)
        {
            number1Name = decideProcess(numbersA.get(0));
            number2Name = decideProcess(numbersA.get(1));
            setResultNumString(numbersA.get(0),number1Name,numbersA.get(1),number2Name);
        
            addNumberNames(number1Name,number2Name);
            sortedNameList(number1Name,number2Name);
            sortedNumbersD();
            
            if(Objects.equals(numbersA.get(0), numbersA.get(1)) && Objects.equals(sortedNumbersD.get(0), sortedNumbersD.get(1)))
            {    
                 converge = 1;
                 convergeValue = numbersA.get(0);
            }
            
            temp = numbersA.get(0);
            numbersA.set(0, (numbersA.get(0)+sortedNumbersD.get(0)));   //adding 1st element in A to 1st element in D
            sortedNumbersD.set(0, (sortedNumbersD.get(0) + temp));      //adding 1st element in D to previous value of 1st element of A
            
            temp = numbersA.get(1);
            numbersA.set(1, (numbersA.get(1)+sortedNumbersD.get(1)));   //adding 2nd element in A to 2nd element in D
            sortedNumbersD.set(1, (sortedNumbersD.get(1) + temp));      //adding 2nd element in D to previous value of 2nd element of A
        }

        if(numbersA.get(0)>99999 || sortedNumbersD.get(0)>99999 || numbersA.get(1)>99999 || sortedNumbersD.get(0)>99999)
            {  System.out.print("out of bounds");}
      	else
      		System.out.print(convergeValue);
    }
}