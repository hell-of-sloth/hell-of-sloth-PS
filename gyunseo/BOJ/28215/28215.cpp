#include <bits/stdc++.h>
#include <cassert>

#define fastio cin.tie(0)->sync_with_stdio(0)
#define endl '\n'
#define watch(x) cout << (#x) << " is " << (x) << endl
#define ASSERT(exp, msg) assert((exp) &&(msg))
#define I first
#define J second
#define EACH(x, a) for (const auto &x : (a))
using namespace std;
using ll = long long;
using VL = vector<ll>;
using PL = pair<ll, ll>;

ll N, K;
vector<PL> coords;
void readInput() {
    cin >> N >> K;
    for (ll i = 0; i < N; ++i) {
        ll x, y;
        cin >> x >> y;
        coords.push_back({ x, y });
    }
}

ll getTaxiDist(PL x, PL y) {
    return abs(x.I - y.I) + abs(x.J - y.J);
}
void solve() {
    ll ans = (ll)1e9 + 7;
    vector<bool> mask(N, 0);
    fill(mask.begin(), mask.begin() + K, 1);
    sort(mask.begin(), mask.end());
    do {
        vector<PL> combs;
        ll farDist = 0;
        for (ll i = 0; i < N; ++i) {
            if (mask[i]) combs.push_back(coords[i]);
        }

        EACH(coord, coords) {
            ll closeTargetDist = (ll)1e9 + 7;
            EACH(target, combs) {
                closeTargetDist = min(closeTargetDist, getTaxiDist(coord, target));
            }
            farDist = max(farDist, closeTargetDist);
        }
        ans = min(ans, farDist);

    }while(next_permutation(mask.begin(), mask.end()));

    cout << ans << endl;
}

int main() {
    fastio;
    readInput();
    solve();
    return 0;
}