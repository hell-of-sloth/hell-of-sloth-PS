#include <iostream>
#include <cassert>
#define endl '\n'
#define ASSERT(exp, msg) assert(exp && msg)
using namespace std;
const int MAX = 1000;
int N;

enum e_color {
    R,
    G,
    B
};

// R = 0, G = 1, B = 2
int RGB[MAX + 1][3], dp[MAX + 1][3];

void solve() {
    dp[1][R] = RGB[1][R];
    dp[1][G] = RGB[1][G];
    dp[1][B] = RGB[1][B];
    for (int i = 2; i <= N; i++) {
        dp[i][R] = min(dp[i - 1][G], dp[i - 1][B]) + RGB[i][R];
        dp[i][G] = min(dp[i - 1][R], dp[i - 1][B]) + RGB[i][G];
        dp[i][B] = min(dp[i - 1][R], dp[i - 1][G]) + RGB[i][B];
    }
    cout << min(min(dp[N][R], dp[N][G]), dp[N][B]) << endl;
}

void read_user_input() {
    cin >> N;
    for (int i = 1; i <= N; i++) {
        cin >> RGB[i][R] >> RGB[i][G] >> RGB[i][B];
    }
}

int main() {
    ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    read_user_input();
    solve();
    return 0;
}
