import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int x = Integer.parseInt(br.readLine());

        Arrays.sort(arr); // 오름차순 정렬

        int left = 0;
        int right = n - 1;
        int count = 0;

        while (left < right) {
            if (arr[left] + arr[right] == x) {
                count++;
                left++;
                right--;
            } else if (arr[left] + arr[right] < x) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(count);
    }
}
