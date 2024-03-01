#include <cassert>
#include <iostream>
#include <queue>
#include <tuple>
#define endl '\n'
#define ASSERT(exp, msg) assert(exp &&msg)
using namespace std;

const int MAX = 1000, NUM_DIR = 4, WALL = MAX * MAX + 1;
int MATRIX[MAX][MAX], dist1[MAX][MAX], dist2[MAX][MAX];
int R, C;
int di[NUM_DIR] = {-1, 0, 1, 0}, dj[NUM_DIR] = {0, 1, 0, -1};
queue<pair<int, int>> Q1, Q2;

bool OOB(int ci, int cj) {
    if (ci < 0 || ci >= R)
        return true;
    if (cj < 0 || cj >= C)
        return true;
    return false;
}

void solve() {

    // 불이 퍼지는 BFS를 먼저 실행
    while (!Q1.empty()) {
        int ci, cj;
        tie(ci, cj) = Q1.front();
        Q1.pop();
        for (int k = 0; k < NUM_DIR; k++) {
            int ni, nj;
            ni = ci + di[k];
            nj = cj + dj[k];

            if (dist1[ni][nj] == -1)
                continue;
            if (OOB(ni, nj))
                continue;
            if (dist1[ni][nj] >= 1)
                continue;
            dist1[ni][nj] = dist1[ci][cj] + 1;
            Q1.push({ni, nj});
        }
    }
    // 불이 퍼진 시각을 보면서 불에 데이는지 안 데이는지 확인한다
    while (!Q2.empty()) {
        int ci, cj;
        tie(ci, cj) = Q2.front();
        Q2.pop();
        for (int k = 0; k < NUM_DIR; k++) {
            int ni, nj;
            ni = ci + di[k];
            nj = cj + dj[k];
            // 벽이나 이미 간 곳은 못 간다
            if (dist2[ni][nj] == -1)
                continue;
            if (dist2[ni][nj] >= 1)
                continue;
            // 탈출하면
            if (OOB(ni, nj)) {
                cout << dist2[ci][cj] << endl;
                return;
            }
            // 불이 이미 퍼진 곳이라면
            if (dist1[ni][nj] > 0 && dist1[ni][nj] <= dist2[ci][cj] + 1) {
                continue;
            }
            dist2[ni][nj] = dist2[ci][cj] + 1;
            Q2.push({ni, nj});
        }
    }
    // 여기까지 온 것은 탈출에 실패한 것을 의미함
    cout << "IMPOSSIBLE" << endl;
}

void read_user_input() {
    cin >> R >> C;
    cin.ignore();
    for (int i = 0; i < R; i++) {
        string s;
        getline(cin, s);
        for (int j = 0; j < C; j++) {
            if (s[j] == '#') {
                dist1[i][j] = -1;
                dist2[i][j] = -1;
            } else if (s[j] == 'F') {
                dist1[i][j] = 1;
                Q1.push({i, j});
            } else if (s[j] == 'J') {
                dist2[i][j] = 1;
                Q2.push({i, j});
            }
        }
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
