#include <iostream>
#include <vector>
#include <tuple>
#include <algorithm>

#define fastio cin.tie(0)->sync_with_stdio(0)
#define watch(x) cout << (#x) << " is " << (x) << endl

using namespace std;
using ll = long long;
using TL = tuple<ll, ll, ll>;

struct UnionFind {
    vector<ll> parents;
    UnionFind(ll _n) { parents.assign(_n + 1, -1); }

    ll find(ll x) {
        if (parents[x] < 0)
            return x;
        return parents[x] = find(parents[x]);
    }

    bool hasSameParents(ll u, ll v) {
        u = find(u), v = find(v);
        if (u == v)
            return true;
        return false;
    }

    void uni(ll u, ll v) {
        u = find(u), v = find(v);
        if (u == v)
            return;

        if (parents[u] > parents[v]) swap(u, v);

        if (parents[u] == parents[v]) parents[u] -= 1;

        parents[v] = u;
    }
};

ll N, M;
vector<TL> arr;

bool compare(TL &lhs, TL &rhs) {
    return get<2>(lhs) < get<2>(rhs);
}

void debugVector() {
    for (ll i = 0; i < M; ++i) {
        auto [u, v, cost] = arr[i];
        cout << u << " " << v << " " << cost << endl;
    }
}
void readInput() {
    cin >> N >> M;
    for (ll i = 0; i < M; ++i) {
        ll u, v, cost;
        cin >> u >> v >> cost;
        arr.push_back(make_tuple(u,v,cost));
    }
    sort(arr.begin(), arr.end(), compare);
    // debugVector();
}

void solve() {
    UnionFind uf(N);
    vector<TL> arr2;
    ll ans = 0;
    for (const auto &e : arr) {
        auto [u, v, cost] = e;
        if (uf.hasSameParents(u, v)) continue;
        uf.uni(u, v);
        ans += cost;
        arr2.push_back(make_tuple(u, v, cost));
    }
    auto [lastEdgeU, lastEdgeV, lastEdgeCost] = arr2.back();
    cout << ans - lastEdgeCost << endl;
}

int main() {
    fastio;
    readInput();
    solve();
    return 0;
}