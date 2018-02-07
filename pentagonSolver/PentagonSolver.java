import java.util.ArrayList;

public class PentagonSolver {

    public static void main(java.lang.String[] args) {

        int[] nums = new int[10];
        ArrayList<int[]> solutions = new ArrayList<int[]>();

        for (int i = 0; i < 10; i++) {
            nums[i] = i;
        }

        while (hasNext(nums)) {

            if (nums[0] + nums[1] + nums[2] == nums[2] + nums[3] + nums[4] &&
            nums[2] + nums[3] + nums[4] == nums[4] + nums[5] + nums[6] &&
            nums[4] + nums[5] + nums[6] == nums[6] + nums[7] + nums[8] &&
            nums[6] + nums[7] + nums[8] == nums[8] + nums[9] + nums[0] &&
            nums[8] + nums[9] + nums[0] == nums[0] + nums[1] + nums[2]) {
                int[] solution = new int[10];

                for (int i = 0; i < 10; i++) {
                    solution[i] = nums[i];
                }

                solutions.add(solution);
            }
        }
        System.out.println("The solutions to the Pentagon Problem are the following list of numbers where the first ");
        System.out.println("number is a point of the pentagon and the other numbers are sides and points in clock-wise order.");
        for (int n = 0; n < solutions.size(); n++) {
            int[] result = solutions.get(n);
            for (int p = 0; p < 10; p++) {
                System.out.print(result[p]);
            }
            System.out.println();
        }

    }

    public static void swap (int[] ray, int i, int j) {
        int temp = ray[i];
        ray[i] = ray[j];
        ray[j] = temp;
    }

    public static boolean hasNext(int[] ray) {
        int n = ray.length;

        int j;
        for (j = n-2; j >= 0; j--) {
            if (ray[j] < ray[j+1]) {
                break;
            }
        }

        if (j == -1) {
            return false;
        }

        int k = n-1;
        while (ray[j] > ray[k]) {
            k--;
        }
        swap(ray, k, j);

        for (int l = n-1, m = j+1; l > m; l--, m++) {
            swap(ray, l, m);
        }

        return true;
    }


}
