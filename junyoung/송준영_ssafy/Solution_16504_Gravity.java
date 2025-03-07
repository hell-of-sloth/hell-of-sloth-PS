import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * Solution_16504_Gravity
 * 난이도 4/10
 * 구현, 배열, 시뮬레이션
 * 75ms
 * 
 * 블럭이 있는 맵을 돌려서 블럭 하나하나를 떨어트려 그 낙차를 계산해야한다
 * 맵을 돌린다 -> 문제 상황 대로 넣고 돌려도 되지만 애초에 돌려진 상태로 입력을 하면 되지 않을까?
 * 떨어진 낙차를 구하는 것은 따로 메서드(drop)을 구현해서 한 줄 한 줄 계산하였다.
 * static으로 선언한것 초기화 잘해주기 (중요) + 반복되는 변수 초기화 되는지 확인 -> 초기화 에러뜨면 잡기 쉽지만 에러 안뜨면 영영 못 찾을수도...
 * 다행히 이번문제에서는 안그랬다.
 * 낙차계산은 뒤에 시작 인덱스를 두고 그 인덱스와 블럭 위치의 차를 구하면 된다.
 * 한 번 계산후 시작 인덱스의 값을 하나 올려준다 (블럭 하나가 내려왔으므로)
 */
public class Solution_16504_Gravity {
    // 빠른 입출력
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuffer sb = new StringBuffer();

    // static 메인 변수
    static int T;                   // 테스트 케이스

    // static solve 변수
    static int N;                   // 블럭 수
    static int[] blockNum;          // 블럭 수 배열
    static int blockMax;            // 블럭 최대 수 -> 세로 최대 칸
    static boolean[][] blockMap;    // 블럭 맵

    public static void main(String[] args) throws Exception {
        // 테스트 케이스 입력
        T = parseInt(br.readLine());
        
        // solve 실행
        for (int t = 1; t <= T; t++) {
            sb.append(String.format("#%d %d\n", t, solve()));
        }
        System.out.println(sb);
    }

    /**
     * solve 메서드
     * 문제의 메인 로직을 실행하는 메서드 입니다.
     * @return  최대 낙차
     * @throws Exception
     */
    public static int solve() throws Exception {
        // 결과를 저장할 변수 -> 지역 변수이므로 리턴 필요
        // 왜 얘는 static으로 안함? -> static으로 하면 계속 초기화 해줘야하는데
        // 귀찮기도 하고 초기화 하는거 까먹으면 뒤에서 틀릴 수 있음
        int result = 0;

        // 입력, static 변수 초기화
        N = parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        blockNum = new int[N];
        blockMax = Integer.MIN_VALUE;

        // 블럭 수 입력, 최대 블럭 수 구하기
        for (int i = 0; i < N; i++) {
            blockNum[i] = parseInt(st.nextToken());
            blockMax = Math.max(blockMax, blockNum[i]);
        }

        // 만약 블럭이 없다면 0 리턴
        // 이거 없으면 밑에서 에러
        if (blockMax == 0) return 0;

        // 블럭 맵 초기화
        blockMap = new boolean[N][blockMax];

        // 블럭 맵에 블럭 위치 표시
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < blockNum[i]; j++) {
                blockMap[i][j] = true;
            }
        }

        // 최대 낙차 구하기
        // drop 메서드 실행
        for (int i = 0; i < blockMax; i++) {
            result = Math.max(result, drop(i));
        }
        
        // 결과 리턴
        return result;
    }

    /**
     * drop 메서드
     * 블럭을 떨어트려서 낙차를 구하는 메서드
     * @param index     블럭 위치
     * @return          낙차
     */
    public static int drop(int index) {
        // 시작 인덱스, 최대 낙차
        int startIndex = N-1;
        int maxGap = 0;

        // 블럭이 있는지 확인하고 낙차 계산
        // startIndex - i가 그 한 블럭의 낙차임
        for (int i = N-1; i >= 0; i--) {
            if (blockMap[i][index] == true) {
                maxGap = Math.max(maxGap, startIndex-- - i);
                // System.out.println("what : " + maxGap);
            }
        }

        // 최대 낙차 리턴
        return maxGap;
    }
}
