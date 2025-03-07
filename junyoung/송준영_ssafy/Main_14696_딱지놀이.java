import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt; // import static 으로 사용하면 메소드명으로만 사용 가능
import java.util.StringTokenizer; 

/**
 * Main_14696_딱지놀이
 * 난이도 2/10
 * 구현
 * 단순히 4개의 분기를 나눠서 처리하면 되는 문제
 * 딱지의 모양을 저장하는 배열을 사용해서 각 모양의 개수를 저장하고 비교하였다
 */
public class Main_14696_딱지놀이 {
    // 빠른 입력과 출력을 위한 BufferedReader, StringBuilder
    // 토큰 분리를 위한 StringTokenizer
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        int N = parseInt(br.readLine());        // 테스트 케이스 수
        int result;                             // 결과값 -> 1, -1, 0
        for (int i = 0; i < N; i++) {
            result = ddakjiGame();              // 딱지 게임 진행

            // if 문 사용시
            // if (result == 1) {
            //     sb.append("A\n");
            // } else if (result == -1) {
            //     sb.append("B\n");
            // } else {
            //     sb.append("D\n");
            // }

            switch (result) {
                case 1:                     // A가 이기면
                    sb.append("A\n");
                    break;

                case -1:                    // B가 이기면
                    sb.append("B\n");
                    break;

                case 0:                     // 비기면
                    sb.append("D\n");
                    break;
            }
        }

        // 출력
        System.out.println(sb.toString());
    }

    /**
     * 딱지 게임 진행 메서드
     * @return 1 : A가 이김, -1 : B가 이김, 0 : 비김
     * @throws Exception
     */
    public static int ddakjiGame() throws Exception {
        // 인덱스 숫자로 저장하기 위해서 일부러 5 할당
        int[] a = new int[5];                   // A의 딱지 모양 저장 배열
        int[] b = new int[5];                   // B의 딱지 모양 저장 배열

        st = new StringTokenizer(br.readLine());
        int aIter = parseInt(st.nextToken());           // A의 딱지 수
        for (int i = 0; i < aIter; i++) {
            a[parseInt(st.nextToken())]++;              // 인덱스 값에 딱지 모양 저장
        }
        st = new StringTokenizer(br.readLine());
        int bIter = parseInt(st.nextToken());           // B의 딱지 수
        for (int i = 0; i < bIter; i++) {
            b[parseInt(st.nextToken())]++;              // 인덱스 값에 딱지 모양 저장
        }

        // 별 비교
        if (a[4] > b[4]) {
            return 1;
        } else if (a[4] < b[4]) {
            return -1;
        }

        // 동그라미 비교
        if (a[3] > b[3]) {
            return 1;
        } else if (a[3] < b[3]) {
            return -1;
        }

        // 네모 비교
        if (a[2] > b[2]) {
            return 1;
        } else if (a[2] < b[2]) {
            return -1;
        }

        // 세모 비교
        if (a[1] > b[1]) {
            return 1;
        } else if (a[1] < b[1]) {
            return -1;
        }

        // 다 같으면
        return 0;
    }
}
