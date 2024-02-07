#include <iostream>
#include <vector>
#include <queue>
#include <tuple>
#include <algorithm>
#define endl '\n'
using namespace std;

const int MAX = 1000;
int M, N, tomato_cnt = 0, ans = -1;
int board[MAX][MAX], ripen_date[MAX][MAX];
int di[4] = {-1, 1, 0, 0}, dj[4] = {0, 0, -1, 1};
queue<pair<int, int>> q;
vector<pair<int, int>> start_poses;
bool OOB(int i, int j) {
    if (i < 0 || i >= N) return true;
    if (j < 0 || j >= M) return true;
    return false;
}

void solve() {
    for (auto pos : start_poses) {
        q.push(pos);
        ripen_date[pos.first][pos.second] = 1;
        tomato_cnt--;
    }
    if (tomato_cnt == 0) {
        cout << tomato_cnt << endl;
        return;
    }
    int cur_i, cur_j;
    while (!q.empty()) {
        tie(cur_i, cur_j) = q.front();
        q.pop();
        for (int k = 0; k < 4; k++) {
            int ni, nj;
            ni = cur_i + di[k];
            nj = cur_j + dj[k];
            if (OOB(ni, nj)) continue;
            if (board[ni][nj] == -1) continue;
            if (ripen_date[ni][nj] != 0) continue;
            ripen_date[ni][nj] = ripen_date[cur_i][cur_j] + 1;
            tomato_cnt--;
            q.push({ni, nj});
        }
        
    }
    // 이미 다 익어 있었을 때
    // 다 익지 못 했을 때를 체크해야 함
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            if (ripen_date[i][j] > ans) ans = ripen_date[i][j];
        }
    }
    if (tomato_cnt > 0) {
        cout << -1 << endl;
        return;
    }

    cout << ans - 1 << endl;
}

void read_user_input() {
    cin >> M >> N;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> board[i][j];
            if (board[i][j] == 1) start_poses.push_back({i, j});
            if (board[i][j] != -1) tomato_cnt++;
        }
    }

}
int main() {
    ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    read_user_input();
    solve();
    return 0;
}
