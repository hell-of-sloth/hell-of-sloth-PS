#include <iostream>
#include <vector>

#define fastio cin.tie(0)->sync_with_stdio(0)
#define endl '\n'
#define REP(x) for (ll idx = 0; idx < (x); ++idx)
#define watch(x) cout << (#x) << " is " << (x) << endl

using namespace std;
using ll = long long;
using VL = vector<ll>;

const ll MAX_X = 2'000'000;

struct SegmentTree {

    VL tree;

    SegmentTree() { tree.assign(4 * MAX_X + 7, 0); }
    
    void upd(ll root, ll s, ll e, ll ii, ll val) {
        if (ii < s or ii > e) return;
        if (s == e and ii == s) {
            tree[root] = val;
            return;
        }
        ll m = s + e >> 1;
        upd(root << 1, s, m, ii, val), upd(root << 1 | 1, m + 1, e, ii, val);
        tree[root] = tree[root << 1] + tree[root << 1 | 1];
    }

    ll query(ll root, ll ts, ll te, ll qs, ll qe) {
        if (qe < ts or qs > te) return 0;
        if (ts >= qs and te <= qe) return tree[root];
        ll m = ts + te >> 1;
        return query(root << 1, ts, m, qs, qe) + query(root << 1 | 1, m + 1, te, qs, qe);
    }

    ll findKth(ll root, ll ts, ll te, ll k) {
        if (ts == te) return ts;
        ll m = ts + te >> 1;
        ll left = tree[root << 1];
        if (left >= k) return findKth(root << 1, ts, m, k);
        return findKth(root << 1 | 1, m + 1, te, k - left);
    }
};

ll gN;
void readInput() {
    cin >> gN;
    SegmentTree segmentTree;
    REP(gN) {
        ll T, X, prevVal;
        cin >> T >> X;
        

        if (T == 1) {
            prevVal = segmentTree.query(1, 1, MAX_X, X, X);
            // watch(prevVal);
            segmentTree.upd(1, 1, MAX_X, X, prevVal + 1);
        }
        else if (T == 2) {
            ll kthVal = segmentTree.findKth(1, 1, MAX_X, X);
            cout << kthVal << endl;
            prevVal = segmentTree.query(1, 1, MAX_X, kthVal, kthVal);
            segmentTree.upd(1, 1, MAX_X, kthVal, prevVal - 1);
        }
    }
}

int main() {
    fastio;
    readInput();
    return 0;
}