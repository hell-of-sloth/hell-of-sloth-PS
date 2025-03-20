#include <iostream>
#include <vector>
#include <cassert>
#include <algorithm>
#include <set>
#include <tuple>

#define endl '\n'
#define fastio cin.tie(0)->sync_with_stdio(0)
#define REP(x) for (ll idx = 0; idx < (x); ++idx)
#define watch(x) cout << (#x) << " is " << (x) << endl

using namespace std;
using ll = long long;
using VL = vector<ll>;
using PL = pair<ll, ll>;

const ll MAX_N = 200'000;
ll N;
vector<PL> v;
multiset<ll> ans;

void readInput() {
    cin >> N;
    REP(N) {
        ll s, t;
        cin >> s >> t;
        v.push_back({s, t});
    }
}

void debug() {
    for (auto it = ans.begin(); it != ans.end(); ++it) {
        cout << *it << " "; 
    }cout << endl;
}

void solve() {
    sort(v.begin(), v.end());
    REP(N) {
        auto [s, t] = v[idx];
        if (ans.empty()) ans.insert(t);
        else {

            if (ans.upper_bound(s) == ans.begin()) {
                ans.insert(t);
            }
            else {
                if (ans.find(s) != ans.end()) {
                    ans.erase(ans.find(s)); ans.insert(t);
                }
                else {
                    ans.erase(next(ans.upper_bound(s), -1)); ans.insert(t);
                }
            }
        }
    }
    // debug();
    cout << ans.size() << endl;
}

int main() {
    fastio;
    readInput();
    solve();
    return 0;
}