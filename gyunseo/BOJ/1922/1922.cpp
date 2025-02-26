#include <iostream>
#include <cassert>
#include <vector>
#include <tuple>
#include <algorithm>
#include <set>
#include <queue>

#define endl '\n'
#define fastio cin.tie(0)->sync_with_stdio(0)
#define ASSERT(exp, msg) assert((exp) &&(msg))
#define EACH(x, a) for (const auto &(x) : (a))
#define REP(x) for (ll idx = 0; idx < (x); ++idx)

using namespace std;

using ll = long long;
using VL = vector<ll>;
using PL = pair<ll, ll>;
using TL = tuple<ll, ll, ll>;

struct Node {
    ll cost, u, v;

    bool operator<(const Node &rhs) const { return cost > rhs.cost; }

    Node(ll _cost, ll _u, ll _v) { cost = _cost, u = _u, v = _v; }
};

const ll MAX_N = 1'000, MAX_M = 100'000;
ll N, M;
priority_queue<Node> pq;
vector<ll> parents(MAX_N + 7, -1);

ll findParents(ll n) {
    ASSERT(n >= 0 and n < MAX_N+ 1, "n should be in range!");
    if (parents[n] < 0) return n;
    // 경로 압축
    return parents[n] = findParents(parents[n]);
}

bool hasSameParents(ll u, ll v) {
    return findParents(u) == findParents(v);
}

void unite(ll u, ll v) {
    u = findParents(u), v = findParents(v);
    if (u == v) return;

    if (u > v) swap(u, v);

    // 음수 rank를 표현함 v의 rank가 더 크면 안됨 항상 u가 rank가 더크거나 같은 상태로 union을 한다
    if (parents[v] < parents[u]) swap(u, v);
    if (parents[u] == parents[v]) --parents[u];
    parents[v] = u;
}

void readInput() {
    cin >> N;
    cin >> M;
    REP(M) {
        ll a, b, c;
        cin >> a >> b >> c;
        pq.push(Node(c, a, b));
    }
}

void solve() {
    ll ans = 0;
    // 1. 가중치가 작은 간선부터 선택
    // 2. 사이클을 형성하려 하면 제외
    while (!pq.empty()) {
        ll cost, u, v;
        tie(cost, u, v) = make_tuple(pq.top().cost, pq.top().u, pq.top().v);
        pq.pop();
        if (hasSameParents(u, v)) continue;
        unite(u, v);
        ans += cost;
    }
    cout << ans << endl;
}


int main() {
    fastio;
    readInput();
    solve();
    return 0;
}