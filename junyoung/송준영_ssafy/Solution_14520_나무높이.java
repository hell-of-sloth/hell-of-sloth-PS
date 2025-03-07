/*
 * 가장 첫 줄에는 테스트 케이스의 총 수가 주어진다. 
 * 그 다음 줄부터 각 테스트 케이스가 주어지며, 각 테스트 케이스는 2줄로 구성된다. 
 * 각 테스트 케이스의 첫째 줄에는 나무의 개수 N이 주어진다. 
 * 다음 줄에는 나무들의 높이가 N개의 자연수로 주어진다
 */

/*
 * 출력의 각 줄은 ‘#x’로 시작하고, 공백을 한 칸 둔 다음 가능한 최소 날짜 수를 출력한다. 
 * 단, x는 테스트 케이스의 번호이다.
 */

import java.io.*;
import java.util.*;

public class Solution_14520_나무높이 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append(String.format("#%d %d\n", t, solve()));
        }
        System.out.println(sb);
    }
    
    public static int solve() throws Exception {
        int N;
        int[] trees;
        int maxTree = 0;
        int result = 0;
        int differ = 0;
        int odd = 0, even = 0;
        int temp = 0;

        int test = 0;

        N = Integer.parseInt(br.readLine());
        trees = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            maxTree = Math.max(maxTree, trees[i]);
        }

        // 차이 저장
        for (int i = 0; i < N; i++) {
            differ += maxTree - trees[i];
            // even += (maxTree - trees[i]) / 3;
            // odd += (maxTree - trees[i]) / 3;
            even += (maxTree - trees[i]) / 2;
            odd += (maxTree - trees[i]) % 2;
            // test += (maxTree - trees[i]) / 3;
            // if ((maxTree - trees[i]) % 3 == 1) {
            //     odd++;
            // } else if ((maxTree - trees[i]) % 3 == 2) {
            //     even++;
            // }
        }

        // System.out.println(odd + " " + even);

        // if (odd > even) {
        //     result += even * 2;
        //     differ = odd - even;
        //     result += (differ / 3) * 2;
        //     result += differ % 3;
        // } else if (odd < even) {
        //     result += odd * 2;
        //     differ = (even - odd) * 2;
        //     result += (differ / 3) * 2;
        //     result += differ % 3;
        // } else {
        //     result += even * 2;
        // }
        
        if (odd > even) {
            result += even * 2;
            result += (odd - even) * 2 - 1;
        } else if (odd < even) {
            result += odd * 2;
            result += ((even-odd) / 3) * 4;
            result += ((even-odd) % 3) + 1;
        } else {
            result += even * 2;
        }


        // result += (even / 3) * 4;
        // result += (even % 3) + 1;
        // result += (odd * 2) - 1;
        // System.out.println(even);
        // System.out.println(odd);
        // System.out.println(even % 3);

        return result;
    }
}
