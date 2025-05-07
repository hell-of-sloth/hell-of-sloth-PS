#include <bits/stdc++.h>
#include <cassert>
#define endl '\n'
#define ASSERT(exp, msg) assert((exp) &&(msg))
#define watch(x) cout << (#x) << " is " << (x) << endl
#define watch2(x, y) cout << (#x) << ", " << (#y) << " is " << (x) << ", " << (y) << endl
#define EACH(x, a) for (const auto &x: (a))
#define fastio cin.tie(0)->sync_with_stdio(0)

using namespace std;
using ll = long long;
using VL = vector<ll>;
using PL = pair<ll, ll>;
using VB = vector<bool>;
PL getLoc(vector<VL> &board, ll n) {
    for (ll i = 0; i < 5; ++i) {
        for (ll j = 0; j< 5; ++j) {
            if (board[i][j] == n) return { i, j };
        }
    }
}

// 3개 이상의 빙고가 잇는지 체크한다
bool threeBingosExists(vector<VB> &isChecked) {
    ll bingoCnt = 0;
    for (ll i = 0; i < 5; ++i) {
        bool andSum = true;
        for (ll j = 0; j < 5; ++j) andSum = andSum and isChecked[i][j];
        if (andSum) ++bingoCnt;
    }

    for (ll j = 0; j < 5; ++j) {
        bool andSum = true;
        for (ll i = 0; i < 5; ++i) andSum = andSum and isChecked[i][j];
        if (andSum) ++bingoCnt;
    }

    bool andSum1 = true, andSum2 = true;
    for (ll i = 0; i < 5; ++i) {
        andSum1 = andSum1 and isChecked[i][i];
        andSum2 = andSum2 and isChecked[4 - i][i];
    }
    if (andSum1) ++bingoCnt;
    if (andSum2) ++bingoCnt;

    return bingoCnt >= 3;

}

ll solution(vector<VL> &board, VL &seq) {
    vector<vector<bool>> isChecked(5, vector<bool>(5, false));
    for (ll i = 0; i < seq.size(); ++i) {
        auto [ci, cj] = getLoc(board, seq[i]);
        isChecked[ci][cj] = true;
        if (threeBingosExists(isChecked)) return i;
    }
}

int main() {
    fastio;
    VL seq;
    vector<VL> board(5, VL(5, 0));
    for (ll i = 0; i < 5; ++i) {
        for (ll j = 0; j < 5; ++j) {
            cin >> board[i][j];
        }
    }
    for (ll i = 0; i < 5; ++i) {
        for (ll j = 0; j < 5; ++j) {
            ll e;
            cin >> e;
            seq.push_back(e);
        }
    }
    cout << solution(board, seq) + 1 << endl;
    return 0;
}