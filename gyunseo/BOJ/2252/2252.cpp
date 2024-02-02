#include <iostream>
#include <queue>
#include <vector>
#define endl '\n'
using namespace std;
const int MAX = 32000;
int N, M;
vector<int> graph[MAX + 1];
int indegree[MAX + 1];
queue<int> q;
void solve() {
    for (int i = 1; i <= N; i++) {
         if (indegree[i] == 0) {
            q.push(i);
            continue;
        }
    }
    while (!q.empty()) {
        int cur = q.front();
        q.pop();
        cout << cur << " ";
        for (auto x : graph[cur]) {
            indegree[x]--;
            if (indegree[x] == 0) {
                q.push(x);
            }
        }
    }
}
void read_user_input() {
    int a, b;
    cin >> N >> M;
    for (int i = 0; i < M; i++) {
        cin >> a >> b;
        graph[a].push_back(b);
        indegree[b]++;
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
