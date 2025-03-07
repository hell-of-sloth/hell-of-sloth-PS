
/**
 * 하노이의 탑
 * 로직 설명
 * 1. n이 1이면 시작점에서 끝점으로 이동
 * 2. n이 1이 아니면
 * 2-1. n-1개를 시작점에서 중간점으로 이동(n-1개)
 * 2-2. 시작점에 남아있는 한 개의 판을 끝점으로 이동
 * 2-3. 중간점에 있는 것을 다 끝점으로 이동
 */
public class 하노이의탑 {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        int n = 8;
        System.out.println(hanoi(n, 1, 2, 3));
        System.out.println(sb.toString());
    }

    public static int hanoi(int n, int start, int mid, int end) {
        if (n == 1) {
            sb.append(String.format(start + " " + end + "\n"));
            return 1;
        }

        int count = 1;

        count += hanoi(n - 1, start, end, mid);
        sb.append(String.format(start + " " + end + "\n"));
        count += hanoi(n - 1, mid, start, end); 

        return count;
    }
}
