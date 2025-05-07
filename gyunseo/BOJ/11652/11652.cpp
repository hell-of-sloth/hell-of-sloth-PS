#include <iostream>
#include <map>
#include <string>
#include <set>
#include <vector>
#include <cassert>
#include <algorithm>

#define endl '\n'
#define fastio cin.tie(0)->sync_with_stdio(0)
#define watch(x) cout << (#x) << " is " << (x) << endl
#define watch2(x, y) cout << (#x) << ", " << (#y) << " is " << (x) << ", " << (y) << endl
#define EACH(x, a) for (const auto x: (a))

using namespace std;
using ll = long long;
using VL = vector<ll>;
using VB = vector<bool>;
using PL = pair<ll, ll>;

struct Cmp {
    
    bool operator() (const PL &lhs, const PL &rhs) const {
        if (lhs.second == rhs.second) {
            return lhs.first < rhs.first;
        }
        return lhs.second > rhs.second;
    }

};

ll N;
map<ll, ll> mp;

void readInput() {
    cin >> N;
    for (ll i = 0; i < N; ++i) {
        ll e;
        cin >> e;
        mp[e]++;
    }

}

void solve() {
    vector<PL> vec(mp.begin(), mp.end());
    sort(vec.begin(), vec.end(), Cmp());
    //watch2(vec[0].first, vec[0].second);
    cout << vec[0].first << endl;
}

int main() {
    fastio;
    readInput();
    solve();
    return 0;

}
