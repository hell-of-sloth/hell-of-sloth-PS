// 체감 난이도 3/10, 이거도 완탐?
// 메소드를 따로 만들어서 차근차근 하면 성공
// 자바에서 한 클래스에 여러 메소드를 만들어 서로 호출하는 것을 배움
// Math.max를 이용해서 두 값 중 큰 값을 반환하는 것을 배움
// Math는 따로 import를 할 필요가 없음

import java.util.Scanner;

public class solve {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int t = 0 ; t < T ; t++) {

            int N = sc.nextInt();
            int M = sc.nextInt();
            int[][] arr = new int[N][N];
            
            for (int i = 0 ; i < N ; i++) {
                for (int j = 0 ; j < N ; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }
            
            int result = 0;
            for (int i = 0 ; i < N ; i++) {
                for (int j = 0 ; j < N ; j++) {
                    result = Math.max(result, spray(arr, i, j, M));
                }
            }

            System.out.println("#" + (t + 1) + " " + result);
        }
    }

    public static int spray(int[][] arr, int x, int y, int m) {

        int result_1 = arr[x][y];
        int result_2 = arr[x][y];

        for (int i = 1 ; i < m ; i++) {
            if (x - i >= 0) {
                result_1 += arr[x - i][y];
            }
            if (x + i < arr.length) {
                result_1 += arr[x + i][y];
            }
            if (y - i >= 0) {
                result_1 += arr[x][y - i];
            }
            if (y + i < arr.length) {
                result_1 += arr[x][y + i];
            }
        }

        for (int i = 1 ; i < m ; i++) {
            if (x - i >= 0 && y - i >= 0) {
                result_2 += arr[x - i][y - i];
            }
            if (x + i < arr.length && y + i < arr.length) {
                result_2 += arr[x + i][y + i];
            }
            if (x - i >= 0 && y + i < arr.length) {
                result_2 += arr[x - i][y + i];
            }
            if (x + i < arr.length && y - i >= 0) {
                result_2 += arr[x + i][y - i];
            }
        }

        return Math.max(result_1, result_2);
    }
}
