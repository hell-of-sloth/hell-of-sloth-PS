#include <bits/stdc++.h>
using namespace std;
#define endl '\n'
#define watch(x) cout << (#x) << " is " << x << endl
#define FOR(s, e) for(int _ = s; _ < e; ++_)
#define PI pair<int, int>
using ll = long long;
const int MAX = 100;
const int di[] = {0, 1}, dj[] = {1,0};
int N, BOARD[MAX + 1][MAX + 1];
ll dp[MAX + 1][MAX + 1];
bool is_visited[MAX + 1][MAX + 1];
queue<PI> Q;

bool OOB(int ci, int cj) {
    if (ci < 0 || ci >= N) return true;
    if (cj < 0 or cj >= N) return true;
    return false;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> BOARD[i][j];
        }
    }
    // Q.push({0, 0});
    // is_visited[0][0] = true;
    // while (!Q.empty()) {
    //     int ci, cj;
    //     tie(ci, cj) = Q.front();
    //     Q.pop();
    //     for (int k = 0; k < 2; k++) {
    //         int ni, nj;
    //         ni = ci + di[k] * BOARD[ci][cj];
    //         nj = cj + dj[k] * BOARD[ci][cj];
    //         if(OOB(ni, nj)) continue;

    //     }
    // }
    dp[0][0] = 1;
    for (int ci = 0; ci < N; ci++) {
        for (int cj = 0; cj < N; cj++) {
            if (BOARD[ci][cj] == 0) continue;
            if (ci == N - 1 and cj == N - 1) continue;
            // cout << "(ci, cj)" << endl;
            // cout << ci << ", " << cj << ": " << dp[ci][cj] << endl;
            for (int k = 0; k < 2; k++) {
                int ni, nj;
                ni = ci + di[k] * BOARD[ci][cj];
                nj = cj + dj[k] * BOARD[ci][cj];
                if(OOB(ni, nj)) continue;
                dp[ni][nj] += dp[ci][cj];
                // cout << "(ni, nj)" << endl;
                // cout << ni << ", " << nj << ": " << dp[ni][nj] << endl;
            } 
        }
    }
    cout << dp[N  - 1][N - 1] << endl;
    return 0;
}