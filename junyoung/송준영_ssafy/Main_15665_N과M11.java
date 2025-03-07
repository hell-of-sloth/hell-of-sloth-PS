
import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main_15665_N과M11 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static Set<Integer> duple = new TreeSet<>();
    static int[] arr;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            duple.add(parseInt(st.nextToken()));
        }
        
        // 혹시 toArray() 잘 쓰시는 분?
        arr = duple.stream().mapToInt(Integer::intValue).toArray();
        backtrack(0, new StringBuilder());

        System.out.println(sb);
    }
    
    public static void backtrack(int depth, StringBuilder temp) {
        if (M == depth) {
            sb.append(temp).append("\n");
            return;
        }

        int len = temp.length();

        for (int i = 0; i < arr.length; i++) {
            temp.append(arr[i]).append(" ");
            backtrack(depth + 1, temp);
            temp.setLength(len);
        }
    }
}
