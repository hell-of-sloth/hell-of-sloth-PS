#include <iostream>
#include <string>

// define endl '\n'
#define watch(x) cout << (#x) << " is " << x << endl;
using namespace std;
const char N = '0', S = '1';
const int CLOCK = 1, ANTI = -1, LEFT = -1, RIGHT = 1;
char wheels[4][8];
int K;

void rotate_clock(char (&wheel)[8]) {
    char tmp = wheel[7];
    for (int i = 7; i > 0; i--) {
        wheel[i] = wheel[i - 1];
    }
    wheel[0] = tmp;
}

void rotate_anti(char (&wheel)[8]) {
    char tmp = wheel[0];
    for (int i = 0; i < 7; i++) {
        wheel[i] = wheel[i + 1];
    }
    wheel[7] = tmp;
}

void rotate(char (&wheel)[8], int dir) {
    if (dir == CLOCK)
        rotate_clock(wheel);
    else
        rotate_anti(wheel);
}

int get_ans() {
    int ret = 0;
    // 1번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 1점
    if (wheels[0][0] == S)
        ret += 1;
    // 2번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 2점
    if (wheels[1][0] == S)
        ret += 2;
    // 3번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 4점
    if (wheels[2][0] == S)
        ret += 4;
    // 4번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 8점
    if (wheels[3][0] == S)
        ret += 8;
    return ret;
}

// p_dir = -1, 1
// dir = 1, -1
void chaining(int cur_wheel, int p_dir, int dir) {
    int next_wheel = cur_wheel + p_dir;
    if (next_wheel < 0 || next_wheel > 3)
        return;
    if (p_dir == LEFT) {
        if (wheels[cur_wheel][6 + dir] != wheels[next_wheel][2]) {
            // 일단 돌린다
            rotate(wheels[next_wheel], dir * -1);
            chaining(next_wheel, p_dir, dir * -1);
        }
        return;
    } else if (p_dir == RIGHT) {
        if (wheels[cur_wheel][2 + dir] != wheels[next_wheel][6]) {
            // 일단 돌린다
            rotate(wheels[next_wheel], dir * -1);
            chaining(next_wheel, p_dir, dir * -1);
        }
        return;
    }
}

void read_input() {
    string wheel_info;
    for (int i = 0; i < 4; i++) {
        getline(cin, wheel_info);
        for (int j = 0; j < 8; j++) {
            wheels[i][j] = wheel_info[j];
        }
    }
    cin >> K;
    for (int i = 0; i < K; i++) {
        int num, dir;
        cin >> num >> dir;
        num = num - 1;
        // 일단 돌리고
        rotate(wheels[num], dir);
        // 돌린 정보를 전파한다
        chaining(num, LEFT, dir);
        chaining(num, RIGHT, dir);
    }
    int ans;
    ans = get_ans();
    cout << ans << endl;
    // DEBUG
    // for (int i = 0; i < 4; i++) {
    // for (int j = 0; j < 8; j++) {
    // cout << wheels[i][j] << " ";
    //}
    // cout << endl;
    //}
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    read_input();
    return 0;
}
