import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_1019_책페이지 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[10];
        boolean[] check = new boolean[N+1];

        for (int i = 1; i <= N; i++) {

            int tmp = i;

            while (tmp != 0) {
                check[tmp] = true;
                nums[tmp % 10]++;
                tmp /= 10;
            }
        }

        for (int i : nums) {
            System.out.print(i + " ");
        }
    }
}
