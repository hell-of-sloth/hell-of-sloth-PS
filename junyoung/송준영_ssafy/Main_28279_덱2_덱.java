// 체감 난이도 1/10, 구현, 덱
// 덱의 간단한 구현, java deque의 명령어를 몰라 검색해보았다...

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_28279_덱2_덱 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder(); // 출력할 결과값 저장 (시간 줄이기)
    static ArrayDeque<Integer> dq = new ArrayDeque<>(); // 덱 사용
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());

        // 명령어 처리
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            if (cmd == 1) {
                insertFront(Integer.parseInt(st.nextToken()));
            } else if (cmd == 2) {
                insertBack(Integer.parseInt(st.nextToken()));
            } else if (cmd == 3) {
                deleteFront();
            } else if (cmd == 4) {
                deleteBack();
            } else if (cmd == 5) {
                printSize();
            } else if (cmd == 6) {
                printEmpty();
            } else if (cmd == 7) {
                printFront();
            } else if (cmd == 8) {
                printBack();
            }
        }
        System.out.println(sb);
    }

    // 앞에 삽입
    static public void insertFront(int x) {
        dq.addFirst(x);
    }

    // 뒤에 삽입
    static public void insertBack(int x) {
        dq.addLast(x);
    }

    // 앞에 삭제, 비어있으면 -1
    static public void deleteFront() {
        if (dq.isEmpty()) {
            sb.append(-1).append("\n");
        } else {
            sb.append(dq.pollFirst()).append("\n");
        }
    }

    // 뒤에 삭제, 비어있으면 -1
    static public void deleteBack() {
        if (dq.isEmpty()) {
            sb.append(-1).append("\n");
        } else {
            sb.append(dq.pollLast()).append("\n");
        }
    }

    // 크기 출력
    static public void printSize() {
        sb.append(dq.size()).append("\n");
    }

    // 비어있으면 1, 아니면 0
    static public void printEmpty() {
        if (dq.isEmpty()) {
            sb.append(1).append("\n");
        } else {
            sb.append(0).append("\n");
        }
    }

    // 앞에 출력, 비어있으면 -1
    static public void printFront() {
        if (dq.isEmpty()) {
            sb.append(-1).append("\n");
        } else {
            sb.append(dq.peekFirst()).append("\n");
        }
    }

    // 뒤에 출력, 비어있으면 -1
    static public void printBack() {
        if (dq.isEmpty()) {
            sb.append(-1).append("\n");
        } else {
            sb.append(dq.peekLast()).append("\n");
        }
    }
}
