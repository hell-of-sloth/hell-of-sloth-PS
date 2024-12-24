#include <iostream>
#include <cassert>
#include <algorithm>
#include <vector>

#define fastio cin.tie(0)->sync_with_stdio(0)
#define endl '\n'
#define ASSERT(exp, msg) assert((exp) && (msg))
#define VL vector<ll>
#define FOR(x, s, e) for(ll (x) = (ll)(s); (x) < (e); ++(x))

using namespace std;
using ll = long long;

ll N, K;
VL v;

void readInput() {
    cin >> N >> K;
    FOR(i, 0, N) {
        ll e;
        cin >> e;
        v.push_back(e);
    }
}

void solve() {
    sort(v.begin(), v.end());
    ASSERT(K - 1 >= 0 && K - 1 < N, "K - 1 is within the valid range");
    cout << v[K - 1] << endl;
}

int main() {
    fastio;
    readInput();
    solve();
    return 0;
}