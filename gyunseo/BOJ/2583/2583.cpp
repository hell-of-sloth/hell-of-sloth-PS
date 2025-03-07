#include <iostream>
#include <vector>
#include <algorithm>
#include <cassert>
#include <tuple>
#include <queue>

#define endl '\n'
#define fastio cin.tie(0)->sync_with_stdio(0)
#define ASSERT(exp, msg) assert((exp) &&(msg))
#define REP(x) for (ll idx = 0; idx < (x); ++idx)
#define watch(x) cout <<  (#x) << " is " << (x) << endl
#define EACH(x, a) for (const auto &x : (a))
#define I first
#define J second

using namespace std;
using ll = long long;
using VL = vector<ll>;
using PL = pair<ll, ll>;

ll gM, gN, gK;
vector<VL> board;
VL areas;
VL di({-1, 1, 0, 0}), dj({0, 0, -1, 1});

void debugBoard() {
    for (ll i = 0; i < gM; ++i) {
        for (ll j = 0; j < gN; ++j) {
            cout << board[i][j] << " ";
        }cout << endl;
    }
}

void readInput() {
    cin >> gM >> gN >> gK;
    board.assign(gM, VL(gN, 0));
    REP(gK) {
        ll j1, i1, j2, i2;
        cin >> j1 >> i1 >> j2 >> i2;
        for (ll i = i1; i < i2; ++i) {
            for (ll j = j1; j < j2; ++j) ++board[i][j];
        }
    }
}

bool OOB(ll ci, ll cj) {
    if (ci < 0 or ci >= gM) return true;
    if (cj < 0 or cj >= gN) return true;
    return false;
}

ll BFS(ll si, ll sj) {
    ll ret = 1;
    queue<PL> q({make_pair(si, sj)});
    board[si][sj] = 1;
    while (!q.empty()) {
        ll ci, cj;
        tie(ci, cj) = q.front();
        q.pop();
        for (auto it1 = di.begin(), it2 = dj.begin(); it1 != di.end() and it2 != dj.end(); ++it1, ++it2) {
            ll ni = ci + *it1, nj = cj + *it2;
            if (OOB(ni, nj)) continue;
            if (board[ni][nj]) continue;
            board[ni][nj] = board[ci][cj] + 1;
            ++ret;
            q.push({ni, nj});
        }
    }
    return ret;
}

void solve() {
    // debugBoard();
    for (ll i = 0; i < gM; ++i) {
        for (ll j = 0; j < gN; ++j) {
            if (board[i][j]) continue;
            areas.push_back(BFS(i, j));
        }
    }
    sort(areas.begin(), areas.end());
    cout << areas.size() << endl;
    EACH(area, areas) cout << area << " ";
}

int main() {
    fastio;
    readInput();
    solve();
    return 0;
}