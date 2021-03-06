public class App {

    public static int x = 0;
    public static void main(String[] args) throws Exception {
        /*
            May 29 HW
            
            Don't refer to anything
            Push when done. 


            Topics:
            - Methods
            - Returning values
            - Scope
            - Overloading
        
            NOTE: For any method you make, make sure you call it in the main method.
            If the method returns a value, make sure to print that value (You can give it a test input).        
        */


        int[] arr = {3, 4, 2, 4, 1};

        System.out.println(name(arr));
        
        

    }

        //Q1 Make a method that prints out "Hello" 3 times. 
        public static void hi (){
            System.out.println ("Hello");
            System.out.println ("Hello");
            System.out.println ("Hello");
        }


        //Q2 Make a method that takes in an integer and prints out the integer less 2. 
        public static void hey (int a) { 
            System.out.println (a -= 2);
        }



        //Q3 Make a method that takes in a string and prints out the string 3 times.
        public static void hello (String a) { 
            System.out.println (a);
            System.out.println (a);
            System.out.println (a);
        }


        //Q4 Make a method that takes in an integer and returns the integer less 2. 
        public static int hey2 (int a) { 
            return a-2;
        }

        //Q5 Make a method that takes in and prints out an array of Strings
        public static void hey (String[] array) { 
            for(String item : array){
                System.out.println(item);
            }
        }

        //Q6 Make a variable that any method can access
        //not sure how to do or what this means


        //Q7 True or False, you can have methods with the same name.
        //Ans: yes


        //BONUS Make a method that takes in an array of integers and returns the sum of all the integers. 
        public static int method1(int [] abcd) {
            int ee = 0;
            for (int i = 0; i < abcd.length; i++) {
                
                ee = abcd[i] + ee;
            }
            return ee;
        }

        //BONUS2 Make a method that takes in an array of integers and returns the greatest integer.
        public static int name(int[] azy) {
            int bigI = 0;
            for (int i = 1; i < azy.length; i++) {
                if(azy[bigI] < azy[i]){
                    bigI = i;
                }
            }
            return azy[bigI];
        }


        //You're finished!
}
