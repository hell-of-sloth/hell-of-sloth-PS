#include <algorithm>
#include <cassert>
#include <cstring>
#include <iostream>
#define endl '\n'
#define ASSERT(exp, msg) assert(exp &&msg)
#define WATCH(X) cout << (#X) << " is " << X << endl
using namespace std;
// 첫째 줄에 보드의 크기 N (1 ≤ N ≤ 20)이 주어진다. 둘째 줄부터 N개의 줄에는
// 게임판의 초기 상태가 주어진다. 0은 빈 칸을 나타내며, 이외의 값은 모두 블록을
// 나타낸다. 블록에 쓰여 있는 수는 2보다 크거나 같고, 1024보다 작거나 같은 2의
// 제곱꼴이다. 블록은 적어도 하나 주어진다.
const int MAX = 20, MAX_MOVE_CNT = 5, NUM_DIRS = 4;
int N, ans = -1;
int BOARD[MAX][MAX], board[MAX][MAX], tmp_board[MAX][MAX];
void print_board() {
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cout << board[i][j] << " ";
        }
        cout << endl;
    }
}
// 반시계 방향으로 회전
void rotate_anti_clock_wise() {
    fill(&tmp_board[0][0], &tmp_board[MAX - 1][MAX], 0);
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            tmp_board[i][j] = board[j][(N - 1) - i];
        }
    }
    memmove(board, tmp_board, sizeof(board));
    print_board();
}

// 시계 방향으로 회전
void rotate_clock_wise() {
    fill(&tmp_board[0][0], &tmp_board[MAX - 1][MAX], 0);
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            tmp_board[i][j] = board[(N - 1) - j][i];
        }
    }
    memmove(board, tmp_board, sizeof(board));
    // print_board();
}

// 왼쪽으로 tilt 하는 함수
void tilt(int dir) {
    while (dir) {
        rotate_clock_wise();
        dir--;
    }

    for (int i = 0; i < N; i++) {
        int tilted_row[MAX] = {
            0,
        };
        int idx = 0;
        for (int j = 0; j < N; j++) {
            // board에 아무 것도 없으면 넘어 간다
            if (board[i][j] == 0)
                continue;
            if (tilted_row[idx] == 0)
                tilted_row[idx] = board[i][j];
            else if (tilted_row[idx] == board[i][j])
                tilted_row[idx++] *= 2;
            else
                tilted_row[++idx] = board[i][j];
        }
        memmove(&board[i][0], &tilted_row[0], sizeof(tilted_row));
    }
}

// num^k
int get_pow(int num, int k) {
    int ret = 1;
    for (int i = 0; i < k; i++) {
        ret *= num;
    }
    return ret;
}

int get_ans() {
    int tmp_ans = -1;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (board[i][j] > tmp_ans)
                tmp_ans = board[i][j];
        }
    }
    return tmp_ans;
}

void solve() {
    int brute_cases = get_pow(4, MAX_MOVE_CNT);
    for (int brute_case = 0; brute_case <= brute_cases; brute_case++) {
        int tmp_case = brute_case;
        memmove(&board[0][0], &BOARD[0][0], sizeof(BOARD));
        // 4^0의 자리수부터 꺼내면서 어떤 방향인지 확인
        for (int k = 0; k < MAX_MOVE_CNT; k++) {
            int cur_dir = tmp_case % NUM_DIRS;
            // 다음 자리수로 미리 넘어 가 있기
            tmp_case /= NUM_DIRS;
            // 방향 만큼 돌리고
            // tilt한다.
            tilt(cur_dir);
            // 다시 되돌려
        }
        int tmp_ans = get_ans();
        if (tmp_ans > ans)
            ans = tmp_ans;
    }
    // WATCH(ans);
    cout << ans << endl;
}

void read_user_input() {
    cin >> N;
    for (int i = 0; i < N; i++) {
        for (int ii = 0; ii < N; ii++) {
            cin >> BOARD[i][ii];
            board[i][ii] = BOARD[i][ii];
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
