import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Main_30021_순열선물하기
 * 난이도 5/10
 * 백트랙킹, 소수
 * 처음 문제를 보았을때 N 범위가 5000까지라 백트랙킹을 사용하면 시간초과가 날 것이라고 생각했다
 * 그래서 다른 방법을 생각해 보려고 했지만 머리가 아퍼서 일단 시도 해보기로 했음
 * -> 그런데 풀림 (약 260ms)
 * 소수를 구해야 하므로 비교적 빠른 방법인 "에라토스테네스의 체"를 사용하여 소수를 구함 -> O(N * 1/2) 정도라고 함
 * 백트랙킹을 돌면서 합이 소수면 멈추고 돌아가기
 * 만약 N 까지 다 돌고 합이 소수가 아니면 출력 되도록 했다.
 */
public class Main_30021_순열선물하기 {
    // 빠른 입출력을 위한 BufferedReader, StringBuilder
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    // static 변수 선언
    static int N;
    static boolean[] check;         // 방문 체크
    static boolean[] isNotPrime;    // 소수 체크, 소수가 아니면 true
    static Deque<Integer> dq = new ArrayDeque<>();  // 순열 저장을 할 Deque
    public static void main(String[] args) throws Exception {
        // long sTime = System.currentTimeMillis();

        N = Integer.parseInt(br.readLine());    // N 입력
        check = new boolean[N+1];               // 방문 체크 배열 생성, 크기는 N+1 -> 인덱스로 접근 할 거라서

        int primeLength = (N * (N + 1)) / 2;        // 소수를 구할 범위를 정하기 위한 변수 -> 1 ~ N까지의 합이 최대이므로 N * (N + 1) / 2
        isNotPrime = new boolean[primeLength+1];    // 소수 체크 배열 생성, 크기는 primeLength+1 -> 인덱스로 접근 할 거라서
        
        isNotPrime [0] = isNotPrime [1] = true;     // 0, 1은 소수가 아니므로 true로 설정
        
        // 에라토스테네스의 체 구현을 위한 반복문
        // 2 부터 마지막의 제곱근까지 반복 -> 제곱근까지 하는이유 -> 제곱근 이후는 중복이 되기 때문
        // 소수가 아니면 true로 설정
        // 소수면 그 소수의 배수는 소수가 아니므로 다 반복해서 true로 설정
        // -> 전에 그 소수보다 작은 소수의 배수는 이미 다 true로 설정 되어 있으므로 소수 * 소수 부터 진행하면 됨 -> 이거는 마지막 까지 반복
        for(int i=2; i*i<=primeLength; i++){
            if(!isNotPrime[i]){
                for(int j=i*i; j<=primeLength; j+=i) {
                    isNotPrime[j] = true;                
                }
            }        
        }

        // 백트래킹 실행, sum = 0, depth = 0 부터 시작
        // sum : 현재까지의 합, depth : 현재 깊이
        backtrack(0, 0);

        // 만약 여기까지 왔다면 만족하는 조건을 찾지 못한 것이므로 NO 출력
        System.out.println("NO");

        // long eTime = System.curretTimeMillis();
        // System.out.println(eTime - sTime);
    }

    /**
     * 백트래킹 메서드
     * @param sum   현재까지의 합
     * @param depth 현재 깊이
     */
    public static void backtrack(int sum, int depth) {

        // 만약 현재까지의 합이 소수가 아니면 return
        if (!isNotPrime[sum]) {
            return;
        }

        // 만약 현재 깊이가 N과 같다면 조건이 맞음 -> 합이 소수인 것은 위의 if문에서 걸러짐
        if (depth == N) {
            // 빠른 출력을 위해 sb에 추가
            sb.append("YES\n");
            for (int i : dq) {
                sb.append(i).append(" ");
            }
            
            // 출력 후 프로그램 종료 -> return 하면 또 계속 찾아버리니까 시간적 손해
            System.out.println(sb);     
            System.exit(0);     // 프로그램 강제 종료, status 0 -> 정상 종료
        }

        // 1부터 N까지 반복
        for (int i = 1; i <= N; i++) {
            if (!check[i]) {                    // 방문하지 않았다면
                check[i] = true;                // 방문 체크 하고
                dq.offerLast(i);                // 순열에 해당 수 저장하고
                backtrack(sum + i, depth + 1);  // 다음 재귀로 이동, 합은 해당 수를 추가, 깊이는 1씩 증가
                dq.pollLast();                  // 나오면 순열에서 제거하고
                check[i] = false;               // 방문 체크 해제하면 됨
            }
        }
    }
}
