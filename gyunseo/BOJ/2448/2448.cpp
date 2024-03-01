#include <cstring>
#include <iostream>
#define endl '\n'
using namespace std;

// 첫째 줄에 N이 주어진다. N은 항상 3×2^k 수이다. (3, 6, 12, 24, 48, ...) (0 ≤ k
// ≤ 10, k는 정수)
//  3 ~ 3072(3 * 1024)
const int MAX = 3072;
int N;
char matrix[MAX + 100][MAX * 2 + 100];
// size는 3 * 2^k에서 k를 의미, k == 0 일 때 base condition

// size가 3 * 2^0 일 때
void update_base_matrix(int si, int sj) {
    matrix[si][sj + 2] = '*';

    matrix[si + 1][sj + 1] = '*';
    matrix[si + 1][sj + 3] = '*';

    for (int j = 0; j < 5; j++) {
        matrix[si + 2][sj + j] = '*';
    }
}

void recursion(int size, int si, int sj) {
    if (size == 0) {
        update_base_matrix(si, sj);
        return;
    }
    int delta = 3 * (1 << (size - 1));
    recursion(size - 1, si, sj + delta);
    recursion(size - 1, si + delta, sj);
    recursion(size - 1, si + delta, sj + delta * 2);
}
void solve() {
    int s = 0, tmp_n = N;

    tmp_n = tmp_n / 3;
    while (tmp_n > 1) {
        tmp_n /= 2;
        s++;
    }
    // cout << s << endl;
    memset(matrix, ' ', sizeof(matrix));
    recursion(s, 0, 0);
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < 2 * N; j++) {
            cout << matrix[i][j];
        }
        cout << endl;
    }
}
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    solve();
    return 0;
}