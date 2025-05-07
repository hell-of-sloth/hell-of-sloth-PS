#include <bits/stdc++.h>
#include <cassert>

#define endl '\n'
#define watch(x) cout << (#x) << " is " << (x) << endl
#define watch2(x, y) cout << (#x) << ", " << (#y) << " is " << (x) << ", " << (y) << endl
#define EACH(x, a) for (const auto &x: (a))
#define ASSERT(exp, msg) cassert((exp) &&(msg))
#define fastio cin.tie(0)->sync_with_stdio(0)
#define I first
#define J second
using namespace std;
using ll = long long;
using VL = vector<ll>;
using PL = pair<ll, ll>;

VL di({-1, 1, 0, 0}), dj({0, 0, -1, 1});

bool OOB(ll i, ll j, ll N, ll M) {
    if (i < 0 or i >= N) return true;
    if (j < 0 or j >= M) return true;
    return false;
}

PL getPassword(PL s, vector<VL> &board) {
    ll N = board.size(), M = board[0].size();
    queue<PL> q;
    vector<VL> dist(N, VL(M, 0));
    PL ans = {0, 0};
    q.push(s);
    dist[s.I][s.J] = 1;
    while (not q.empty()) {
        auto [ci, cj] = q.front();
        q.pop();
        for (ll k =0; k < 4; ++k) {
            ll ni = ci + di[k], nj = cj + dj[k];
            if (OOB(ni, nj, N, M)) continue;
            if (board[ni][nj] == 0) continue;
            if (dist[ni][nj] > 0) continue;

            dist[ni][nj] = dist[ci][cj] + 1;
            if (ans.first < dist[ni][nj]) {
                ans.first = dist[ni][nj], ans.second = board[s.I][s.J] + board[ni][nj];
            }
            if (ans.first == dist[ni][nj] and ans.second < board[s.I][s.J] + board[ni][nj]) {
                ans.second = board[s.I][s.J] + board[ni][nj];
            }
            q.push({ni, nj});
        }
    }
    return ans;
}

ll solution(vector<VL> &board) {
    // 거리, password
    PL ans = {0, 0};
    ll N = board.size(), M = board[0].size();
    if (N == 1 and M == 1) return board[0][0];
    vector<PL> startPoses;
    for (ll i = 0; i < N; ++i) {
        for (ll j = 0; j < M; ++j) {
            if (board[i][j] > 0) startPoses.push_back({i, j});
        }
    }
    sort(startPoses.begin(), startPoses.end());
    
    EACH(startPos, startPoses) {
        PL tmpAns = getPassword(startPos, board);
        if (ans.first < tmpAns.first) {
            ans.first = tmpAns.first, ans.second = tmpAns.second;
        }
        if (ans.first == tmpAns.first and ans.second < tmpAns.second) {
            ans.second = tmpAns.second;
        }
    }

    return ans.second;
}

int main() {
    fastio;
    ll N, M;
    cin >> N >> M;
    vector<VL> board(N, VL(M, 0));
    for (ll i = 0; i < N; ++i) {
        for (ll j = 0; j < M; ++j) {
            cin >> board[i][j];
        }
    }
    cout << solution(board) << endl;
    return 0;
}