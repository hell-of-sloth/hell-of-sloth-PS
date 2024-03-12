#include <iostream>
#include <vector>

// #define endl '\n'
#define watch(x) cout << (#x) << " is " << x << endl

using namespace std;
//*  5 ≤ n ≤ 100,000
//* 5 ≤ m ≤ 200,000
//* 1 ≤ S ≤ n이고 1 ≤ T ≤ n이며 S ≠ T
//* S에서 T로 가는 경로와 T에서 S로 가는 경로가 하나 이상 존재함이 보장된다.
//* 간선의 양 끝 점은 서로 다르다.
//* 같은 정점쌍을 같은 방향으로 잇는 간선은 두 개 이상 주어지지 않는다.

const int MAX = (int)1e5;
int n, m, S, T;
vector<int> graph_o[MAX + 1], graph_r[MAX + 1];

bool is_visited1[MAX + 1], is_visited2[MAX + 1], is_visited3[MAX + 1],
    is_visited4[MAX + 1];

void dfs(int cur, bool (&is_visited)[MAX + 1], vector<int> (&graph)[MAX + 1]) {
    for (auto nv : graph[cur]) {
        if (is_visited[nv])
            continue;
        is_visited[nv] = true;
        dfs(nv, is_visited, graph);
    }
}

void solve() {

    is_visited1[S] = true;
    is_visited1[T] = true;
    dfs(S, is_visited1, graph_o);

    is_visited2[T] = true;
    is_visited2[S] = true;
    dfs(T, is_visited2, graph_o);

    // S -> 정답 노드 -> S
    is_visited3[S] = true;
    dfs(S, is_visited3, graph_r);

    // T -> 정답 노드 -> T
    is_visited4[T] = true;
    dfs(T, is_visited4, graph_r);

    int ans = 0;
    for (int i = 1; i <= n; i++) {
        if (i == S || i == T)
            continue;
        if (is_visited1[i] && is_visited2[i] && is_visited3[i] &&
            is_visited4[i])
            ans++;
    }
    cout << ans << endl;
}

void read_user_input() {
    cin >> n >> m;
    for (int i = 0; i < m; i++) {
        int s, e;
        cin >> s >> e;
        graph_o[s].push_back(e);
        graph_r[e].push_back(s);
    }
    cin >> S >> T;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    read_user_input();
    solve();
    return 0;
}
