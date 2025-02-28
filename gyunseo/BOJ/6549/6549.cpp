#include <iostream>
#include <cassert>
#include <vector>
#include <algorithm>

#define endl '\n'
#define ASSERT(exp, msg) assert((exp) &&(msg))
#define fastio cin.tie(0)->sync_with_stdio(0)
#define EACH(x, a) for(const auto &x : (a))
#define watch(x) cout << (#x) << " is " << (x) << endl
#define REP(x) for (ll idx = 0; idx < (x); ++idx)

using namespace std;
using ll = long long;
using VL = vector<ll>;

const ll MAX_N = 100'000, MIN_IDENTITY = (ll)1e9 + 7, MIN_IDENTITY_IDX = MAX_N + 6;
ll gN, ans = -1;
VL H(MAX_N + 7, MIN_IDENTITY), segTree(MAX_N * 4 + 7, MIN_IDENTITY_IDX);

ll operate(ll lhs, ll rhs) {
    return H[lhs] < H[rhs] ? lhs : rhs;
}


ll initSegTree(ll root, ll s, ll e) {
    if (s == e) return segTree[root] = s;
    ll m = s + e >> 1;
    return segTree[root] = operate(initSegTree(root << 1, s, m), initSegTree(root << 1 | 1, m + 1, e));
}

ll query(ll root, ll ts, ll te, ll qs, ll qe) {
    if (ts >= qs and te <= qe) return segTree[root];
    if (te < qs or ts > qe) return MIN_IDENTITY_IDX;
    ll m = ts + te >> 1;
    return operate(query(root << 1, ts, m, qs, qe), query(root << 1 | 1, m + 1, te, qs, qe));
}

bool readInput() {
    cin >> gN;
    if (gN == 0) return false;
    for (ll i = 1; i <= gN; ++i) cin >> H[i];
    return true;
}

bool OOB(ll i) {
    if (i <= 0 or i > gN) return true;
    return false;
}

void r(ll s, ll e) {

    // base condition #1 
    if (s == e) {
        ans = max(ans, H[s]);
        return;
    }

    ll mnHIdx = query(1, 1, gN, s, e);
    ans = max(ans, (e - s + 1) * H[mnHIdx]);

    if (!OOB(mnHIdx - 1)) r(s, mnHIdx - 1);
    if (!OOB(mnHIdx + 1)) r(mnHIdx + 1, e);
}

void solve() {
    ans = -1;
    for (ll i = 1; i < MAX_N + 7; ++i) segTree[i] = MIN_IDENTITY_IDX;
    initSegTree(1, 1, gN);
    r(1, gN);
    cout << ans << endl;
}

int main() {
    fastio;

    while (readInput()) solve();
    return 0;
}