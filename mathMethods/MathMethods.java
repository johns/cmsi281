import java.math.BigInteger;

public class MathMethods extends java.lang.Object {

    public MathMethods() {

    }

    public static java.math.BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        if (n <= 1)
        return result;
        else {
            for (int i = n; i > 1; i--) {
                result = result.multiply(BigInteger.valueOf(i));
            }
            return result;
        }
    }

    public static java.math.BigInteger fibonacci(int n) {
        if (n == 0) {
            return BigInteger.ZERO;
        } else if (n == 1) {
            return BigInteger.ONE;
        } else {
            BigInteger result = BigInteger.ONE;
            BigInteger previousNum = BigInteger.ZERO;
            for (int i = 2; i <= n; i++) {
                BigInteger tempNum = result;
                result = result.add(previousNum);
                previousNum = tempNum;
            }
            return result;
        }
    }

    public static long gcd(long m, long n) {
        if (m == 0 && n == 0) {
            return 0;
        } else if (m == 0) {
            return n;
        } else if (n == 0) {
            return m;
        }
        long larger = Math.max(Math.abs(m), Math.abs(n));
        long smaller = Math.min(Math.abs(m), Math.abs(n));
        long remainder = larger % smaller;
        long result = 1;
        if (remainder != 0) {
            gcd(smaller, remainder);
        } else {
            result = smaller;
        }
        return result;
    }

    public static long lcm(long m, long n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        return Math.abs(m * n) / gcd(m, n);
    }

    public static double poly(double x, double[] coeff) {
        double result = coeff[coeff.length - 1];
        for (int i = coeff.length - 1; i > 0; i--) {
            result = result * x + coeff[i - 1];
        }
        return result;
    }

    public static double sqrt(double x, double epsilon) {
        double result = 0;
        double lowerBound = 0;
        double upperBound = 1;
        if (x == 0) {
            return 0;
        } else if (x > 1) {
            upperBound = x;
        } else if (x == 1) {
            return 1;
        }
        while (Math.abs(result * result - x) > epsilon * x) {
            result = ((upperBound - lowerBound) / 2) + lowerBound;
            if (result * result > x) {
                upperBound = result;
            } else {
                lowerBound = result;
            }
        }
        return result;
    }

    public static double root(int n, double x, double epsilon) {
        boolean negative = false;
        double result = 0;
        double lowerBound = 0;
        double upperBound = 1;
        if (x != Math.abs(x)) {
            negative = true;
        } else if (x == 0) {
            return 0;
        } else if (Math.abs(x) > 1) {
            upperBound = Math.abs(x);
        } else if (x == 1) {
            return 1;
        } else if (x == -1) {
            return -1;
        }
        while (Math.abs(power(result, n) - Math.abs(x)) > epsilon * Math.abs(x)) {
            result = ((upperBound - lowerBound) / 2) + lowerBound;
            if (power(result, n) > Math.abs(x)) {
                upperBound = result;
            } else {
                lowerBound = result;
            }
        }
        if (negative) {
            return result * -1;
        } else {
            return result;
        }
    }

    public static double power(double x, int n) {
        double result = 0;
        if (n < 0) {
            return 1 / power(x, Math.abs(n));
        } else if (n == 0) {
            return 1;
        } else if (n == 1) {
            return x;
        } else {
            if (n % 2 == 0) {
                result = power(x, n / 2);
                return result * result;
            } else {
                return x * power(x, n - 1);
            }
        }
    }

    public static void main(java.lang.String[] args) {
        if (args.length < 2) {
            System.out.println("Not enough arguments, please try again.");
        } else if (args[0].equals("factorial")) {
            if (args.length != 2) {
                System.out.println("Wrong number of parameters, please try again.");
            } else if (Integer.parseInt(args[1]) != Math.abs(Integer.parseInt(args[1]))) {
                System.out.println("The number must be greater than or equal to zero, please try again.");
            } else {
                System.out.println(factorial(Integer.parseInt(args[1])));
            }
        } else if (args[0].equals("fibonacci")) {
            if (args.length != 2) {
                System.out.println("Wrong number of parameters, please try again.");
            } else if (Integer.parseInt(args[1]) != Math.abs(Integer.parseInt(args[1]))) {
                System.out.println("The number must be greater than or equal to zero, please try again.");
            } else {
                System.out.println(fibonacci(Integer.parseInt(args[1])));
            }
        } else if (args[0].equals("gcd")) {
            if (args.length != 3) {
                System.out.println("Wrong number of parameters, please try again.");
            } else {
                System.out.println(gcd(Long.parseLong(args[1]), Long.parseLong(args[2])));
            }
        } else if (args[0].equals("lcm")) {
            if (args.length != 3) {
                System.out.println("Wrong number of parameters, please try again.");
            } else {
                System.out.println(lcm(Long.parseLong(args[1]), Long.parseLong(args[2])));
            }
        } else if (args[0].equals("poly")) {
            if (args.length < 3) {
                System.out.println("Wrong number of parameters, please try again.");
            } else {
                double[] coeffs = new double[args.length - 2];
                for (int i = 2; i < args.length; i++) {
                    coeffs[i - 2] = Double.parseDouble(args[i]);
                }
                System.out.println(poly(Double.parseDouble(args[1]), coeffs));
            }
        } else if (args[0].equals("sqrt")) {
            if (args.length != 3) {
                System.out.println("Wrong number of parameters, please try again.");
            } else if (Double.parseDouble(args[1]) != Math.abs(Double.parseDouble(args[1]))
            || Double.parseDouble(args[2]) != Math.abs(Double.parseDouble(args[2]))) {
                System.out.println("The numbers must be greater than or equal to zero, please try again.");
            } else {
                System.out.println(sqrt(Double.parseDouble(args[1]), Double.parseDouble(args[2])));
            }
        } else if (args[0].equals("root")) {
            if (args.length != 4) {
                System.out.println("Wrong number of parameters, please try again.");
            } else if (Integer.parseInt(args[1]) != Math.abs(Integer.parseInt(args[1]))
            || (Math.abs(Integer.parseInt(args[1])) % 2 == 0
            && Double.parseDouble(args[2]) != Math.abs(Double.parseDouble(args[2])))
            || Double.parseDouble(args[3]) != Math.abs(Double.parseDouble(args[3]))) {
                System.out.println(
                "The numbers must be greater than or equal to zero or the root power must change, please try again.");
            } else {
                System.out.println(
                root(Integer.parseInt(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3])));
            }
        } else if (args[0].equals("power")) {
            if (args.length != 3) {
                System.out.println("Wrong number of parameters, please try again.");
            } else {
                System.out.println(power(Double.parseDouble(args[1]), Integer.parseInt(args[2])));
            }
        } else {
            System.out.println("The method you attemped is not here, please try again.");
        }
    }
}
