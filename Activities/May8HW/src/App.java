/*
Follow the instructions 
Don't search things up
If you are unsure about your answer, leave it blank. We'll discuss it later.

This HW is about
- declaration and initialization
- operations 
- booleans
- chars
- Strings
- Math functions

*/


public class App {
    public static void main(String[] args) throws Exception {
        //Q1 In your own words, describe what is happening on each line 
        int x; //Ans: the int variable x is being declared to exist
        x = 4; //Ans: the int variable x is given the value of 4

        //Which one is the declaration and which one is the initilization?
        
        //declaration (1)
        //initilization (2)

        //Q2 create 2 booleans(one that is true, another that is false)

        boolean a = true;
        boolean b = false;


        //Using those variables along with the 3 boolean operators, print out the value true 4 times each in a different way. 
        //System.out.println(true); NOT ALLOWED
        //Make sure you use each of the 3 boolean operators at least once among your 4 print statements. (you don't have to use all 3 every time) 
        //You don't need to use both of the variables in every line 

        boolean ab = a || b;
        boolean cd = a != b;
        boolean ef = a == a;
        boolean gh = b == b;


        //Q3 Create 2 integer variables. (assign them any value)
        int c = 2;
        int d = 3;


        //Perform and print each math operation on them (+, -, *, /, %)
        //Make sure that it is printed as a double value (type casting)

        
        System.out.println ((double)c+d);
        System.out.println ((double)d-c);
        System.out.println ((double)c*d);
        System.out.println ((double)c/d);
        System.out.println ((double)c%d);
        

        //Using the 2 integers along with the comparison operators (>, <, ==, <=, >=, !=), write the value true in 6 ways
        //You can use math operators such as x + 2 > y 

        boolean e = c <= d;
        boolean f = c > d || 4 < 7;
        boolean g = c + 4 > d;
        boolean h = c == d-1;
        boolean i =  c != d;
        boolean j = c * c >= d;

        //Q4 Make a string (assign it anything)
        String hi = "hi";

        //print the length of the string

        int hey = hi.length (); or // length.hi ();
        System.out.println (hey);

        //Q5 Remember those 2 ints you made in question 3?
        //Print the greater one (using the Math. functions);
        System.out.println (Math.max (c, d));

        //Print the lesser one
        System.out.println (Math.min (c, d));

        //Print the square root of one of the them (it can be a decimal)
        System.out.println (Math.sqrt ((double)d);
        
        //Subtract 999 from one of the variables in 2 different ways

        int asd = d - 999;
        System.out.println (d - 999);

        //Find the absolute value of that variable
        System.out.println (Math.abs (asd));


        /* 

        What will be the two outputs of this: (hint: the first is a boolean, the second is a integer)
        int x = 4;
        System.out.println(x++>4);
        System.out.println(x);
        //Ans: false, 5 (unsure)
        
        
        */

        
        //Yay you are done
    }
}
