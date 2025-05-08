#include <bits/stdc++.h>
#include <cassert>

#define endl '\n'
#define fastio cin.tie(0)->sync_with_stdio(0)
#define watch(x) cout << (#x) << " is " << (x) << endl
#define watch2(x, y) cout << (#x) << ", " << (#y) << " is " << (x) << ", " << (y) << endl
#define TIME first
#define MOS_CNT second

using namespace std;
using ll = long long;

ll N, maxMosCnt = 0, maxRangeS = -1, maxRangeE = -1;
map<ll, ll> diff;
int main() {
    fastio;
    cin >> N;
    for (ll i = 0; i < N; ++i) {
        ll TE, TX;
        cin >> TE >> TX;
        diff[TE] += 1, diff[TX] -=1;
    }
    auto it = diff.begin(), prev = it++;
    ll mosCnt = 0;
    for (;it != diff.end();++prev, ++it) {
        ll s = prev->TIME, e = it->TIME - 1;
        // watch(prev->MOS_CNT);
        mosCnt += prev->MOS_CNT;
        
        // watch2(s, e);
        // watch(mosCnt);
        if (mosCnt > maxMosCnt) {
            maxMosCnt = mosCnt, maxRangeS = s, maxRangeE = e + 1;
        } else if (mosCnt == maxMosCnt and maxRangeE == s) {
            // extend the range
            maxRangeE = e + 1;
        }
    }
    cout << maxMosCnt << endl;
    cout << maxRangeS << " " << maxRangeE << endl;
    return 0;
}