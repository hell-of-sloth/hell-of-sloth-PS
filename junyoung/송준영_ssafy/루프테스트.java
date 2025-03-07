public class 루프테스트 {
    public static void main(String[] args) {
        
        L1: for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 5) {
                    break L1;
                }
                System.out.println("i = " + i + ", j = " + j);
            }
        }
    }
}
