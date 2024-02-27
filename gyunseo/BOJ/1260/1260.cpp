#include <algorithm>
#include <cassert>
#include <iostream>
#include <queue>
#include <vector>

#define endl '\n'
#define ASSERT(exp, msg) assert(exp &&msg)
#define watch(x) cout << (#x) << "is " << x << endl;

using namespace std;

const int MAX = 1000;
vector<vector<int>> graph(MAX + 1);
queue<int> q;
bool is_visited[MAX + 1];
int N, M, V;

void dfs(int c) {
    cout << c << " ";
    for (auto n : graph[c]) {
        if (is_visited[n])
            continue;
        is_visited[n] = true;
        dfs(n);
    }
}

void solve() {
    is_visited[V] = true;
    dfs(V);
    cout << endl;
    for (int i = 1; i <= N; i++) {
        is_visited[i] = false;
    }
    q.push(V);
    is_visited[V] = true;
    while (!q.empty()) {
        int c = q.front();
        q.pop();
        cout << c << " ";
        for (auto n : graph[c]) {
            if (is_visited[n])
                continue;
            is_visited[n] = true;
            q.push(n);
        }
    }
}

void read_user_input() {
    cin >> N >> M >> V;
    for (int i = 0; i < M; i++) {
        int u, v;
        cin >> u >> v;
        graph[u].push_back(v);
        graph[v].push_back(u);
    }
    for (int i = 1; i <= N; i++) {
        sort(graph[i].begin(), graph[i].end());
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    read_user_input();
    solve();
    return 0;
}
