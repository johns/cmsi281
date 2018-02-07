import java.util.Scanner;
/**
* Asks a user what their top cp pokemon is
* and if it's under 1000, insults them
*
*/
public class PokeInsult {

    public static void main (String [] args) {
        System.out.println("Tell me your top cp pokemon.");
        Scanner input = new Scanner(System.in);

        int cp = Integer.parseInt(input.nextLine());

        if (cp < 1000) {
            System.out.println("how very sad to be you");
        }
        else {

        }
    }

}
