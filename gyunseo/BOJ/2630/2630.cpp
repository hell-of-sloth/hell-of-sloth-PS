#include <iostream>
#define endl '\n'
using namespace std;
// N은 2, 4, 8, 16, 32, 64, 128
const int MAX = 128;
int N;
int MATRIX[MAX][MAX];

// size는 2^k에서 k를 의미
// si, sj는 시작좌표
bool is_all_same_color(int size, int si, int sj) {
    int range = 1 << size;
    for (int i = si; i < si + range; i++) {
        for (int j = sj; j < sj + range; j++) {
            if (MATRIX[si][sj] != MATRIX[i][j])
                return false;
        }
    }
    return true;
}

// size는 2^k에서 k를 의미
// si, sj는 시작 좌표
// deli는 흰색이냐 파란색이냐
int recursive(int size, int si, int sj, int deli) {
    // base condition 1
    if (size == 0) {
        if (MATRIX[si][sj] == deli)
            return 1;
        else
            return 0;
    }
    // base condition 2
    if (is_all_same_color(size, si, sj)) {
        if (MATRIX[si][sj] == deli)
            return 1;
        else
            return 0;
    }

    int delta = 1 << (size - 1);
    return recursive(size - 1, si, sj, deli) +
           recursive(size - 1, si, sj + delta, deli) +
           recursive(size - 1, si + delta, sj, deli) +
           recursive(size - 1, si + delta, sj + delta, deli);
}

void solve() {
    int size = 0;
    while(N > 1) {
        N /= 2;
        size++;
    }

    cout << recursive(size, 0, 0, 0) << endl;
    cout << recursive(size, 0, 0, 1) << endl;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> MATRIX[i][j];
        }
    }
    solve();
    return 0;
}