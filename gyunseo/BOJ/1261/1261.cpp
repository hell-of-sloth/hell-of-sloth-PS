#include <bits/stdc++.h>
#include <cassert>

#define endl '\n'
#define fastio cin.tie(0)->sync_with_stdio(0)
#define ASSERT(exp, msg) assert((exp) &&(msg))
#define EACH(x, a) for (const auto &x: (a))
#define watch(x) cout << (#x) << " is " << (x) << endl
#define watch2(x, y) cout << "(" (#x) << ", " << (#y) << ")" << " is " << "(" << (x) << ", " << (y) << ")" << endl
#define I first
#define J second

using namespace std;
using ll = long long;
using VL = vector<ll>;
using PL = pair<ll, ll>;


const ll MAX_N = 100, INF = (ll)1e9 + 7;
ll M, N, ans = 0;
vector<VL> board(MAX_N + 7, VL(MAX_N + 7, 0)), dist(MAX_N + 7, VL(MAX_N + 7, INF));
VL di({-1, 1, 0, 0}), dj({0, 0, -1, 1});
vector<vector<PL>> prevCoord(MAX_N + 7, vector<PL>(MAX_N + 7, {-1, -1}));

void debugMatrix(vector<vector<ll>> &mat) {
    for (ll i = 0; i < N; ++i) {
        for (ll j = 0; j < M; ++j) {
            cout << mat[i][j] << " ";
        }cout << endl;
    }
}
void readInput() {
    cin >> M >> N;
    cin.ignore();
    for (ll i = 0; i < N; ++i) {
        string s;
        getline(cin, s);
        for (ll j = 0; j < s.size(); ++j) board[i][j] = s[j] == '1' ? 1 : 0;
    }

}

bool OOB(ll i , ll j) {
    if (i < 0 or i >= N) return true;
    if (j < 0 or j >= M) return true;
    return false;
}

void dijkstra(ll si, ll sj) {
    priority_queue<tuple<ll, ll, ll>, vector<tuple<ll, ll, ll>>, greater<>> pq;
    dist[si][sj] = 0;
    pq.push({dist[si][sj], 0, 0});

    while(not pq.empty()) {
        auto [cd, ci, cj] = pq.top();
        pq.pop();
        // 이미 다른 더 작은 걸로 업데이트 돼 있었다면 패스하기
        if (dist[ci][cj] != cd) continue;

        for (ll k = 0; k < 4; ++k) {
            ll ni = ci + di[k], nj = cj + dj[k];
            if (OOB(ni, nj)) continue;
            ll nd = dist[ci][cj] + (board[ni][nj] == 1 ? 1 : 0);
            if (nd < dist[ni][nj]) {
                dist[ni][nj] = nd, prevCoord[ni][nj] = {ci, cj}, pq.push({nd, ni, nj});
            }
        }
    }
}

void backtrack(PL &coord) {
    // (-1, -1)이면 base condition
    if (coord.I == -1 and coord.J == -1) return;

    if (board[coord.I][coord.J] == 1) ++ans;

    backtrack(prevCoord[coord.I][coord.J]);

}
void solve() {
    if (N == 1 and M == 1) {
        cout << 0 << endl;
        return;
    }
    // debugMatrix(board);
    dijkstra(0, 0);
    // PL dst = {N - 1, M - 1};
    // backtrack(dst);
    cout << dist[N - 1][M - 1] << endl;
}

int main() {
    fastio;
    readInput();
    solve();
    return 0;
}