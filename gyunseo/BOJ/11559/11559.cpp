#include <algorithm>
#include <cstring>
#include <iostream>
#include <queue>
#include <tuple>
#define endl '\n'
#define F first
#define S second

using namespace std;

const int MAX = 12;
const int DI[4] = {-1, 0, 1, 0}, DJ[4] = {0, 1, 0, -1};
char board[MAX][MAX], board2[MAX][MAX], tmp_board[MAX][MAX];
int dist[MAX][MAX], ans;
queue<pair<int, int>> Q;
void print_board() {
    for (int i = 0; i < 12; i++) {
        for (int j = 0; j < 6; j++) {
            cout << board[i][j] << " ";
        }
        cout << endl;
    }
}

void print_rotated_board() {
    for (int i = 0; i < 6; i++) {

        for (int j = 0; j < 12; j++) {
            cout << board[i][j] << " ";
        }
        cout << endl;
    }
}

bool OOB(int ci, int cj) {
    if (ci < 0 || ci >= 6)
        return true;
    if (cj < 0 || cj >= 12)
        return true;
    return false;
}

bool bfs(pair<int, int> &start) {
    memmove(board2, board, sizeof(board));
    while (!Q.empty())
        Q.pop();
    fill(&dist[0][0], &dist[MAX - 1][MAX], 0);
    Q.push(start);
    dist[start.F][start.S] = 1;
    board2[start.F][start.S] = '.';
    int cnt = 1;
    while (!Q.empty()) {
        int ci, cj;
        tie(ci, cj) = Q.front();
        Q.pop();
        for (int k = 0; k < 4; k++) {
            int ni, nj;
            ni = ci + DI[k];
            nj = cj + DJ[k];
            if (OOB(ni, nj))
                continue;
            if (board[ni][nj] == '.')
                continue;
            // 이미 방문했다면
            if (dist[ni][nj])
                continue;
            // 원본 보드에서 동일한지 다른지 확인
            if (board[ci][cj] != board[ni][nj])
                continue;
            dist[ni][nj] = dist[ci][cj] + 1;
            board2[ni][nj] = '.';
            cnt++;
            Q.push({ni, nj});
        }
    }
    // 상하좌우 4개 이상있었다면, board2의 결과를 board에 반영하기
    if (cnt >= 4) {
        memmove(board, board2, sizeof(board2));
        // DEBUG
        // cout << "above 4!" << endl;
        // print_rotated_board();
        return true;
    }
    return false;
}

// 반시계로 꺾기
void rotate() {
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 12; j++) {
            tmp_board[i][j] = board[11 - j][i];
        }
    }
    memmove(board, tmp_board, sizeof(board));
}

void tilt() {
    for (int i = 0; i < 6; i++) {

        char tilted[12];
        int idx = 0;
        fill(&tilted[0], &tilted[12], '.');
        for (int j = 0; j < 12; j++) {

            if (board[i][j] == '.')
                continue;
            tilted[idx++] = board[i][j];
        }
        for (int j = 0; j < 12; j++)
            board[i][j] = tilted[j];
    }
}

void solve() {
    // 반시계로 꺾기
    rotate();
    // DEBUG
    // print_rotated_board();
    bool iteration = true, is_found = false;
    // 상하좌우로 4개 연결된 거 찾고 '.'으로 바꾸기
    while (iteration) {
        is_found = false;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 12; j++) {
                if (board[i][j] == '.')
                    continue;
                // 이미 간 곳
                pair<int, int> pos = {i, j};
                // DEBUG
                // cout << pos.F << " " << pos.S << endl;
                if (bfs(pos)) {
                    // DEBUG
                    // print_rotated_board();
                    is_found = true;
                }
            }
        }

        if (is_found) {
            // DEBUG
            // cout << "found! continue" << endl;
            tilt();
            fill(&dist[0][0], &dist[MAX - 1][MAX], 0);
            // print_rotated_board();
            ans++;
        } else if (is_found == false) {
            //  DEBUG
            //  cout << "fdafd" << endl;
            iteration = false;
        }
    }

    cout << ans << endl;
}

void read_user_input() {
    for (int i = 0; i < 12; i++) {
        for (int j = 0; j < 6; j++) {
            cin >> board[i][j];
        }
    }
    // print_board();
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    read_user_input();
    solve();
    return 0;
}
