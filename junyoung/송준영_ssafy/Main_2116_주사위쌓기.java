import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main_2116_주사위쌓기
 * 난이도 4/10
 * 구현, 브루트포스
 * 첫번째 주사위의 면이 정해지면 그 기준으로 다른 주사위는 다 정해짐
 * 6가지 경우의 수 다 확인 + 주사위 하나당 4면 중 가장 큰 값 찾기
 * Arrays.stream() 사용하면 오버헤드가 좀 있다
 * for문 이용시 내 기준 276ms -> 220ms로 줄어듦
 */
public class Main_2116_주사위쌓기 {
    // 입력 처리
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());        // 주사위 개수
        int[][] dices = new int[N][6];                  // 주사위 정보

        // 주사위 정보 입력 String -> int[]
        for (int i = 0; i < N; i++) {
            // dices[i] = Arrays.stream(br.readLine().split(" "))
            //                 .mapToInt(Integer::parseInt).toArray();
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                dices[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 최대값 출력
        System.out.println(getMaxDiceNum(N, dices));
    }

    // 주사위를 쌓는 메서드 (최대값 반환)
    public static int getMaxDiceNum(int N, int[][] dices) {
        int result = 0;                 // 최대값
        int max;                        // 각 경우의 최대값, 6가지 경우
        int bottom, top;                // 밑면, 윗면
        int tempMax;                    // 주사위 하나당 4면중 가장 큰 값

        // 6가지 경우의 수 다 확인
        for (int i = 0; i < 6; i++) {
            max = 0;                        // 초기화
            bottom = dices[0][i];           // 밑면 할당
            top = dices[0][getOppo(i)];     // 윗면 할당

            // 주사위 쌓기
            // 주사위 하나당 각 면의 최대값 확인
            // 그 다음 윗면, 밑면 갱신
            for (int j = 0; j < N; j++) {
                // System.out.println("top : " + top + " bottom : " + bottom);
                tempMax = 0;

                // 주사위 한 개 당 4면 중 가장 큰 값 찾기
                for (int k = 0; k < 6; k++) {
                    if (dices[j][k] == bottom || dices[j][k] == top) continue;  // 밑면, 윗면은 제외
                    tempMax = Math.max(tempMax, dices[j][k]);                   // 최대값 갱신
                }

                max += tempMax;                             // 최대값 더하기

                // 마지막 주사위가 아니면 밑면, 윗면 갱신하기
                if (j != N-1) {
                    bottom = top;                           // 밑면은 윗면으로
                    for (int k = 0; k < 6; k++) {
                        if (dices[j+1][k] == bottom) {
                            top = dices[j+1][getOppo(k)];   // 윗면 갱신
                        }
                    }
                }
            }
            result = Math.max(result, max);                 // 최대값 갱신
        }
        return result;      // 최대값 반환
    }

    // 주사위 반대편 인덱스 쉽게 찾으려고 만든 메서드
    public static int getOppo(int N) {
        switch (N) {
            case 0:
                return 5;
            case 1:
                return 3;
            case 2:
                return 4;
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
                return 0;
        }
        return -1;
    }
}   
