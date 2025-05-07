#include <iostream>
#include <vector>
#include <cassert>
#include <algorithm>

#define fastio cin.tie(0)->sync_with_stdio(0)
#define watch(x) cout << (#x) << " is " << (x) << endl
#define endl '\n'

using namespace std;
using ll = long long;
using VL = vector<ll>;

ll N, M;
VL v;
void readInput() {
    cin >> N >> M;
    for (ll i = 0; i < N; ++i) {
        ll e; 
        cin >> e;
        v.push_back(e);
    }
}

void fn(ll level, VL &buf) {
    if (level == M) {
        for (ll i = 0; i < buf.size(); ++i) {
            cout << buf[i] << " "; 
        }cout << endl;
        return;
    }

    for (ll i = 0; i < N; ++i) {
        buf.push_back(v[i]);
        fn(level + 1, buf);
        buf.pop_back();
    }
}

void solve() {
    sort(v.begin(), v.end());
    VL buf;
    fn(0, buf);
}

int main() {
    fastio;
    readInput();
    solve();
    return 0;
}
