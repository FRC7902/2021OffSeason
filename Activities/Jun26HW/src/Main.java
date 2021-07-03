public class Main {
  public static void main(String[] args) throws Exception {
    Car car1 = new Car();
    System.out.println(car1.numberOfWheels);
    System.out.println(car1.colour);
    System.out.println(car1.brand);
    System.out.println(car1.name);

    car1.name = "centodieci";
    System.out.println(car1.name);
    System.out.println(hasDriver);
    System.out.println(Car.hasDriver);
  }

  /**
   * June26HW
   * 
   * All about Classes
   */

  // Q1 Create a class Car

  // Q2 Give the class Car a couple of attributes (number of wheels, colour, etc)
  // Make sure they're non-static

  // Q3 In the main method, create an object of Car

  // Q4 Print out that object's attributes

  // Q5 Change one of the attributes and print it out again.

  // Q6 Add a static attribute to the Car class (maybe a boolean called hasDriver)

  // Q7 Use the previous object to print out the static attribute

  // Q8 Use the class itself to print out the static attribute (don't use the
  // object)

  // All done!
}

class Car {
  public int numberOfWheels = 4;
  public String colour = "black";
  public String brand = "Bugatti";
  public String name = "La Voiture Noire";
  public static boolean hasDriver = true;
}
