import java.io.*;
import static java.lang.Integer.parseInt;

/**
 * SWEA 1954. 달팽이숫자
 * 난이도 3/10
 * 구현, 배열 순회?
 * 75ms 약 25,000 kb
 * 
 * 배열을 달팽이 처럼 뺑뺑 도는 문제
 * 예전에 풀었었는데 로직이 살짝만 기억나서 일단 해봤는데 예전 로직처럼 됐다
 * 가로, 세로 방향을 나눠서 처리하고 방향벡터를 두어서 바꿔주면서 처리하면 된다
 * 가로 방향은 가장 큰게 하나 더 있기 때문에 이걸 처리해주어야 하며
 * 어디서 방향을 바꿀지, 어디서 크기를 줄일지도 이슈가 된다
 * 기본 틀만 잡히면 다음에 구현 할 때 어렵지 않을 듯
 */
public class SWEA_1954_달팽이숫자_송준영 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    
    // 배열 크기 N * N
    static int N;

    public static void main(String[] args) throws Exception {
        // 테스트 케이스 수
        int T = parseInt(br.readLine());

        // 메인 로직 반복
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append("\n");  // 테케 출력형식을 지켰는가?? - 확인
            solve();
        }
        // 출력
        System.out.println(sb);
    }
    
    // 메인 메서드
    public static void solve() throws Exception {
        // 배열 크기 입력
        N = parseInt(br.readLine());
        // 배열 초기화
        int[][] snail = new int[N][N];

        // 변수 선언
        int sx = 0, sy = -1;    // 행, 열 -> 열은 왜 -1 임? 0부터 하면 뒤의 로직 할 때 배열 벗어나 버림, 이동하고 넣기 때문에
        int num = 1;            // 현재 숫자 번호
        int dir = 1;            // 순방향 증가인지 역방향 증가인지
        int iter = N;           // 반복 횟수

        // 예시 4 일때 ) 4 3 3 2 2 1 1, 가로가 4 하나가 추가된다
        while (true) {
            // 가로 방향 처리
            for (int i = 0; i < iter; i++) {
                sy += dir; // 가로 좌표에 방향 1만큼 더해주기 (순방향이면 +1, 역방향이면 -1)
                snail[sx][sy] = num++; // 숫자 찍고 숫자 증가
            }

            iter--; // 반복 횟수 감소, 가로는 한 번 더 많기 때문에 가로 하고 지워줌

            // 만약 반복 횟수가 0이 되면 즉시 탈출
            if (iter == 0) {
                break;
            }

            // 세로 방향 처리
            for (int i = 0; i < iter; i++) {
                sx += dir; // 세로 좌표에 방향 1만큼 더해주기 (순방향이면 +1, 역방향이면 -1)
                snail[sx][sy] = num++; // 숫자 찍고 숫자 증가
            }

            dir *= -1; // 방향 전환
        }
        
        // 빠른 출력을 위해 sb에 넣어주기
        // 출력 형식을 지켰는가?? - 확인
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(snail[i][j]).append(" ");
            }
            sb.append("\n");
        }
    }
}
