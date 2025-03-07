import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main_19539_사과나무
 * 난이도 5/10
 * 그리디, 수학
 * 372ms -> 236ms
 * 
 * 원래 풀이
 * 각 수를 2로 나눠서 2의 개수와 1의 개수를 구한다
 * 1. 1의 개수가 2의 개수보다 많으면 NO
 * 2. 2의 개수가 1의 개수보다 많으면 2의 개수에서 1의 개수를 빼고 1도 0이됨 -> 2 하나를 꺼내 1 두개로 만든다 -> 반복해서 수가 같아지면 YES, 1이 많아지면 NO
 * 3. 그냥 둘 다 같으면 YES
 * 
 * 개선한 풀이
 * 1. 3의 배수가 아니면 NO
 * 2. 3의 배수이면 3으로 나눈 몫이 2의 개수보다 많으면 NO
 * 원리는 3으로 나누면 2와 1의 개수가 같다는게 증명되는데 각 수를 나눠서 나온 2의 개수가 부족하면 못 만들기 때문!\
 * -> 3으로 나눈 몫 <= 2의 개수이면 YES다
 */
public class Main_19539_사과나무 {
    // 빠른 입력을 위한 BufferedReader
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // 변수 선언
    static int N, ones = 0, twos = 0, total = 0;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        // 입력 처리
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (arr[i] % 2 != 0) {
                ones++;                     // 1의 개수 추가
            }
            total += arr[i];                // 전체 합
            twos += arr[i] / 2;             // 2의 개수 추가
        }

        // 3의 배수가 아니면 NO
        if (total % 3 != 0) {
            System.out.println("NO");
            return;
        }

        // 3의 몫의 개수
        total /= 3;

        // 3의 몫이 2의 개수보다 많으면 NO
        if (total > twos) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
        }


        // if (ones > twos) {
        //     System.out.println("NO");
        // } else if (twos > ones) {
        //     twos -= ones;
        //     ones = 0;
        //     while (ones < twos) {
        //         twos--;
        //         ones += 2;
        //     }            
        //     if (ones == twos) {
        //         System.out.println("YES");
        //     } else {
        //         System.out.println("NO");
        //     }
        // } else {
        //     System.out.println("YES");
        // }
    }
}
