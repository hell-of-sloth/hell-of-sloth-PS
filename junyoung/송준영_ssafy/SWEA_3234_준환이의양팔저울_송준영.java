import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * SWEA 3234 준환이의 양팔저울
 * 난이도 6~7 / 10
 * 조합, 순열, 백트래킹(가지치기 주의)
 * 1078ms (제한시간 2000ms)
 * 
 * 사실 이문제는 강범이형꺼 코드 비교해주면서 본적이 있어서 그 때 본것 기억 바탕으로 구현했다(그때 내가 코드는 안짜봤음)
 * 먼저 순열로 가능한 무게추의 순서(순열)를 다 구한다음 -> 이 순서 하나하나에 가능한 조합(조합)을 찾는 것이 답이다
 * 순열과 조합은 따로 메서드를 구현하였고 SWEA 문제 특성상 테케를 한 코드에 구현 하기 때문에 반복되는 메인 문제 풀이 로직을 따로 메서드로 구현하였다.
 * 
 * 이 문제를 풀면서 다시 깨달은 점
 * ☆컬렉션 생성자에 다른 컬렉션을 넣으면 복사가 된다!!☆
 * List는 마지막에 빼는게 없다 (파이썬 pop과 달리...) -> 따라서 stack(deque)을 사용하자
 * 
 * 조합에서 가지치기는 L이 R보다 크면 무조건 가능하다는 것을 이용하여 분기를 두 개로 나눠 L쪽 삽입은 무조건 되고, R은 조건 체크후 갈 수 있게 하였음
 */
public class SWEA_3234_준환이의양팔저울_송준영 {
    // 빠른 입출력을 위한 BufferedReader, StringTokenizer, StringBuilder
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    // static 변수 선언 -> 초기화 주의!!
    static int N;                           // 무게추 개수
    static int[] weight;                    // 무게추 배열
    static List<List<Integer>> permuCase;   // 무게추 순열 경우들
    static int cnt;                         // 가능한 경우의 수

    public static void main(String[] args) throws Exception {
        int T = parseInt(br.readLine()); // 테스트 케이스 수

        // 메인 로직 돌리기
        for (int t = 1; t <= T; t++) {
            solve();
            sb.append(String.format("#%d %d\n", t, cnt));
        }

        // 출력
        System.out.println(sb);
    }
    
    /**
     * 메인 로직 메서드
     * @throws Exception
     */
    public static void solve() throws Exception {
        // 입력 및 초기화 처리
        // static 변수는 메인 로직 실행시 처음 초기화 해주는게 좋다
        N = parseInt(br.readLine());
        weight = new int[N];
        permuCase = new ArrayList<>();
        cnt = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weight[i] = parseInt(st.nextToken());
        }

        // 순열과 조합 구하기
        // 조합은 순열의 가지 수 만큼 -> foreach 이용
        permu(0, new boolean[N], new ArrayDeque<>());
        for (List<Integer> p : permuCase) {
            comb(0, p, 0, 0);
        }
    }

    /**
     * 순열 구하기
     * @param depth     현재 depth
     * @param visited   방문 여부 배열
     * @param temp      임시 저장 Deque
     */
    public static void permu(int depth, boolean[] visited, Deque<Integer> temp) {
        // 종료 조건 -> depth가 N이면 무게추 순열 완성
        if (depth == N) {
            permuCase.add(new ArrayList<>(temp)); // temp를 복사해서 넣어줘야 함 -> 얕은 복사하면 큰일남 (요소 변경 가능성)
            return;
        }

        // 순열 구하기
        for (int i = 0; i < N; i++) {
            if (!visited[i]) { // 방문 안했으면
                visited[i] = true; // 방문 체크
                temp.offerLast(weight[i]); // 무게추 넣기
                permu(depth + 1, visited, temp);// 다음 depth로
                temp.pollLast(); // 다시 무게추 빼주고
                visited[i] = false; // 방문 처리 빼기
            }
        }
    }

    /**
     * 조합 구하기
     * @param depth     현재 depth
     * @param checkList 무게추 순열
     * @param L         왼쪽 저울 무게 (굳이 List 필요없음, 총합만 필요하기 때문)
     * @param R         오른쪽 저울 무게 (굳이 List 필요없음, 총합만 필요하기 때문)
     */
    public static void comb(int depth, List<Integer> checkList, int L, int R) {
        // 종료 조건 -> depth가 N이면 무게추 조합 완성
        if (depth == N) {
            cnt++;
            return;
        }

        // 조합 구하기
        // 왼쪽꺼는 무조건 갈 수 있음
        L += checkList.get(depth);          // 왼쪽에 무게추 더하기
        comb(depth + 1, checkList, L, R);   // 다음 depth로 진입
        L -= checkList.get(depth);          // 왼쪽에 무게추 빼기

        // 오른쪽꺼는 조건 체크 후 갈 수 있음
        if (L >= R + checkList.get(depth)) {
            R += checkList.get(depth);
            comb(depth + 1, checkList, L, R);
        }
    }
}
