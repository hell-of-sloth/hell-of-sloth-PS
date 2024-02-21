#include <cassert>
#include <cstring>
#include <iostream>
#include <algorithm>
#include <vector>
#include <tuple>
#include <queue>

#define endl '\n'
#define ASSERT(exp, msg) assert(exp && msg)
#define watch(x) cout << (#x) << " is " << (x) << endl;
using namespace std;
const int MAX = 40;
const int MAX_NUM_STICKERS = 100;
int N, M, K;
int matrix[MAX + 1][MAX + 1];
int matrices_sticker[MAX_NUM_STICKERS][10][10], tmp_sticker[10][10];
vector<pair<int, int>> sizes_sticker;
enum e_rot {
    ORIGIN,
    RIGHT,
    DOWN,
    LEFT
};

void rotate_clock_wise(int (&sticker)[10][10], int &r, int &c) {
    for (int i = 0; i < c; i++) {
        for (int j = 0; j < r; j++) {
            tmp_sticker[i][j] = sticker[r - 1 - j][i];
        }
    }
    memmove(sticker, tmp_sticker, sizeof(sticker));
    swap(r, c);
    //cout << "after a rotation:" << endl;
    //for (int i = 0; i < r; i++) {
        //for (int j = 0; j < c; j++) {
            //cout << sticker[i][j] << " ";
        //}cout << endl;
    //}
}

bool OOB(int i, int j) {
    if (i < 0 || i >= N) return true;
    if (j < 0 || j >= M) return true;
    return false;
}

bool is_sticker_pastable(int (&sticker)[10][10], int r, int c, pair<int, int> &start_pos) {
    const auto [start_i, start_j] = start_pos;
    for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
            // 붙이려는 부분에 이미 붙혀져있으면
            ASSERT(!OOB(start_i + i, start_j +j), "OOB!");
            if (matrix[start_i + i][start_j + j] == 1 && sticker[i][j] == 1) {
                return false;
            }
        }
    }
    for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
            if (sticker[i][j] == 1) {
                ASSERT(!OOB(start_i + i, start_j +j), "OOB!");
                //watch(r);
                //watch(c);
                matrix[start_i + i][start_j + j] = 1;
            }
        }
    }
    return true;
}
void print_matrix() {
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cout << matrix[i][j] << " ";
        }cout << endl;
    }
}
void solve() {
    // sticker matrix를 순회하면서
    // matrix[0][0]부터 자리가 있는지 확인한다
    pair<int, int> start_pos;
    for (int k = 0; k < K; k++) {
        auto [r, c] = sizes_sticker[k];
        bool is_pasted = false;
        for (int rot = 0; rot < 4; rot++) {
            if (is_pasted) break;
            for (int i = 0; i <= N - r ; i++) {
                for (int j = 0; j <= M - c; j++) {
                    start_pos = {i, j};
                    if(is_sticker_pastable(matrices_sticker[k], r, c, start_pos)) {
                        is_pasted = true; 
                        //cout << "=========================" << endl;
                        //cout << "r: " << r << " " << "c: " << c << endl;
                        //cout << "start_i: " << i << " " << "start_j: " << j << endl;
                        //cout << "k: " << k << endl;
                        //print_matrix();
                        //cout << "=========================" << endl;
                        break;
                    }
                }
                if (is_pasted == true) break;
            }
            if (is_pasted == true) break;
            rotate_clock_wise(matrices_sticker[k], r, c);
        }
    }
    int ans = 0;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j <  M; j++) {
            if (matrix[i][j] == 1) ans++;
        }
    }
    cout << ans << endl;
}

void read_user_input() { 
    cin >> N >> M >> K; 
    for (int k = 0; k < K; k++) {
        int r, c;
        cin >> r >> c;
        sizes_sticker.push_back({r, c});
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                cin >> matrices_sticker[k][i][j];
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
