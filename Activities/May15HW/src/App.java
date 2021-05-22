
/**
 * May 15 Homework
 * 
 * Do not refer to anything
 * 
 * Push when done
 * 
 * Topics: - if statements - else if and else statements - ternary operators -
 * while loops
 */

public class App {
    public static void main(String[] args) throws Exception {

        // Q1 Make an if statement (including else if and else) that does the following
        // If the age is less than 16, print "you can't drive yet"
        // If the age is exactly 16, print "You can start learning to drive"
        // If the age is greater than 16, print "You can learn to drive"
        int age = 15;
        if (age < 16) {
            System.out.println("you can't drive yet");
        } else if (age == 16) {
            System.out.println("You can start learning to drive");
        } else {
            System.out.println("You can learn to drive");
        }

        // Q2 Using a ternary operator, print "YES" or "NO" if the person is an adult
        // (age is at least 18)
        // The template is given to you
        System.out.println((true) ? "Something" : "Something");

        int personage = 15;
        System.out.println((personage < 17) ? "YES" : "NO");

        // Q3 Using a while loop, count from 1 to 20 (print it out)
        int x = 1;
        while (x < 21) {
            System.out.println(x);
            x++;
        }

        // Q4 Using a while loop, count down from 20 to 1 (print it out)
        int y = 20;
        while (y > 0) {
            System.out.println(y);
            y--;
        }

        // Q5 Using one while loop, count from 1 to 50 but:
        // - if the count is less than 25, print out the count squared
        // - otherwise, print out the count multiplied by -1
        int z = 1;
        while (z < 51) {
            if (z < 25) {
                System.out.println(Math.sqrt(z));
            } else {
                System.out.println(z * -1);
            }
            z++;
        }

        // NOTE: Don't do anything to the count except increment it

        // BONUS
        // Remember how chars can be represented using integers?
        // Using a while loop, print out the entire lowercase alphabet
        // You can refer to things if you need help (though it is possible to not refer
        // to the chart)

        char c = 97;
        while (c < 123) {
            System.out.println(c);
            c++;
        }

    }
}
