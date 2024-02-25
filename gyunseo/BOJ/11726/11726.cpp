#include <cassert>
#include <iostream>

#define endl '\n'
#define ASSERT(exp, msg) assert(exp &&msg)

using namespace std;

const int MAX = 1000;
int n;
int dp[MAX + 1];

void solve() {
    dp[1] = 1;
    dp[2] = 2;
    for (int i = 3; i <= n; i++) {
        dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
    }
    cout << dp[n] << endl;

}

void read_user_input() {
    cin >> n;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    read_user_input();
    solve();
    return 0;
}
