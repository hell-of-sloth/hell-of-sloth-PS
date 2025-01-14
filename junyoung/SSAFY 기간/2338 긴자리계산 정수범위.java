// 체감 난이도 3/10 정수 범위
// 브5 지만 나에게 큰 가르침을 준 문제
// 자바에서 큰 수는 BigInteger를 이용 (무한 범위)
// 초기화는 String으로 받아서 BigInteger로 변환
// 연산은 BigInteger의 메소드를 이용

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        BigInteger A = new BigInteger(br.readLine());
        BigInteger B = new BigInteger(br.readLine());
        
        System.out.println(A.add(B));       // 덧셈
        System.out.println(A.subtract(B));  // 뺄셈
        System.out.println(A.multiply(B));  // 곱셈
    }
}
