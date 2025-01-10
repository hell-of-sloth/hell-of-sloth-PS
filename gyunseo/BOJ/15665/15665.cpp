#include <iostream>
#include <set>
#include <vector>

#define fastio cin.tie(0)->sync_with_stdio(0)
#define endl '\n'
#define watch(x) cout << (#x) << " is " << x << endnl
#define EACH(x, a) for (const auto &(x): (a))

using namespace std;
using ll = long long;
using SL = set<ll>;
using VL = vector<ll>;

ll N, M;
SL s;
VL v;

void readInput() {
    cin >> N >> M;
    for (ll i = 0; i < N; ++i) {
        ll e;
        cin >> e;
        s.insert(e);
    }
}

void dfs(ll level) {
    if (level == M) {
        EACH(e, v) {
            cout << e << " ";
        }
        cout << endl;
        return;
    }
    for (const auto &e : s) {
        v.push_back(e);
        dfs(level + 1);
        v.pop_back();
    }
}

void solve() {
    for (const auto &e : s) {
        v.push_back(e);
        dfs(1);
        v.pop_back();
    }
}

int main() {
    fastio;
    readInput();
    solve();
    return 0;
}
