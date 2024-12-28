#include <iostream>
#include <cassert>
#include <vector>
#include <algorithm>
#include <tuple>
#include <cmath>

#define endl '\n'
#define fastio cin.tie(0)->sync_with_stdio(0)
#define REP(x) for (ll i = 0; i < x; ++i)
#define watch(x) cout << (#x) << " is " << x << endl
#define EACH(x, a) for (const auto &(x) : (a))
#define I first
#define J second

using namespace std;
using ll = long long;
using vl = vector<ll>;
using pl = pair<ll, ll>;

ll N, ansSum;
vl v;
pl ansPair;

void readInput() {
    cin >> N;
    REP(N) {
        ll e;
        cin >> e;
        v.push_back(e);
        // watch(v.back());
    }
}

void solve() {
    sort(v.begin(), v.end());
    ll lo = 0, hi = N - 1;
    ansSum = (ll)2e9 + 1LL;
    for (; lo < hi;) {
        ll tmpSum = v[lo] + v[hi];
        if (tmpSum == 0) {
            ansSum = 0;
            ansPair = {lo, hi};
            break;
        }

        if (tmpSum > 0) {
            if (abs(tmpSum) < abs(ansSum)) {
                ansSum = tmpSum;
                ansPair = {lo, hi};
            }
            --hi;
            
        } else if (tmpSum < 0 ) {
            if (abs(tmpSum) < abs(ansSum)) {
                ansSum = tmpSum;
                ansPair = {lo, hi};
            }
            ++lo;
        }
    }
    cout << v[ansPair.I] << " " << v[ansPair.J] << endl;
}

int main() {
    fastio;
    readInput();
    solve();
    return 0;
}