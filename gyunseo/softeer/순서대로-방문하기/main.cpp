#include <algorithm>
#include <cassert>
#include <iostream>
#include <tuple>
#include <vector>
#define endl '\n'
#define ASSERT(exp, msg) assert(exp &&msg)
#define F first
#define S second

using namespace std;
// [조건 1] 2 ≤ n ≤ 4
// [조건 2] 2 ≤ m ≤ n^2
const int MAX = 4;
int n, m, ans;
int BOARD[MAX][MAX];
int is_visited[MAX][MAX];
int di[] = {-1, 0, 1, 0}, dj[] = {0, 1, 0, -1};
vector<pair<int, int>> poses;
bool OOB(int ci, int cj) {
    if (ci < 0 || ci >= n)
        return true;
    if (cj < 0 || cj >= n)
        return true;
    return false;
}


void dfs(const pair<int, int> &cur_pos) {
    int ci, cj;
    tie(ci, cj) = cur_pos;
    // 마지막으로 방문해야 하는 곳에 도달했다면
    if (ci == poses.back().F && cj == poses.back().S) {
        int len_poses = poses.size();
        for (int i = 1; i < len_poses; i++) {
            if (!is_visited[poses[i].F][poses[i].S]) return;
            if (is_visited[poses[i].F][poses[i].S] < is_visited[poses[i - 1].F][poses[i - 1].S]) return;
        }
        // cout << "DEBUG" << endl;
        ans++;
        return;
    }
    for (int k = 0; k < 4; k++) {
        int ni, nj;
        ni = ci + di[k];
        nj = cj + dj[k];
        if (OOB(ni, nj))
            continue;
        if (is_visited[ni][nj])
            continue;
        if (BOARD[ni][nj] == 1)
            continue;
        pair<int, int> n_pos = {ni, nj};
        is_visited[ni][nj] = is_visited[ci][cj] + 1;
        dfs(n_pos);
        is_visited[ni][nj] = 0;
    }
}
void solve() {
    is_visited[poses[0].F][poses[0].S] = 1;
    dfs(poses[0]);
    cout << ans << endl;
}

void read_user_input() {
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> BOARD[i][j];
        }
    }
    for (int i = 0; i < m; i++) {
        int ci, cj;
        cin >> ci >> cj;
        poses.push_back({ci - 1, cj - 1});
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