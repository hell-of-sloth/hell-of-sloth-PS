import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import static java.lang.Integer.parseInt;

public class Main_7512_연속하는소수의합_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int m;
    static int[] n;
    static boolean[] isNotPrime = new boolean[1000000];
    static List<Integer> prime = new ArrayList<>();
    static int[] nIndex; 
    static int[] nSum;
    static int max;
    
    public static void main(String[] args) throws Exception {
        int T = parseInt(br.readLine());
        
        prime();

        for (int t = 1; t <= T; t++) {
            sb.append(String.format("Scenario %d:\n", t));
            sb.append(solve()).append("\n\n");
        }
        System.out.println(sb);
    }

    public static int solve() throws Exception {
        m = parseInt(br.readLine());

        n = new int[m];
        nIndex = new int[m];
        nSum = new int[m];
        max = Integer.MIN_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            n[i] = parseInt(st.nextToken());
        }

        Arrays.sort(n);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n[i]; j++) {
                nSum[i] += prime.get(j);
            }
            max = Math.max(max, nSum[i]);
        }
        
        Loop1: while(true) {
            if(isAllSame(nSum)) {

                if (max >= 1000000) {
                    return -1;
                }

                if (!isNotPrime[max]) {
                    return max;
                } else {
                    for (int i = 0; i < m; i++) {
                        if (nIndex[i] + n[i] >= prime.size()) {
                            return -1;
                        }

                        nSum[i] -= prime.get(nIndex[i]++);
                        nSum[i] += prime.get(nIndex[i] + n[i] - 1);
                        max = Math.max(max, nSum[i]);
                    }
                }

                if (max >= 1000000) {
                    return -1;
                }

            } else {
                for (int i = 0; i < m; i++) {
                    if(nSum[i] < max) {
                        if (nIndex[i] + n[i] >= prime.size()) {
                            return -1;
                        }
    
                        nSum[i] -= prime.get(nIndex[i]++);
                        nSum[i] += prime.get(nIndex[i] + n[i] - 1);
                        max = Math.max(max, nSum[i]);
                    }
                }
    
                if (max >= 1000000) {
                    return -1;
                }
            } 
        }
    }

    public static boolean isAllSame(int[] nSum) {
        for (int i = 0; i < m; i++) {
            if (nSum[0] != nSum[i]) return false;
        }
        return true;
    }

    public static void prime() {
        for(int i = 2; i * i < 1000000; i++) {
            if(isNotPrime[i]) continue;

            for(int j = i * i; j < 1000000; j += i) {
                isNotPrime[j] = true;
            }
        }

        for(int i = 2; i < 1000000; i++) {
            if(!isNotPrime[i]) prime.add(i);
        }
    }
    
}
