
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * Main_10158_개미
 * 
 * 난이도 2/10
 * 수학적인 문제
 * 
 * 인풋이 200,000,000 이고 시간이 0.15초 제한이라 매우 적은 시간복잡도 요구 -> 공식으로 푸는 문제로 예상
 * 개미 이동시 좌표에 대한 공식이 있음
 * 개미 좌표에서 각 x, y에 T 만큼 더한 후 각축의 값으로 나눠서 몫과 나머지를 구함
 * 몫의 짝수, 홀수 여부에 따라 계산 방법이 다름
 * 짝수면 0에서 나머지만큼 이동(0 + 나머지), 홀수면 좌표 끝에서 나머지만큼 이동 (좌표끝 - 나머지)
 * 
 */
public class Main_10158_개미 {
    // 입력 처리
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        int[] mapSize = new int[2];     // 맵 크기
        int[] antPos = new int[2];      // 개미 위치
        int[] result = new int[2];      // 결과값
        int T;                          // 시간

        // 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2; i++) {
            mapSize[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2; i++) {
            antPos[i] = Integer.parseInt(st.nextToken());
        }
        T = Integer.parseInt(br.readLine());

        // 공식 계산
        // x축
        if (((antPos[0] + T) / mapSize[0] % 2) == 0) {              // 짝수
            result[0] = (antPos[0] + T) % mapSize[0] + 0;
        } else {                                                    // 홀수
            result[0] = mapSize[0] - ((antPos[0] + T) % mapSize[0]);
        }
        // y축
        if (((antPos[1] + T) / mapSize[1] % 2) == 0) {              // 짝수
            result[1] = (antPos[1] + T) % mapSize[1] + 0;
        } else {                                                    // 홀수
            result[1] = mapSize[1] - ((antPos[1] + T) % mapSize[1]);
        }

        // 출력
        System.out.println(result[0] + " " + result[1]);
    }
}
