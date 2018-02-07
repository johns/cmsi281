public class CrownAndAnchor {

    public static void main(java.lang.String[] args) {

        int numOfGames = 10000000;

        if (args.length != 0) {
            numOfGames = Integer.parseInt(args[0]);
        }

        if (numOfGames < 1) {
            System.out.println("No games were played.");
            return;
        }

        int betSymbol = (int)(Math.random() * 6 + 1);
        int die1;
        int die2;
        int die3;
        int noMatchLosses = 0;
        int oneMatchWins = 0;
        int twoMatchWins = 0;
        int threeMatchWins = 0;

        for (int i = 0; i < numOfGames; i++) {
            die1 = (int)(Math.random() * 6 + 1);
            die2 = (int)(Math.random() * 6 + 1);
            die3 = (int)(Math.random() * 6 + 1);
            if (betSymbol != die1 && betSymbol != die2 && betSymbol != die3) {
                noMatchLosses++;
            }
            else if ((betSymbol == die1 && betSymbol != die2 && betSymbol != die3) ||
            (betSymbol != die1 && betSymbol == die2 && betSymbol != die3) ||
            (betSymbol != die1 && betSymbol != die2 && betSymbol == die3)) {
                oneMatchWins++;
            }
            else if ((betSymbol == die1 && betSymbol == die2 && betSymbol != die3) ||
            (betSymbol != die1 && betSymbol == die2 && betSymbol == die3) ||
            (betSymbol == die1 && betSymbol != die2 && betSymbol == die3)) {
                twoMatchWins++;
            }
            else {
                threeMatchWins++;
            }
        }
        int profit = ((-1*noMatchLosses)+(1*oneMatchWins)+(2*twoMatchWins)+(3*threeMatchWins));

        System.out.println("Welcome to Crown and Anchor simulator!");
        System.out.println("By default the simulation runs ten million games.");
        System.out.println("If you would like to run different amounts of games ");
        System.out.println("please put the ammount of games you want to play after 'java CrownAndAnchor', ");
        System.out.println(" for example 'java CrownAndAnchor 500' runs a simulation of 500 games. ");

        System.out.println("");

        System.out.println("There were no matches and the Player lost his/her bet for " + noMatchLosses + " games.");
        System.out.println("There was one match and the Player won his/her bet back and $1 for " + oneMatchWins + " games.");
        System.out.println("There were two matches and the Player won his/her bet back and $2 for " + twoMatchWins + " games.");
        System.out.println("There were three matches and the Player won his/her bet back and $3 for " + threeMatchWins + " games.");

        System.out.println("");

        if (profit > 0) {
            System.out.println("The Player made a profit of $" + profit + " from his/her bets.");
        }
        else {
            System.out.println("The Player lost a total of $" + Math.abs(profit) + " to the House.");
        }
        System.out.println("The House therefore had a winning percentage of " + (((double)noMatchLosses/numOfGames) * 100) + " %.");
        System.out.println("The Player therefore had a winning percentage of " + (((double)(oneMatchWins+twoMatchWins+threeMatchWins)/numOfGames) * 100) + " %.");
    }

}
