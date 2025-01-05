import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] fruits = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            fruits[i] = Integer.parseInt(st.nextToken());
        }

        // 투 포인터 + Map
        int L = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int answer = 0;

        for (int R = 0; R < N; R++) {
            // 오른쪽 포인터가 가리키는 과일 추가
            map.put(fruits[R], map.getOrDefault(fruits[R], 0) + 1);

            // 서로 다른 과일이 2개를 초과하면 왼쪽 포인터 이동
            while (map.size() > 2) {
                map.put(fruits[L], map.get(fruits[L]) - 1);
                if (map.get(fruits[L]) == 0) {
                    map.remove(fruits[L]);
                }
                L++;
            }

            // 최대 길이 갱신
            answer = Math.max(answer, R - L + 1);
        }

        System.out.println(answer);
    }
}
