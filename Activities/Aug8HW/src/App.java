import newObject.cat;

public class App {
    public static void main(String[] args) throws Exception {

        tree tree1 = new tree(8, 101);
        System.out.println(tree1.height);
        System.out.println(tree1.type);

        tree1.height = 9;
        tree1.introduction();
        tree1.IncreaseHeight();

        System.out.println(tree1.getAge());
        System.out.println(tree1.setAge(99));

        cat cat1 = new cat();
    }

    public int[] rectangle(int x, int y, int height, int width) {
        int xx = height / 2;
        int yy = width / 2;
        int xxx = xx + x;
        int yyy = yy + y;
        int[] i = { xxx, yyy };
        return i;
    }
}
