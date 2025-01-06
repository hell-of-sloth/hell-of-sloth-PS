// 체감 난이도 1/10
// toCharArray()로 char 배열로 변환 후 Character.getNumericValue()로 int로 변환


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String args[]) throws Exception {
        int answer = 0;
        String N = br.readLine();
        for (char i : N.toCharArray()) {            // N을 char 배열로 변환
            answer += Character.getNumericValue(i); // char를 int로 변환, i - '0'으로도 가능
        }
        System.out.println(answer);
    }
}
