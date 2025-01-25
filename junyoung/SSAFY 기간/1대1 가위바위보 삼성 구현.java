// 체감 난이도 1/10

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String args[]) throws Exception {
        int A, B;
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        if (A == 1) {
            if (B == 2) {
                System.out.println("B");
            } else {
                System.out.println("A");
            }
        } else if (A == 2) {
            if (B == 1) {
                System.out.println("A");
            } else {
                System.out.println("B");
            }
        } else {
            if (B == 1) {
                System.out.println("B");
            } else {
                System.out.println("A");
            }
        }
    }
}
