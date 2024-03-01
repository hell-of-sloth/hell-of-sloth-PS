#include <algorithm>
#include <cassert>
#include <iostream>
#include <tuple>
#include <vector>
#define watch(X) cout << (#X) << " is " << X << endl
#define ASSERT(exp, msg) assert(exp &&msg)
#define endl '\n'
#define F first
#define S second
using namespace std;

const int MAX = 20, NUM_DIR = 4;
int n, m, ans = -1;
int MATRIX[MAX][MAX], is_visited[MAX][MAX];
const int di[NUM_DIR] = {-1, 0, 1, 0}, dj[NUM_DIR] = {0, 1, 0, -1};
vector<pair<int, int>> start_poses;

bool OOB(int ci, int cj) {
    if (ci < 0 || ci >= n)
        return true;
    if (cj < 0 || cj >= n)
        return true;
    return false;
}

int get_pow(int num) {
    int ret = 1;
    for (int i = 0; i < num; i++) {
        ret *= 64;
    }
    return ret;
}

void update_dist(int si, int sj, int dirs) {
    int ci = si, cj = sj;

    is_visited[ci][cj]++;
    for (int i = 0; i < 3; i++) {
        int dir = dirs % NUM_DIR;
        dirs /= NUM_DIR;
        int ni, nj;
        ni = ci + di[dir];
        nj = cj + dj[dir];
        if (OOB(ni, nj))
            continue;
        is_visited[ni][nj]++;
        ci = ni;
        cj = nj;
    }
}

int get_ans() {
    int tmp_ans = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (is_visited[i][j] != 0)
                tmp_ans += MATRIX[i][j];
        }
    }
    return tmp_ans;
}

void print_dist() {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cout << is_visited[i][j] << " ";
        }
        cout << endl;
    }
}
void solve() {
    int brutes = get_pow(m);
    // watch(brutes);
    //  64진법으로 전체 경우의 수를 나타낸다
    for (int brute = 0; brute < brutes; brute++) {
        int tmp_brute = brute;
        fill(&is_visited[0][0],
             &is_visited[0][0] + sizeof(is_visited) / sizeof(int), 0);

        for (auto &cur_pos : start_poses) {
            int dirs = tmp_brute % 64;
            tmp_brute /= 64;
            int start_i, start_j;
            tie(start_i, start_j) = cur_pos;
            update_dist(start_i, start_j, dirs);
        }
        int tmp_ans = get_ans();
        // watch(tmp_ans);
        if (tmp_ans > ans)
            ans = tmp_ans;
    }
    cout << ans << endl;
}
void read_user_input() {
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> MATRIX[i][j];
        }
    }
    fill(&is_visited[0][0],
         &is_visited[0][0] + sizeof(is_visited) / sizeof(int), 0);
    for (int i = 0; i < m; i++) {
        int ci, cj;
        cin >> ci >> cj;
        start_poses.push_back({ci - 1, cj - 1});
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
