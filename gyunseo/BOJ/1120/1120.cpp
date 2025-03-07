#ifndef _CRT_SECURE_NO_WARNINGS
#define _CRT_SECURE_NO_WARNINGS
#endif

#include <cassert>
#include <deque>
#include <iostream>
#include <string>
#include <vector>

#define fastio cin.tie(0)->sync_with_stdio(0)
#define endl '\n'
#define watch(x) cout << (#x) << " is " << (x) << endl
#define WILD_CARD 0
#define REP(x) for (ll idx = 0; idx < (x); ++idx)
#define PB push_back
#define PF push_front

using namespace std;
using ll = long long;
using VL = vector<ll>;
using DC = deque<char>;
string A, B;
ll ans = (ll)1e9 + 7, diff;
vector<VL> dp;
void readInput() { cin >> A >> B; }

ll getDiscrepeny(DC &A, DC &B) {
    if (A.size() != B.size())
        return -1;
    ll ret = 0;
    for (auto it1 = A.begin(), it2 = B.begin();
         it1 != A.end() and it2 != B.end(); ++it1, ++it2) {
        if (*it1 == WILD_CARD or *it2 == WILD_CARD)
            continue;
        if (*it1 == *it2)
            continue;
        ++ret;
    }
    return ret;
}

ll DFS(DC &dqA, DC &dqB, ll f, ll b) {
    if (dp[f][b] != -1) return dp[f][b];
    if (f + b == diff) {
        REP(f) dqA.PF(WILD_CARD);
        REP(b) dqA.PB(WILD_CARD);
        dp[f][b] = min(ans, getDiscrepeny(dqA, dqB));
        REP(f) dqA.pop_front();
        REP(b) dqA.pop_back();
        return dp[f][b];
    }

    return dp[f][b] = min(DFS(dqA, dqB, f + 1, b), DFS(dqA, dqB, f, b + 1));
}

void solve() {
    deque<char> dqA(A.begin(), A.end()), dqB(B.begin(), B.end());
    diff = dqB.size() - dqA.size();
    dp.assign(diff + 1, VL(diff + 1, -1));
    // cout << "!" << endl;
    cout << DFS(dqA, dqB, 0, 0) << endl;
}

int main() {
    fastio;
    readInput();
    solve();
    return 0;
}