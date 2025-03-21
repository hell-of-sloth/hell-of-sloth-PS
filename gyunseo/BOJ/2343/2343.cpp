#include <iostream>
#include <vector>
#include <algorithm>

#define fastio cin.tie(0)->sync_with_stdio(0)

using namespace std;
using ll = long long;

ll N, M;
vector<ll> lectures, prefixSum;
void readInput() {
    cin >> N >> M;
    for (ll i = 0; i < N; ++i) {
        ll e;
        cin >> e;
        lectures.push_back(e);
        if (i == 0) prefixSum.push_back(e);
        else prefixSum.push_back(e + prefixSum.back());
    }

}

bool f(ll mBlueraySize) {
    if (mBlueraySize >= prefixSum.back()) return true;
    ll tmpSum = 0, tmpCnt = 1;
    for (auto it = lectures.rbegin(); it != lectures.rend(); ++it) {
        if (tmpSum + *it > mBlueraySize) {
            tmpCnt++;
            tmpSum = *it;
            continue;
        }
        tmpSum += *it;
    }

    if (tmpCnt <= M) return true;
    return false;
}

void solve() {
    ll lo = *max_element(lectures.begin(), lectures.end()), hi = prefixSum.back();
    ll x = lo - 1;
    for (ll b = hi; b >= 1; b >>= 1) {
        while(x + b <= hi and not f(x + b)) x += b;
    }
    cout << x + 1 << endl;
}


int main() {
    fastio;
    readInput();
    solve();
    return 0;
}