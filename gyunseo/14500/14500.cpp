#include <bits/stdc++.h>
#include <cassert>

#define endl '\n'
#define watch(x) cout << (#x) << " is " << (x) << endl
#define watch2(x, y)                                                           \
    cout << (#x) << ", " << (#y) << " is " << (x) << ", " << (y) << endl
#define EACH(x, a) for (const auto x : (a))
#define I first
#define J second
#define ASSERT(exp, msg) assert((exp) && (msg))
#define fastio cin.tie(0)->sync_with_stdio(0)

using namespace std;
using ll = long long;
using VL = vector<ll>;
using PL = pair<ll, ll>;
using VB = vector<bool>;

VL di({-1, 1, 0, 0}), dj({0, 0, -1, 1});
ll N, M, ans = 0;
bool OOB(ll i, ll j) {
    if (i < 0 or i >= N)
        return true;
    if (j < 0 or j >= M)
        return true;
    return false;
}

void dfs(ll ci, ll cj, set<PL> &visited, vector<VL> &board) {

    if (visited.size() == 4) {
        ll tmpAns = 0;
        for (auto it = visited.begin(); it != visited.end(); ++it) {
            auto [i, j] = *it;
            tmpAns += board[i][j];
        }
        ans = max(ans, tmpAns);
        return;
    }

    set<PL> aroundCoords;
    for (ll k = 0; k < 4; ++k) {
        ll ni = ci + di[k], nj = cj + dj[k];
        if (OOB(ni, nj))
            continue;
        if (visited.count({ni, nj}) > 0)
            continue;
        aroundCoords.insert({ni, nj});
    }

    if (visited.size() <= 2) {
        vector<PL> aroundCoordsVec(aroundCoords.begin(), aroundCoords.end());
        for (ll i = 0; i < aroundCoordsVec.size(); ++i) {
            for (ll j = i + 1; j < aroundCoordsVec.size(); ++j) {
                PL a = aroundCoordsVec[i], b = aroundCoordsVec[j];
                visited.insert(a), visited.insert(b);
                dfs(a.I, a.J, visited, board), dfs(b.I, b.J, visited, board);
                visited.erase(a), visited.erase(b);
            }
        }
    }

    for (auto it = aroundCoords.begin(); it != aroundCoords.end(); ++it) {
        PL a = *it;
        visited.insert(a);
        dfs(a.I, a.J, visited, board), visited.erase(a);
    }
}
ll solution(vector<VL> &board) {
    for (ll i = 0; i < N; ++i) {
        for (ll j = 0; j < M; ++j) {
            set<PL> visited({{i, j}});
            dfs(i, j, visited, board);
        }
    }
    return ans;
}

int main() {
    fastio;
    cin >> N >> M;
    vector<VL> board(N, VL(M, 0));
    for (ll i = 0; i < N; ++i) {
        for (ll j = 0; j < M; ++j)
            cin >> board[i][j];
    }
    cout << solution(board) << endl;
    return 0;
}