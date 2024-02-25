#include <cassert>
#include <iostream>
#include <vector>

#define endl '\n'
#define watch(x) cout << (#x) << "is " << x << endl
#define ASSERT(exp, msg) assert(exp &&msg)
using namespace std;

const int MAX = 1000;
int N, M, ans = 0;
vector<vector<int>> graph(MAX + 1);
bool is_visited[MAX + 1];

void dfs(int cur_vertex) {
    is_visited[cur_vertex] = true;
    for (auto nv : graph[cur_vertex]) {
        if (is_visited[nv])
            continue;
        dfs(nv);
    }
}
void solve() {
    for (int i = 1; i <= N; i++) {
        // 지금 dfs로 방문하려는 vertex가 아직 방문되지 않았다면
        if (is_visited[i] == false) {
            dfs(i);
            ans++;
        }
    }
    cout << ans << endl;
}

void read_user_input() {
    cin >> N >> M;
    for (int i = 0; i < M; i++) {
        int u, v;
        cin >> u >> v;
        graph[u].push_back(v);
        graph[v].push_back(u);
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
