#include <algorithm>
#include <iostream>
#define endl '\n'

using namespace std;
const int MAX = (int)1e6;
int N, dp[MAX + 1], pre[MAX + 1];
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    fill(dp, dp + MAX + 1, (int)1e9);
    dp[1] = 0, dp[2] = 1, dp[3] = 1;
    pre[1] = -1, pre[2] = 1, pre[3] = 1;
    for (int i = 4; i <= N; i++) {
        if (i % 3 == 0) {
            if (dp[i / 3] < dp[i]) {
                dp[i] = dp[i / 3];
                pre[i] = i / 3;
            }
        }
        if (i % 2 == 0) {
            if (dp[i / 2] < dp[i]) {
                dp[i] = dp[i / 2];
                pre[i] = i / 2;
            }
        }
        if (dp[i - 1] < dp[i]) {
            dp[i] = dp[i - 1];
            pre[i] = i - 1;
        }
        dp[i]++;
    }

    cout << dp[N] << endl;
    // for (int i = 1; i <= N - 1; i++) {
    // cout << "dp[" << i << "]: " << dp[i] << " ";
    //}
    int idx = N;
    cout << N << " ";
    while (pre[idx] != -1) {
        cout << pre[idx] << " ";
        idx = pre[idx];
    }
    cout << endl;
    return 0;
}
