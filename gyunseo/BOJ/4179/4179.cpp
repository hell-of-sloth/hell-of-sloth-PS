#include <iostream>
#include <utility>
// due to tie
#include <tuple>
#include <queue>
#include <vector>
using namespace std;
const int MAX = 1000;
int R, C;
string board[MAX + 10];
int exit_time[MAX + 10][MAX + 10];
queue<pair<int, int>> q;
pair<int, int> jihun_pos;
vector<pair<int, int>> fire_poses;
int di[4] = {-1, 1, 0, 0}, dj[4] = {0, 0, -1, 1};
bool did_escape = false;

bool OOB(int i, int j) {
    if (i < 0 || i >= R) return true;
    if (j < 0 || j >= C) return true;
    return false;
}

void bfs() {
    q.push(jihun_pos); 
    exit_time[jihun_pos.first][jihun_pos.second] = 1;
    for (auto &fire_pos : fire_poses) {
        q.push(fire_pos);
        exit_time[fire_pos.first][fire_pos.second] = (int)1e6 + 10;
    }
    int ci, cj;
    int ni, nj;
    while (!q.empty()) {
        tie(ci, cj) = q.front(); 
        q.pop();
        for (int k = 0; k < 4; k++) {
            ni = ci + di[k];
            nj = cj + dj[k];

            if (OOB(ni, nj)) {
                if (exit_time[ci][cj] <= MAX) { 
                    did_escape = true;
                    cout << exit_time[ci][cj] << endl;
                    return;
                }
                // 불이 탈출 하는 경우
                continue;
            }
            if (exit_time[ci][cj] >= (int)1e6) {
                if (board[ni][nj] == '#') continue;
                if (exit_time[ni][nj] >= (int)1e6) continue;
                exit_time[ni][nj] = exit_time[ci][cj] + 1;
            }
            else {
                if (board[ni][nj] == '#') continue;
                if (board[ni][nj] == 'F') continue;
                if (exit_time[ni][nj] != 0) continue;
                exit_time[ni][nj] = exit_time[ci][cj] + 1;
            }
            q.push({ni, nj});
        }
    }
}

void solve() {
    bfs();
    //for (int i = 0; i < R; i++) {
        //for (int j = 0; j < C; j++) {
            //cout << exit_time[i][j] << " ";
        //}
        //cout << endl;
    //}
    if (did_escape == false) {
        cout << "IMPOSSIBLE" << endl;
    }
}

void read_user_input() {
    cin >> R >> C;
    cin.ignore();
    for (int i = 0; i < R; i++) {
        getline(cin, board[i]);
        //cout << board[i] << endl;
        for (int j = 0; j < C; j++) {
            if (board[i][j] == 'F') {
                fire_poses.push_back({i, j});
            }
            else if (board[i][j] == 'J') {
               jihun_pos = {i, j}; 
            }
        }
    }
}

int main() {
    ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    read_user_input();
    solve();
    return 0;
}
