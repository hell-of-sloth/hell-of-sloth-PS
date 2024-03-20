#include <iostream>
#include <queue>
#include <tuple>
#include <vector>
#define endl '\n'
#define F first
#define S second
using namespace std;
// 격자 크기
const int MAX = 1000;
const char N = 'N', D = 'D', G = 'G', WALL = '#';
const int di[] = {-1, 0, 1, 0}, dj[] = {0, 1, 0, -1};
int n, m;
char BOARD[MAX + 1][MAX + 1];
int dist1[MAX + 1][MAX + 1], dist2[MAX + 1][MAX + 1];
vector<pair<int, int>> G_poses;
pair<int, int> N_pos, D_pos;
queue<pair<int, int>> Q;

bool OOB(int i, int j) {
    if (i < 0 || i >= n)
        return true;
    if (j < 0 || j >= m)
        return true;
    return false;
}

void solve() {
    // 귀신들이 방문한다 먼저
    for (const auto& pos : G_poses) {
        // 귀신 출발지의 거리를 1로 설정해 놓는다
        Q.push(pos);
        dist1[pos.F][pos.S] = 1;
    }
    while (!Q.empty()) {
        int ci, cj;
        tie(ci, cj) = Q.front();
        Q.pop();
        for (int k = 0; k < 4; k++) {
            int ni, nj;
            ni = ci + di[k];
            nj = cj + dj[k];
            // BOARD밖이면
            if (OOB(ni, nj))
                continue;
            // 이미 방문했던 곳이면
            if (dist1[ni][nj] > 0)
                continue;
            // distance가 1증가
            dist1[ni][nj] = dist1[ci][cj] + 1;
            Q.push({ni, nj});
        }
    }

    // DEBUG
    // for (int i = 0; i < n; i++) {
    // for (int j = 0; j < m; j++) {
    // cout << dist1[i][j] << " ";
    //}
    // cout << endl;
    //}

    while (!Q.empty())
        Q.pop();
    // 남우가 움직이는 시나리오를 돌린다
    // 남우는 귀신들이 본인보다 먼저 자기가 가려는 곳에 있다면 탈출하지 못한다
    Q.push(N_pos);
    dist2[N_pos.F][N_pos.S] = 1;
    while (!Q.empty()) {
        int ci, cj;
        tie(ci, cj) = Q.front();
        Q.pop();
        for (int k = 0; k < 4; k++) {
            int ni, nj;
            ni = ci + di[k];
            nj = cj + dj[k];
            // BOARD밖이면
            if (OOB(ni, nj))
                continue;
            // 벽이면
            if (BOARD[ni][nj] == WALL)
                continue;
            // 유령이 먼저 방문했던 곳이거나, 동시에 도착해서 마주친다면
            if (dist1[ni][nj] <= (dist2[ci][cj] + 1))
                continue;
            // 이미 방문했던 곳이면
            if (dist2[ni][nj] > 0)
                continue;
            // distance가 1증가
            dist2[ni][nj] = dist2[ci][cj] + 1;
            Q.push({ni, nj});
        }
    }

    // DEBUG
    // cout << "-------" << endl;
    // for (int i = 0; i < n; i++) {
    // for (int j = 0; j < m; j++) {
    // cout << dist2[i][j] << " ";
    //}
    // cout << endl;
    //}

    // 출구 좌표에 남우가 방문을 했다면은
    if (dist2[D_pos.F][D_pos.S])
        cout << "Yes" << endl;
    else
        cout << "No" << endl;
}

void read_user_input() {
    cin >> n >> m;
    cin.ignore();
    string str;
    for (int i = 0; i < n; i++) {
        getline(cin, str);
        for (int j = 0; j < m; j++) {
            BOARD[i][j] = str[j];
            if (BOARD[i][j] == N) {
                N_pos = make_pair(i, j);
            } else if (BOARD[i][j] == D) {
                D_pos = make_pair(i, j);
            } else if (BOARD[i][j] == G) {
                G_poses.push_back({i, j});
            }
        }
    }
    // DEBUG
    // for (int i = 0; i < n; i++) {
    // for (int j = 0; j < m; j++) {
    // cout << BOARD[i][j] << " ";
    //}
    // cout << endl;
    //}
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    read_user_input();
    solve();
    return 0;
}
