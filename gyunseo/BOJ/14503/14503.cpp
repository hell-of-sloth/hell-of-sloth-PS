#include <bits/stdc++.h>

#define endl '\n'
#define fastio cin.tie(0)->sync_with_stdio(0)
#define watch(x) cout << (#x) << " is " << (x) << endl
#define watch2(x, y) cout << (#x) << ", " << (#y) << " is " << (x) << ", " << (y) << endl
#define EACH(x, a) for (const auto &x : (a))
#define I first
#define J second

using namespace std;
using ll = long long;
using VL = vector<ll>;
using PL = pair<ll, ll>;

const ll UNWIPED = 0, WALL = 1, WIPED = 2;

VL di({-1, 0, 1, 0}), dj({0, 1, 0, -1});

class Robot {
public:
    PL curCoord;
    ll curDir, wipedCnt, n, m;
    vector<VL> &board;
    Robot (ll _si, ll _sj, ll _sd, vector<VL> &_board): board(_board), curCoord({_si, _sj}), curDir(_sd), wipedCnt(0), n(_board.size()), m(_board[0].size()) {
        
    }
    ll modularToNegInf(ll a, ll b) {
        ll q = (ll)floor((double)a / b);
        return a - b * q;
    }
    bool OOB(ll i, ll j) {
        if (i < 0 or i >= n) return true;
        if (j < 0 or j >= m) return true;
        return false;
    }

    void debugBoard() {
        cout << "===" << endl;
        for (ll i = 0; i < n; ++i) {
            for (ll j = 0; j < m; ++j) {
                cout << board[i][j] << " ";
            }cout << endl;
        }
        cout << "===" << endl;
    }

    void operate() {
        while (true) {
            if (board[curCoord.I][curCoord.J] == UNWIPED) {
                board[curCoord.I][curCoord.J] = WIPED;
                ++wipedCnt;
            }
            bool check4dir = false;
            for (ll k = 0; k < 4; ++k) {
                ll ni = curCoord.I + di[k], nj = curCoord.J + dj[k];
                if (OOB(ni, nj)) continue;
                if (board[ni][nj] == WALL) continue;
                if (board[ni][nj] == WIPED) continue;
                check4dir = true;
                break;
            }

            if (check4dir) {
                curDir = modularToNegInf(curDir - 1, 4);
                ll ni = curCoord.I + di[curDir], nj = curCoord.J + dj[curDir];
                if (board[ni][nj] == UNWIPED) curCoord.I = ni, curCoord.J = nj;
            } else {
                ll ni = curCoord.I + di[(curDir + 2) % 4], nj = curCoord.J + dj[(curDir + 2) % 4];
                if (board[ni][nj] == WALL) break;
                curCoord.I = ni, curCoord.J = nj;
            }
        }

    }
};

ll solution(vector<VL> &board, PL startCoord, ll startDir) {
    Robot robot(startCoord.first, startCoord.second, startDir, board);
    robot.operate();
    return robot.wipedCnt;
}

int main() {
    fastio;
    ll n, m;
    vector<VL> board;
    ll si, sj, sd;
    cin >> n >> m;
    cin >> si >> sj >> sd;
    board.assign(n, VL(m, 0));
    for (ll i = 0; i < n; ++i) {
        for (ll j = 0; j < m; ++j) cin >> board[i][j];
    }
    cout << solution(board, {si, sj}, sd);

    return 0;
}