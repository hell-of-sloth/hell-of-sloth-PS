import java.util.Scanner;

class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();


        for (int t = 0; t < T; t++) {
            System.out.println("#" + (t + 1));

            int n = sc.nextInt();
            
            int[][] arr = new int[n][n];
            int[][] arr90 = new int[n][n];
            int[][] arr180 = new int[n][n];
            int[][] arr270 = new int[n][n];
            
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }
    
            // 90도
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr90[j][n - 1 - i] = arr[i][j];
                }
            }
    
            // 180도
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr180[j][n - 1 - i] = arr90[i][j];
                }
            }
    
            // 270도
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr270[j][n - 1 - i] = arr180[i][j];
                }
            }
    
            // 출력
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(arr90[i][j]);
                }
                System.out.print(" ");
                for (int j = 0; j < n; j++) {
                    System.out.print(arr180[i][j]);
                }
                System.out.print(" ");
                for (int j = 0; j < n; j++) {
                    System.out.print(arr270[i][j]);
                }
                System.out.println();
            }
    
        }
    }
}
