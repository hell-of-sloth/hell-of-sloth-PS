#include <cassert>
#include <iostream>
#include <queue>
#include <tuple>
#define endl '\n'
#define F first
#define S second
#define ASSERT(exp, msg) assert(exp &&msg);

using namespace std;

const int MAX = 1000, NUM_DIR = 4;
int MATRIX[MAX][MAX], dist[MAX][MAX];
int M, N, ans;
// clock_wise
int di[NUM_DIR] = {-1, 0, 1, 0}, dj[NUM_DIR] = {0, 1, 0, -1};
queue<pair<int, int>> Q;

bool OOB(int ci, int cj) {
    if (ci < 0 || ci >= N)
        return true;
    if (cj < 0 || cj >= M)
        return true;
    return false;
}

int get_answer() {
    int answer = 0;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            if (dist[i][j] == 0)
                return -1;
            if (dist[i][j] > answer)
                answer = dist[i][j];
        }
    }
    return answer;
}

void solve() {

    while (!Q.empty()) {
        int ci, cj;
        tie(ci, cj) = Q.front();
        Q.pop();
        for (int k = 0; k < NUM_DIR; k++) {
            int ni, nj;
            ni = ci + di[k];
            nj = cj + dj[k];
            if (OOB(ni, nj))
                continue;
            if (dist[ni][nj] == -1)
                continue;
            if (dist[ni][nj] >= 1)
                continue;

            dist[ni][nj] = dist[ci][cj] + 1;
            Q.push({ni, nj});
        }
    }
    ans = get_answer();
    if (ans == -1)
        cout << ans << endl;
    else
        cout << ans - 1 << endl;
}

void read_user_input() {

    cin >> M >> N;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> MATRIX[i][j];
            if (MATRIX[i][j] == 1) {
                Q.push({i, j});
                dist[i][j] = 1;
            } else if (MATRIX[i][j] == -1) {
                dist[i][j] = -1;
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
