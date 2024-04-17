#include <bits/stdc++.h>

#define F first
#define S second
#define FOR(idx, s, e) for (int idx = (s); idx < (e); ++idx)
#define RFOR(idx, e, s) for (int idx = (e); idx >= (s); --idx)
#define VI vector<int>
#define PI pair<int, int>
#define VL vector<ll>
#define PL pair<ll, ll>
#define endl '\n'
#define watch(x) cout << (#x) << " is " << x << endl
#define ASSERT(exp, msg) assert((exp) && (msg))
using namespace std;
using ll = long long;
using ull = unsigned long long;

const int MAX = 10;
int N, M;
char BOARD[MAX][MAX], board[MAX][MAX], tmp[MAX][MAX];
int ans = (int)1e9;

int get_pow(int n, int k) {

    int ret = 1;
    FOR(i, 0, k) { ret *= n; }
    return ret;
}

void rotate_clock(char (&matrix)[MAX][MAX]) {

    fill(&tmp[0][0], &tmp[MAX - 1][MAX], 0);
    FOR(i, 0, M) {
        FOR(j, 0, N) { tmp[i][j] = matrix[N - 1 - j][i]; }
    }
    memmove(&matrix[0][0], &tmp[0][0], sizeof(char) * MAX * MAX);
}

void print_matrix(char (&matrix)[MAX][MAX]) {
    FOR(i, 0, N) {
        FOR(j, 0, M) {
            cout << matrix[i][j];
        }cout << endl;
    }
}
// 좌측으로 tilt한다
void tilt(char (&matrix)[MAX][MAX], int dir) {
    while (dir--)
        rotate_clock(matrix);
    // O(MN)
    // #은 뺀다
    FOR(i, 1, N - 1) {
        char tilted[MAX] = {};
        fill(&tilted[0], &tilted[MAX], '.');
        // tilted에서 비어있는 가장 다음의 idx
        int idx = 1;
        FOR(j, 1, N - 1) {
            // 구슬이 아니면 넘어간다
            if (matrix[i][j] == '.')
                continue;
            if (matrix[i][j] == '#') {
                tilted[j] = '#';
                // 벽 다음 위치
                idx = j + 1;
                continue;
            }
            if (matrix[i][j] == 'O') {
                idx = j;
                continue;
            }

            // 비어 있다면
            if (tilted[idx] == '.')
                tilted[idx] = matrix[i][j];
            // 구슬로 차 있다면
            else if (tilted[idx] == 'R' or tilted[idx] == 'B')
                tilted[++idx] = matrix[i][j];
            else if (tilted[idx] == 'O') {
                // 흡수한다
                if (matrix[i][j] == 'R' or matrix[i][j] == 'B')
                    ++idx;
            }
        }
        FOR(k, 1, N - 1) matrix[i][k] = tilted[k];
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> M;
    cin.ignore();
    string str;
    FOR(i, 0, N) {
        getline(cin, str);
        FOR(j, 0, M) BOARD[i][j] = str[j];
    }
    // print_matrix(BOARD);
    int all_brutes = get_pow(4, 10);
    // 4진수로 나타내는 경우의 수
    watch(all_brutes);
    FOR(brute, 0, all_brutes + 1) {
        watch(brute);
        // memmove(&board[0][0], &BOARD[0][0], sizeof(char) * MAX * MAX);
        // FOR(k, 0, 10) {
        //     int cur_dir = tmp_brute % 4;
        //     tmp_brute /= 4;
        //     // tilt(board, cur_dir);
        //     // check if Red goes into the hole faster than Blue within 10 times
        //     // if it does, write it on ans var
        // }
        // print_matrix(board);
    }
    if (ans == (int)1e9)
        cout << -1 << endl;
    else
        cout << ans << endl;
    return 0;
}