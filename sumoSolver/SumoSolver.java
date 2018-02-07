public class SumoSolver {

    public SumoSolver() {
    }

    public static void main(java.lang.String[] args) {
        if (args.length < 3) {
            System.out.println("Not enough arguments, please try again.");
        } else if (args.length % 2 == 0) {
            System.out.println("There cannot be an even number of arguments, please try again.");
        } else {
            for (int i = 0; i < args.length; i++) {
                if (Integer.parseInt(args[i]) != Math.abs(Integer.parseInt(args[i]))) {
                    System.out.println("There cannot be negative arguments, please try again.");
                    return;
                }
            }
            int[] costs = new int[(args.length - 1) / 2];
            int[] weights = new int[(args.length - 1) / 2];
            int max = Integer.parseInt(args[args.length - 1]);

            for (int i = 0; i < args.length - 1; i++) {
                if (i % 2 == 0) {
                    costs[i / 2] = Integer.parseInt(args[i]);
                } else {
                    weights[i / 2] = Integer.parseInt(args[i]);
                }
            }

            Tuple[][] table = new Tuple[costs.length][costs.length + 1];
            for (int i = 0; i < costs.length; i++) {
                table[i][0] = new Tuple(costs.length);
                table[i][1] = new Tuple(costs.length);
                if (costs[i] <= max) {
                    table[i][1].setElement(i, 1);
                }
            }

            if (costs.length > 1) {
                table = solver(table, costs.length - 1, costs.length, max, costs, weights);
            }
            Tuple result = table[costs.length - 1][costs.length];

            int totalCost = 0;
            int totalWeight = 0;

            for (int i = 0; i < costs.length; i++) {
                if (result.getElement(i) == 1) {
                    System.out.println("$" + costs[i] + " / " + weights[i] + " pounds");
                    totalCost += costs[i];
                    totalWeight += weights[i];
                }
            }
            System.out.println(result.total() + " items / $" + totalCost + " / " + totalWeight + " pounds");
        }
    }

    public static Tuple[][] solver(Tuple[][] tuples, int i, int j, int max, int[] costs, int[] weights) {
        boolean finalRun = true;

        if (i > 0 && j > 1 && tuples[i - 1][j - 1] == null) {
            tuples = solver(tuples, i - 1, j - 1, max, costs, weights);
        }
        if (i > 0 && j > 1 && tuples[i][j - 1] == null) {
            tuples = solver(tuples, i, j - 1, max, costs, weights);
        }

        for (int q = i-1; q > 0; q--) {
            int cost = 0;
            for (int k = 0; k <= i; k++) {
                if (tuples[i][j - 1].getElement(k) == 1) {
                    cost += tuples[i][j - 1].getElement(k) * costs[k];
                } else {
                    cost += tuples[q][q].getElement(k) * costs[k];
                }
            }
            if (cost <= max) {
                tuples[i][j] = tuples[i][j - 1].add(tuples[q][q]);
            } else {
                tuples[i][j] = tuples[i][j - 1];
            }
        }


        for (int p = 0; p < costs.length; p++) {
            if (tuples[i][j].getElement(p) > 1) {
                tuples[i][j].setElement(p, 1);
            }
        }

        for (int n = 0; n < costs.length; n++) {
            if (tuples[n][n + 1] == null) {
                finalRun = false;
            }
        }

        if (finalRun) {
            for (int l = 0; l < costs.length; l++) {
                tuples[l][costs.length] = tuples[l][l + 1];
                if (l > 0) {
                    int weight1 = 0;
                    int weight2 = 0;
                    for (int m = 0; m < costs.length; m++) {
                        weight1 += tuples[l - 1][costs.length].getElement(m) * weights[m];
                        weight2 += tuples[l][costs.length].getElement(m) * weights[m];
                    }
                    if (weight1 > weight2) {
                        tuples[l][costs.length] = tuples[l - 1][costs.length];
                    }
                }
            }
        }
        return tuples;
    }

}

class Tuple {

    public static final Tuple IMPOSSIBLE = new Tuple(new int[0]);

    private int[] data;

    public Tuple(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }

        data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = 0;
        }
    }

    public Tuple(int[] data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        this.data = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            this.data[i] = data[i];
        }
    }

    public boolean isImpossible() {
        return this == IMPOSSIBLE;
    }

    public void setElement(int i, int j) {
        checkIndex(i);
        data[i] = j;
    }

    public int getElement(int i) {
        checkIndex(i);
        return data[i];
    }

    public int length() {
        return data.length;
    }

    public int total() {
        int sum = 0;
        for (int i = 0; i < length(); i++) {
            sum = sum + getElement(i);
        }
        return sum;
    }

    public Tuple add(Tuple t) {

        if (length() != t.length()) {
            throw new IllegalArgumentException();
        }

        Tuple sum = new Tuple(length());
        for (int i = 0; i < length(); i++) {
            sum.setElement(i, getElement(i) + t.getElement(i));
        }

        return sum;
    }

    @Override
    public boolean equals(Object t) {
        if ((t == null) || !(t instanceof Tuple) || (length() != ((Tuple) t).length())) {
            return false;
        }

        for (int i = 0; i < length(); i++) {
            if (getElement(i) != ((Tuple) t).getElement(i)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int product = 0;
        for (int i = 0; i < length(); i++) {
            product = product * getElement(i);
        }

        return (product >= 0) ? product : -product;
    }

    @Override
    public String toString() {
        if (isImpossible()) {
            return "Impossible tuple";
        }

        String result = "<";
        for (int i = 0; i < length(); i++) {
            result += (i > 0 ? "," : "") + data[i];
        }
        return result + ">";
    }

    private void checkIndex(int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }

        if (i >= length()) {
            throw new IllegalArgumentException();
        }
    }
}
