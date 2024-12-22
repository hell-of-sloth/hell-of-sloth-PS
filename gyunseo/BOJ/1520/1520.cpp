#include <iostream>
#include <algorithm>

#define endl '\n'
#define fastio cin.tie(0)->sync_with_stdio(0)
#define watch(x) cout << (#x) << " is " << x << endl

using namespace std;
using ll = long long;

const ll MAX_NUM = 500;
ll M, N;
ll board[MAX_NUM + 7][MAX_NUM + 7], dp[MAX_NUM + 7][MAX_NUM + 7];
ll di[4] = {-1, 1, 0, 0}, dj[4] = {0, 0, -1, 1};

bool OOB(ll ci, ll cj) {
    if (ci < 0 or ci >= M) return true;
    if (cj < 0 or cj >= N) return true;
    return false;
}

void readInput() {
    cin >> M >> N;
    for (ll i = 0; i < M; ++i) {
        for (ll j = 0; j < N; ++j) {
            cin >> board[i][j];
            // watch(board[i][j]);
        }
    }
}

ll dfs(ll ci, ll cj) {
    // base condition
    if (ci == M - 1 and cj == N - 1) {
        return dp[M - 1][N - 1];
    }
    // mark as grey
    dp[ci][cj] = 0;

    for (ll k = 0; k < 4; ++k) {
        ll ni = ci + di[k], nj = cj + dj[k];
        // out of bound
        if (OOB(ni, nj)) continue;
        if (board[ci][cj] <= board[ni][nj]) continue;
        // 회색은 탐색 중이라는 뜻
        if (dp[ni][nj] == 0) continue;
        if (dp[ni][nj] > 0) {
            dp[ci][cj] += dp[ni][nj];
            continue;
        }
        dp[ci][cj] += dfs(ni, nj);
    }

    return dp[ci][cj];
}

void solve() {
    fill(&dp[0][0], &dp[0][0] + (MAX_NUM + 7) * (MAX_NUM + 7), -1);
    // init pos (0, 0)
    // -1 for white, 0 for grey, int lt than 0 for black
    dp[0][0] = 0;
    dp[M - 1][N - 1] = 1;
    dfs(0, 0);
    cout << dp[0][0];
}

int main() {
    fastio;
    readInput();
    solve();
    return 0;
}