#include <algorithm>
#include <cassert>
#include <cstdlib>
#include <iostream>
#include <vector>
#define endl '\n'
#define ASSERT(exp, msg) assert(exp &msg)
using namespace std;

const int MAX = 15;
int board[MAX + 1][MAX + 1];
vector<int> is_issued_col;
int N, ans;

bool is_in_diagonal_path(int cur_col, int cur_row) {

    int num_issued_row = is_issued_col.size();
    for (int i = 0; i < num_issued_row; i++) {
        int col = is_issued_col[i];
        int row = i;
        // int abs는 cstdlib에 있음
        if (abs(cur_row - row) == abs(cur_col - col))
            return true;
    }
    return false;
}
void dfs(int col, int row) {
    if (row == N - 1) {
        ans++;
        return;
    }
    for (int i = 0; i < N; i++) {
        if (find(is_issued_col.begin(), is_issued_col.end(), i) !=
            is_issued_col.end())
            continue;
        if (is_in_diagonal_path(i, row + 1))
            continue;
        is_issued_col.push_back(i);
        dfs(i, row + 1);
        is_issued_col.pop_back();
    }
}

void solve() {
    for (int i = 0; i < N; i++) {
        is_issued_col.push_back(i);
        dfs(i, 0);
        is_issued_col.pop_back();
    }
    cout << ans << endl;
}

void read_user_input() { cin >> N; }

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    read_user_input();
    solve();
    return 0;
}
