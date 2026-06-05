package PractisesExercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Corejava {
    public static void main( String[] args) {

        //variables and datatypes

        int myNum=5;
        String word ="Hello";
        char letter = 'c';
        double decimal = 3.14;
        boolean flag=true;

        System.out.println("printing the number value" +myNum);  // + concatenates the int variable to a s tring

//Arrays

        int[] arr1= {1,2,3,4,5};

        int[] arr2 = new int[5];
        arr2[0] = 1;
        arr2[1] = 2;
        arr2[2] = 3;
        arr2[3] = 4;
        arr2[4] = 5;

//knowing type of the element

        System.out.println(arr1.getClass().getSimpleName()); //get the type of object
        //we cannot do the same with primitive data types like int, string

        int a = 5;
        System.out.println(((Object)a).getClass().getSimpleName()); // so boxed/wrapped the primitive data type with Object



//looping an array

        for(int i=0; i<arr2.length; i++){
            System.out.println(arr2[i]);
        }

        String[] s = {"AK", "karthi", "Sush"};
        for(int i=0; i<s.length; i++){
            System.out.println(s[i]);
        }

        //enhanced for loop
        for(String str: s){
            System.out.println(str);
        }


        //for loop with conditional statements
        int[] allNum = {10,22,66,45,90,500};

        for(int i=0; i<allNum.length;i++){
            if(allNum[i] % 2 == 0)
                System.out.println(allNum[i]);
            else
                System.out.println(allNum[i]);
        }

//Array list in java
        //declaration

        ArrayList<Integer> l = new ArrayList<>();
        List<Integer> l2= new ArrayList<>(); // can also declare in this format. list is an interface and arraylist implements the list
        l.add(1);
        l.add(2);
        l.add(3);
        System.out.println(l.get(0));
        l.remove(0);
        int sizeOfList= l.size();
        System.out.println("size of the array list " +sizeOfList);
        for(int item : l){
            System.out.println(item);
        }

//convert array to list

        Integer[] x= {65,54,87,24};
        List<Integer> y = new ArrayList<Integer>(Arrays.asList(x));


        //strings in java is considered as object

        String str1 = "Karthika is pretty"; //literals
        String str2 = "Karthika is pretty"; // since the literals are same it is assigned froms str1 to str2

        System.out.println(str1);

        //declaring string using new keyword
        String str3 = new String("Welcome");
        String str4= new String("Welcome");

//string functions
        String[] splittedString = str2.split("is");
        System.out.println(splittedString[0]);
        System.out.println(splittedString[1]);
        System.out.println(splittedString[1].trim());


        for(int i=str2.length()-1; i>0;i--){

            System.out.println(str2.charAt(i));
        }



    }

}
