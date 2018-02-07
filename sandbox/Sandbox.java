import java.util.ArrayList;

public class Sandbox {

    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList<String>();
        arr.add("hi"); // Append = add
        arr.add("bye");
        arr.add("maybe");
        arr.remove(1);
        System.out.println(arr.get(1));
    }
}
