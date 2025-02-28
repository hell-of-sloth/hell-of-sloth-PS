#include <iostream>
#include <vector>
#include <algorithm>
#include <set>

#define endl '\n'
#define fastio cin.tie(0)->sync_with_stdio(0)
#define REP(x) for (ll idx = 0; idx < (x); ++idx)

using namespace std;
using ll = long long;
using VL = vector<ll>;

ll gN;
VL v;
set<ll> s;

void readInput() {
    cin >> gN;
    REP(gN) {
        ll e;
        cin >> e;
        v.push_back(e);
        s.insert(e);
    }
}

void solve() {
    VL sortedSet(s.begin(), s.end());
    for (const auto &x : v) {
        cout << lower_bound(sortedSet.begin(), sortedSet.end(), x) - sortedSet.begin() << " ";
    }
}

int main() {
    fastio;
    readInput();
    solve();
    return 0;
}