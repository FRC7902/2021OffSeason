public class App {
    public static void main(String[] args) throws Exception {
        /**
         * May22 homework Don't refer to anything Push when done
         * 
         * Content: - For Loops - For Each Loops - Break - Arrays
         * 
         * Have fun!
         */

        // Q1 Using a for loop, count from 1 to 20.

        for (int x = 1; x < 21; x++) {
            System.out.println(x);
        }

        // Q2 Using a for loop, print out every multiple of 3 from 3 to 66

        for (int x = 3; x < 67; x += 3) {
            System.out.println(x);
        }

        // Q3 Using nested for loops, print out the multiplication tables from 1x1=1 to
        // 12x12=144
        for (int x = 1; x < 13; x++) {
            for (int y = 1; y < 13; y++) {
                System.out.println(x + "x" + y + "=" + x * y);
            }
        }

        // Q3.5 Using a break statement, count from 1 to 10.
        for (int i = 1; i < 11; i++) {
            System.out.println(i);
            break; // the break statement doesn't do anything here...
        }

        // Q3.75 Using a continue statement, count from 1 to 50, but skip over every
        // multiple of 5.

        for (int ii = 1; ii < 50; ii++) {
            if (ii % 5 == 0) {
                continue;
            }
            System.out.println(ii);
        }

        // Q4 Make an array of strings and fill it with at least 4 random names
        String[] random = { "apple", "pear", "orange", "grapes" };

        // Q5 Make an empty array of 10 integers
        int[] nums = new int[10];

        // Q6 Using the array from Q4, print out the second name
        String yes = random[2];
        System.out.println(yes);

        // Q7 Using the array from Q4, change the third name to something else
        random[1] = "pie";

        // Q8 Using the array from Q4, print out the number of names
        System.out.println(random.length);

        // Q9 Using a normal for loop, print out the names from Q4.
        for (int xyz = 0; xyz = random.length; xyz++) {
            String var = random[xyz];
            System.out.println(var);
        }

        // Q10 Using a for-each loop, print out the names from Q4.
        for (String x : random) {
            System.out.println(x);
        }

        // BONUS:
        // Make and fill an array of 10 integers.
        int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        // Using a for loop, loop through the array and make any odd numbers even.
        for (int xz = 0; xz = numbers.length; xz++) {
            int vars = numbers[xz];
            if (vars % 2 == 0) {
                vars++;
            }
            System.out.println(vars);
        }
        // You're finished!

    }
}
