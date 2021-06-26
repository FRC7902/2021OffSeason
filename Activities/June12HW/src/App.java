public class App {
    public static void main(String[] args) throws Exception {
        /**
         * 
         * June12HW
         * 
         * More review
         * 
         * Complete the questions, push when done.
         * 
         * Also try and get that red squiggly error line to work again
         * 
         * Enjoy :)
         * 
         * 
         */


         
    }

    //Q1 Make a function that takes in a number N and returns the number of multiples of 10 between 1 and N (assume N is positive)
    public static int mul (int N) {
        int J = N%10;
        int B = N - J;
        int C = B/10;
        return C;
    }

    //Q2 Make a function that takes in 2 numbers N and M and returns the number of multiples of M between 1 and N (assume they're both positive)
    public static int mult(int N, int M) {
        int J = N % M;
        int B = N - J;
        int C = B / N;
        return C;
    }

    
    //Q3 Make a function that takes in a number N and returns an array of multiples of 10 between 1 and N (assume N is positive again)

    public static int [] multi (int N) {
        int J = N%10;
        int B = N-J;
        int C = B/10;
        int [] nums = new int [C];
        for (int i = 0; i <= C; i++) {
          nums [i] = i*10;
        }
        return nums;
      }
    //Q4 Make a function that takes in an array of prices and returns the total cost (total cost = sum of prices * 1.13 tax)
    public static double cost (double [] prices) {
        int i = 0;
        for (int value : prices) {
        i += value;
    }
        double cost = i * 1.13;
        return cost;
      }


    //Q5 Make a function that checks if a number is prime (a number that is not prime will have a divisor that is not 1 or itself)
    public static boolean (int prime) {
        int B = 0;
        for (int i = 2; i < prime; i++) {
          if (prime%i == 0) {
            B = B + 1;
          }
        }
        if (B>0) {
          return true;
        }
        else {
          return false;
        }
      }

    //Q6 Make up your own function that does something cool. 
    // something that calculates how long it will take to read a book in minutes
    // each pages has 400 words

    public static int time (int pages, int wpm) {
        int words = pages * 400;
        int time = words/wpm;
        return time;
    }


}
