#include <algorithm>
#include <cassert>
#include <iostream>
#include <queue>
#include <vector>

#define fastio cin.tie(0)->sync_with_stdio(0)
#define endl '\n'
#define VL vector<ll>
// #define watch(x) cout << (#x) << " is " << (x) << endl
#define watch(x)

using namespace std;
using ll = long long;

struct Vertex {
    ll vertexIdx, endTime;
    bool operator<(const Vertex &rhs) const { return endTime > rhs.endTime; }
};
const ll MAX_N = 1007;
ll T, N, K, X, Y, W;
VL D(MAX_N + 1, 0), inDegree(MAX_N + 1, 0);
VL graph[MAX_N + 1];
priority_queue<Vertex> q;

void readInput() {
    cin >> N >> K;
    watch(N);
    watch(K);
    for (ll i = 1; i <= N; ++i) {
        cin >> D[i];
        watch(D[i]);
    }
    for (ll i = 1; i <= K; ++i) {
        cin >> X >> Y;
        ++inDegree[Y];
        graph[X].push_back(Y);
    }
    cin >> W;
    watch(W);
}

void solve() {
    for (ll i = 1; i <= N; ++i)
        if (!inDegree[i])
            q.push({i, D[i]});

    while (!q.empty()) {
        ll currIdx = q.top().vertexIdx, currEndTime = q.top().endTime;
        q.pop();
        watch(currIdx);
        watch(currEndTime);

        if (currIdx == W) {
            cout << currEndTime << endl;
            break;
        }
        for (const auto &nextV : graph[currIdx]) {
            --inDegree[nextV];
            if (!inDegree[nextV]) {
                q.push({nextV, currEndTime + D[nextV]});
                watch(nextV);
            }
        }
    }
}

void clearGlobalVars() {
    for (ll i = 1; i <= N; ++i) {
        D[i] = 0;
        inDegree[i] = 0;
        while (!graph[i].empty())
            graph[i].pop_back();
    }
    while (!q.empty())
        q.pop();
}
int main() {
    fastio;
    cin >> T;
    while (T--) {
        readInput();
        solve();
        clearGlobalVars();
    }

    return 0;
}