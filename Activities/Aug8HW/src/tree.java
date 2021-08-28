class tree {
    public int height;
    private int age;
    static String type;

    public tree(int height, int age) {
        this.height = height;
        this.age = age;
        type = "Oak";
    }

    public void introduction() {
        System.out.println("Hi my name is tree and I am " + age + " years old and " + height + " meters tall. I am an "
                + type + " tree.");
    }

    public int IncreaseHeight() {
        height++;
        return height;
    }

    public int getAge() {
        return age;
    }

    public int setAge(int newAge) {
        this.age = newAge;
        return newAge;
    }

}