#include <iostream>
#include <vector>
#include <tuple>
#include <cassert>
#include <queue>

#define fastio cin.tie(0)->sync_with_stdio(0)
#define endl '\n'
#define ASSERT(exp, msg) assert((exp) &&(msg))
#define REP(x) for (ll idx = 0; idx < (x); ++idx)
#define EACH(x, a) for (const auto &x : (a))
#define watch(x) cout << (#x) << " is " << (x) << endl

using namespace std;
using ll = long long;
using VL = vector<ll>;
using PL = pair<ll, ll>;

const ll MAX_N = 100;
ll gN;
vector<VL> board(MAX_N + 7, VL(MAX_N + 7, 0)), dist(MAX_N + 7, VL(MAX_N + 7, 0));
ll di[4] = {0, 0, -1, 1}, dj[4] = {-1, 1, 0, 0};
ll ans = (ll)1e9 + 7;

void readInput() {
    cin >> gN;
    for (ll i = 0; i < gN; ++i) {
        for (ll j = 0; j < gN; ++j) {
            cin >> board[i][j];
        }
    }
}

// N^2
void initDist() {
    for (ll i = 0; i < gN; ++i) {
        for (ll j = 0; j <gN; ++j) dist[i][j] = 0;
    }
}

void initDistPartially() {
    for (ll i = 0; i < gN; ++i) {
        for (ll j = 0; j < gN; ++j)
            if (board[i][j] == 0) dist[i][j] = 0;
            else continue;
    }
}
bool OOB(ll ci, ll cj) {
    if (ci < 0 or ci >= gN) return true;
    if (cj < 0 or cj >= gN) return true;
    return false;
}

void debugBoard() {
    for (ll i = 0; i < gN; ++i) {
        for (ll j = 0; j < gN; ++j) {
            cout << board[i][j] << " ";
        }cout << endl;
    }
}
void BFS1(ll si, ll sj, ll id) {
    queue<PL> q;

    dist[si][sj] = 1, board[si][sj] = id;
    q.push({si, sj});
    while (!q.empty()) {
        ll ci, cj;
        tie(ci, cj) = q.front();
        q.pop();
        for (ll k = 0; k < 4; ++k) {
            ll ni = ci + di[k], nj = cj + dj[k];
            if (OOB(ni, nj)) continue;
            if (dist[ni][nj] > 0) continue;
            if (board[ni][nj] == 0) continue;

            dist[ni][nj] = dist[ci][cj] + 1, board[ni][nj] = id;
            q.push({ni, nj});
        }
    }
}

void BFS2(ll si, ll sj, ll curID) {
    queue<PL> q;

    dist[si][sj] = 1;
    q.push({si, sj});
    while (!q.empty()) {
        ll ci, cj;
        tie(ci, cj) = q.front();
        q.pop();
        for (ll k = 0; k < 4; ++k) {
            ll ni = ci + di[k], nj = cj + dj[k];
            if (OOB(ni, nj)) continue;
            if (dist[ni][nj] > 0) continue;

            if (board[ni][nj] > 0 and board[ni][nj] != curID) {
                ans = min(ans, dist[ci][cj] - 1);
                return;
            }

            if (board[ni][nj] > 0) dist[ni][nj] = 1;
            else dist[ni][nj] = dist[ci][cj] + 1;
            q.push({ni, nj});
        }
    }
}

void solve() {
    // divide islands
    ll islandID = 1;
    for (ll i = 0; i < gN; ++i) {
        for (ll j = 0; j <gN; ++j) {
            if (dist[i][j] == 0 and board[i][j] == 1) {
                BFS1(i, j, islandID);
                ++islandID;
            }
            else continue;
        }
    }

    // debugBoard();
    initDist();
    for (ll i = 0; i < gN; ++i) {
        for (ll j = 0; j < gN; ++j) {
            if (dist[i][j] == 0 and board[i][j] >= 1) {
                initDist();
                BFS2(i, j, board[i][j]);
            }
            else continue;
        }
    }

    cout << ans << endl;
}

int main() {
    fastio;
    readInput();
    solve();
    return 0;
}