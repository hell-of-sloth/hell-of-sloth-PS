#include <cassert>
#include <iostream>
#include <map>
#include <queue>
#include <tuple>
#include <vector>

#define endl '\n'
#define fastio cin.tie(0)->sync_with_stdio(0)
#define watch(x) cout << (#x) << " is " << (x) << endl
#define REP(x) for (ll idx = 0; idx < (x); ++idx)
#define EACH(x, a) for (const auto &x : (a))
#define ASSERT(exp, msg) assert((exp) && (msg))

using namespace std;
using ll = long long;
using VL = vector<ll>;
using PL = pair<ll, ll>;

struct Node {
    ll vertex, cost;
    bool operator<(const Node &child) const { return cost > child.cost; }

    Node(ll _vertex, ll _cost) { vertex = _vertex, cost = _cost; }
};

const ll MAX_N = 1'000, MAX_M = 100'000, NO_WHERE = -1, INF = (ll)1e9 + 7;
map<ll, ll> graph[MAX_N + 7];
ll gN, gM, src, dst;
vector<PL> dist(MAX_N + 7, {INF, NO_WHERE});
priority_queue<Node> pq;

void readInput() {
    cin >> gN >> gM;
    REP(gM) {
        ll u, v, cost;
        cin >> u >> v >> cost;
        if (auto search = graph[u].find(v); search != graph[u].end())
            graph[u][v] = min(graph[u][v], cost);
        else
            graph[u][v] = cost;
    }
    cin >> src >> dst;
}

void solve() {
    pq.push(Node(src, 0));
    dist[src].first = 0, dist[src].second = NO_WHERE;

    while (!pq.empty()) {
        ll cv = pq.top().vertex, cc = pq.top().cost;
        pq.pop();
        if (dist[cv].first != cc)
            continue;
        for (const auto &x : graph[cv]) {
            ll nv = x.first, nc = x.second;
            if (dist[cv].first + nc < dist[nv].first) {
                dist[nv].first = dist[cv].first + nc, dist[nv].second = cv;
                pq.push({nv, dist[nv].first});
            }
        }
    }
    cout << dist[dst].first << endl;

    VL path({dst});
    ll curr = dist[dst].second;
    while (curr != NO_WHERE) {
        path.push_back(curr);
        curr = dist[curr].second;
    }
    cout << path.size() << endl;

    for (auto it = path.rbegin(); it != path.rend(); ++it) {
        cout << *it << " ";
    }
}

int main() {
    fastio;
    readInput();
    solve();
    return 0;
}