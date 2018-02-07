import java.util.Scanner;

public class ClassworkT1 {
    public static void main(String[] args) {
        System.out.println("Please enter a sentence.");
        Scanner sent = new Scanner(System.in);

        String unique = sent.nextLine();

        String[] words = unique.split(" ");
        int uniqueCounter = 0;
        for(int i=0; i<words.length; i++) {
            for(int j = 0; j<words.length; j++) {
                if(!(words[i].equals(words[j]))) {
                    uniqueCounter++;
                }
            }
        }
        System.out.println(uniqueCounter);
    }
}
