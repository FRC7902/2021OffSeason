public class App {
    public static void main(String[] args) throws Exception {
        /**
         * Review!
         * 
         * Don't refer to anything
         * Push when done. 
         * 
         * Topics:
         * - Variables
         * - Operators
         * - If/Else Statements
         * - While statements
         * - For Loops
         * - Arrays
         * - Methods
         * 
         * 
         */


        //Q1 Make an variable to hold an integer and assign it a random value
        int var = 3;

        //Q2 Print out the integer
        System.out.println (var);

        //Q3 Print out the integer doubled
        System.out.println (var*2);

        //Q4 Increment the integer.
        var++;

        //Q5 Make a double variable and assign it the variable you made in Q1
        double vars = var;

        //Q6 Add 3.67 to the double variable.
        vars = vars + 3.67;

        //Q7 Store the new double in the variable from Q1. (make sure to cast)
        var = (int)vars;

        //Q8 Make a boolean that records whether the integer is less than 7.
        boolean i = var<7;

        //Q9 Make a booolean that records whether the integer is less than 17.
        boolean ii = var<17;

        //Q10 Make a boolean that records whether the integer is less than or equal to 16, and is also an even number
        boolean iii = var <= 16 && var%2==0;

        //Q11 Make a boolean that records whether the integer is a multiple of 5 or is not a multiple of 3
        boolean iiii = var%5==0 || var%3 != 0;

        //Q12 If the integer is greater than 7, print out "Hello"
        //If it is equal to 7, print out "Goodbye"
        //Otherwise print out "Bonjour"
        if (var>7) {
            System.out.println ("Hello");
        }
        else if (var==7) {
            System.out.println ("Goodbye");
        }
        else {
            System.out.println ("Bonjour");
        }

        //Q13 Make an array of 6 integers (fill it with some numbers)
        int[] numbers = { 1, 2, 3, 4, 5, 6};

        //Q14 If the 1st number is odd and the 2nd number is even, print out "Yes"
        //If both are even, print out "Maybe"
        //If both are odd, print out "Maybe not"
        //Otherwise, print out "No"
        if (numbers[0]%2==1 && numbers[1]%2==0) {
            System.out.println ("Yes");
        } 
        else if (numbers[1]%2==0 && numbers[2]%2==0) {
            System.out.println ("Maybe");
        }
        else {
            System.out.println ("No");
        }

        //Q15 Double the 3rd number until it is greater than 1000.
        while (numbers[2]<=1000) {
            numbers[2]*=2;//is this right?
        }

        //Q16 Repeatedly subtract 5 from the 4th number until it is negative
        while (numbers[3]>=0) {
            numbers[3] -= 5;
        }

        //Q17 Using a for loop, print out all the numbers in the array
        for (int x : numbers) {
            System.out.println(x);
        }

        //Q18 Using a for loop, print out all the numbers that aren't a multiple of 3 in the array
        for (int xyz = 0; xyz < numbers.length; xyz++) {
            int vars = numbers[xyz];
            if (vars%3!=0){
            System.out.println(vars);
            }
        }

        //Q19 Count from -10 to 10
        for (int xx= -10; xx<=10; xx++) {
            System.out.println (xx);
        }


        //Q20 Make a string array of any size and fill it with city names
        String[] random = { "Paris", "Toronto", "Vaughan"};

        //Q21 Print out all the city names that are longer than 7 letters
        for (String a : random) {
            if (a.length() > 7) {
                System.out.println (a);
            }
        }

        //Q22 Change all of the city names to "Toronto"
        random [0] = "Toronto";
        random [2] = "Toronto";

        //NOTE: Make all your methods outside the main method

        //Q23 Make a method that takes in an integer and returns a true/false depending on if the number is even

        //Q24 Make a method that takes in an array of integers and doubles them until all numbers are greater than 1000.

        //Q25 Make a method that takes in an integer N, and returns an array of the first N even numbers


        //You're finished!





    }
    public static boolean hi (int x) {
        if (x%2==0){
            return true;
        }
        else {
            return false;
        }
    }


    public static void hello(int[] inputArray){

        for(int i = 0; i < inputArray.length; i++){
            while (inputArray[i]<=1000) {
                inputArray[i]*=2;//is this right
            }
        }


        
    }
        
    
    public static int[] hey(int N){
        int[] result = new int[N];

        for(int i = 0; i < result.length; i++){
            result[i] = 2*(i+1);
        }


        return result;
    }

    
}
