#include <iostream>
#include <vector>
#include <tuple>

#define F first
#define S second

#define FOR(idx, s, e) for (ll idx = (s); idx < (e); ++idx)
#define EACH(x, a) for (auto const &(x) : (a))

#define VL vector<ll>
#define PL pair<ll, ll>

#define endl '\n'
#define watch(x) cout << (#x) << " is " << x << endl
#define ASSERT(exp, msg) assert((exp) && (msg))
#define fastio cin.tie(0)->sync_with_stdio(0)

using namespace std;
using ll = long long;

ll N, M, currN, currM;
char board[17][17], board2[17][17], tmp[17][17];
PL redPos, bluePos, holePos;

void readInput() {
    cin >> N >> M;
    cin.ignore();
    string s;
    FOR(i, 0, N) {
        getline(cin, s);
        FOR(j, 0, M) {
            board[i][j] = s[j];
            // watch(board[i][j]);
            if (board[i][j] == 'R') {
                redPos.F = i; redPos.S = j;
            }
            else if (board[i][j] == 'B') {
                bluePos.F = i; bluePos.S = j;
            }
            else if (board[i][j] == 'O') {
                holePos.F = i; holePos.S = j;
            }
        }
    }
}

ll customPow(ll n, ll x) {
    ll ret = 1;
    while(x) {
        ret *= n;
        --x;
    }
    return ret;
}

void rotateClockwise() {
    FOR(i, 0, currN) {
        FOR(i, 0, currM) {
            tmp[j][currN - i - 1] = board2[i][j];
        }
    }

    FOR(i, 0, currM) {
        FOR(j, 0, currN) {
            board2[i][j] = tmp[i][j];
        }
    }
    swap(currN, currM);
}

void tiltLeft() {
    // tilt logic
    FOR(i, 0, currN) {
        ll p = 1;
        while (p != '.') ++p;

    }
}

void tiltUp() {
    // rotate * 3, and tiltLeft()
    FOR(i, 0, 3) rotateClockwise();
    tiltLeft();
    // rotate
    rotateClockwise();
}

void tiltRight() {
    // rotate * 2, and tiltLeft()
    FOR(i, 0, 2) rotateClockwise();
    tiltLeft();
    // rotate * 2
    FOR(i, 0, 2) rotateClockwise();
}

void tiltDown() {
    // roate, and tiltLeft()
    rotateClockwise();
    tiltLeft();
    // rotate * 3
    FOR(i, 0, 3) rotateClockwise();
}

void copyBoardToBoard2() {
    FOR(i, 0, N) {
        FOR(i, 0, M) {
            board2[i][j] = board[i][j];
        }
    }
}
void solve() {
    ll num_all_cases = customPow(4, 10);
    for (ll x = 0; x < num_all_cases; ++x) {
        ll y = x;
        copyBoardToBoard2();
        currN = N; currM = M;
        while (y) {
            ll tilt_dir = y % 4;
            y /= 4;
            if (tilt_dir == 0) tiltLeft();
            else if (tilt_dir == 1) tiltUp();
            else if (tilt_dir == 2) tiltRight();
            else if (tilt_dir == 3) tiltDown();
        }
    }
}

int main() {
    fastio;
    readInput();
    solve();
    return 0;
}