// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package Employee;

/** Add your docs here. */
public class Employee {
    public String name = "Ben";
    private int height = 180;
    protected int salary = 10000;
    public static String company = "Amazon";

    public Employee(String name, int height, int salary) {
        this.name = name;
        this.height = height;
        this.salary = salary;
    }


    public void introduction() {
        System.out.println("Hello, my name is" + name + ", an employee at " + company);
    }

    public int getSalary(){
        return salary;
    }

}
