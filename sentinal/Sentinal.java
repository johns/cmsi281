package sentinal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sentinal implements SentinalInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------

    private PhraseHash posHash, negHash;


    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------

    Sentinal (String posFile, String negFile) throws FileNotFoundException {
        posHash = new PhraseHash();
        negHash = new PhraseHash();

        loadSentimentFile(posFile, true);
        loadSentimentFile(negFile, false);
    }


    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------

    public void loadSentiment (String phrase, boolean positive) {
        if (positive) {
            posHash.put(phrase);
        }
        else {
            negHash.put(phrase);
        }
    }

    public void loadSentimentFile (String filename, boolean positive) throws FileNotFoundException {
        File file = new File(filename);
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            String s = sc.nextLine();
            loadSentiment(s, positive);
        }
        sc.close();
    }

    public String sentinalyze (String filename) throws FileNotFoundException {
        int positiveCounter = 0;
        int negativeCounter = 0;

        File file = new File(filename);
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            String s = sc.nextLine();
            String[] words = s.split("\\W+");

            for (int i = 1; i <= posHash.longestLength(); i++) {
                for (int j = 0; j < words.length+1 - i; j++) {
                    String word = "";
                    for (int k = j; k < j+i; k++) {
                        if(k==j) {
                            word = words[k];
                        }
                        else {
                            word += " " + words[k];
                        }
                    }
                    if (posHash.get(word) != null) {
                        positiveCounter++;
                    }
                }
            }

            for (int i = 1; i <= negHash.longestLength(); i++) {
                for (int j = 0; j < words.length+1 - i; j++) {
                    String word = "";
                    for (int k = j; k < j+i; k++) {
                        if(k==j) {
                            word = words[k];
                        }
                        else {
                            word += " " + words[k];
                        }
                    }
                    if (negHash.get(word) != null) {
                        negativeCounter++;
                    }
                }
            }
        }
        sc.close();

        if (positiveCounter > negativeCounter) {
            return "positive";
        }
        else if (negativeCounter > positiveCounter) {
            return "negative";
        }
        else {
            return "neutral";
        }
    }


    // -----------------------------------------------------------
    // Helper Methods
    // -----------------------------------------------------------

    // TODO: Add your helper methods here!

}
