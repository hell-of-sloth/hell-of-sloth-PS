#include <iostream>
#include <vector>
#include <cassert>
#include <set>
#include <tuple>

#define fastio cin.tie(0)->sync_with_stdio(0)
#define endl '\n'
#define watch(x) cout << (#x) << " is " << (x) << endl
#define EACH(x, a) for (const auto &x : (a))
#define ASSERT(exp, msg) assert((exp) &&(msg))

using namespace std;
using ll = long long;
using VL = vector<ll>;
using PL = pair<ll, ll>;

ll H, W;
vector<VL> board(507, VL(507, 0));
set<PL> coords, ans;

void debugBoard() {
    for (ll i = 0; i < H; ++i) {
        for (ll j = 0; j < W; ++j) {
            cout << board[i][j] << " ";
        }cout << endl;
    }
}

void readInput() {
    cin >> H >> W;
    for (ll j = 0; j < W; ++j) {
        ll height;
        cin >> height;
        for (ll i = 0; i < height; ++i) {
            board[i][j] = 1;
        }
    }
    // debugBoard();
}

bool OOB(ll i , ll j) {
    if (i < 0 or i >= H) return true;
    if (j < 0 or j >= W) return true;
    return false;
}

bool fn(ll si, ll sj, ll dir) {
    if (sj == 0 or sj == W - 1) {
        if (board[si][sj] == 0) return false;
        else return true;
    }
    if (board[si][sj] == 1) return true;

    if (dir == 0) return fn(si, sj - 1, -1) and fn(si, sj + 1, 1);
    if (dir == -1) return fn(si, sj - 1, -1);
    if (dir == 1) return fn(si, sj + 1, 1);
}

void solve() {
    for (ll i = 0; i < H; ++i) {
        for (ll j = 0; j < W; ++j) {
            if (board[i][j] == 0) coords.insert({ i, j });
        }
    }
    
    for (auto it = coords.begin(); it != coords.end(); ++it) {
        auto [ci, cj] = *it;
        if (fn(ci, cj, 0)) {
            ans.insert(*it);
        }
    }

    cout << ans.size() << endl;
}

int main() {
    fastio;
    readInput();
    solve();
    return 0;
}