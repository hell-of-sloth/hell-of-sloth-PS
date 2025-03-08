#include <iostream>
#include <set>
#include <vector>
#include <algorithm>

#define endl '\n'
#define fastio cin.tie(0)->sync_with_stdio(0)
#define REP(x) for (ll idx = 0; idx < (x); ++idx)
#define EACH(x, a) for (const auto &x: (a))
#define watch(x) cout << (#x) << " is " << (x) << endl

using namespace std;
using ll = long long;
using VL = vector<ll>;
const ll IDENTITY = 0, MAX_N = 500'000;
ll gN;
VL v, s, relabelled;
VL segTree(MAX_N * 4 + 7, IDENTITY);

ll operate(ll lhs, ll rhs) {
    return lhs + rhs;
}


ll query(ll root, ll ts, ll te, ll qs, ll qe) {
    if (ts >= qs and te <= qe) return segTree[root];
    if (te < qs or ts > qe) return IDENTITY;
    ll tm = ts + te >> 1;
    return operate(query(root << 1, ts, tm, qs, qe), query(root << 1 | 1, tm + 1, te, qs, qe));
}

void upd(ll root, ll s, ll e, ll ii, ll val) {
    if (ii < s or ii > e) return;
    if (s == e and s==ii) {
        segTree[root] = val;
        return;
    }
    ll m = s + e >> 1;
    upd(root << 1, s, m, ii, val), upd(root << 1 | 1, m + 1, e, ii, val);
    segTree[root] = operate(segTree[root << 1], segTree[root << 1 | 1]);
}

void readInput() {
    cin >> gN;
    REP(gN) {
        ll e;
        cin >> e;
        v.push_back(e), s.push_back(e);
    }
    sort(s.begin(), s.end());
    s.erase(unique(s.begin(), s.end()), s.end());
    relabelled.push_back(0);
    EACH(x, v) {
        ll compressed = (lower_bound(s.begin(), s.end(), x) - s.begin()) + 1;
        relabelled.push_back(compressed);
    }
}

void solve() {
    for (ll i = 1; i <= gN; ++i) {
        cout << i - query(1, 1, gN, 1, relabelled[i] - 1) << endl;
        ll curr = query(1, 1, gN, relabelled[i], relabelled[i]);
        upd(1, 1, gN, relabelled[i], curr + 1);
    }
}

int main() {
    fastio;
    readInput();
    solve();
    return 0;
}