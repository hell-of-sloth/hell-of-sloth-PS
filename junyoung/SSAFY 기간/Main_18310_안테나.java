// 체감 난이도 6/10
// 답을 구하는 방법을 찾는게 어려웠다
// 정확하지 않으면 예시 몇개 테스트 해보고 규칙 찾기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_18310_안테나 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        // 정렬
        Arrays.sort(arr);
        
        // 중앙값 출력
        System.out.println(arr[(N-1)/2]);
    }
}
