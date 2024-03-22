#include <algorithm>
#include <deque>
#include <iostream>
// #define endl '\n'
#define watch(x) cout << (#x) << " is " << x << endl;
using namespace std;

// 1.첫 번째 원소를 뽑아낸다. 이 연산을 수행하면, 원래 큐의 원소가 a1, ...,
// ak이었던 것이 a2, ..., ak와 같이 된다. 2.왼쪽으로 한 칸 이동시킨다. 이 연산을
// 수행하면, a1, ..., ak가 a2, ..., ak, a1이 된다. 3.오른쪽으로 한 칸
// 이동시킨다. 이 연산을 수행하면, a1, ..., ak가 ak, a1, ..., ak-1이 된다.

int N, M;
deque<int> dq;
int answer;
int get_pop_front() {
    int ret = dq.front();
    dq.pop_front();
    return ret;
}

void shift_left() {
    int append_e = dq.front();
    dq.pop_front();
    dq.push_back(append_e);
}
void shift_right() {
    int push_front_e = dq.back();
    dq.pop_back();
    dq.push_front(push_front_e);
}
void solve(int find_num) {
    if (find_num == dq.front()) {
        dq.pop_front();
        return;
    }
    int mid = (int)(dq.size() / 2);
    if ((find(dq.begin(), dq.end(), find_num) - dq.begin()) <= mid) {
        // 찾을 때까지 shift_left()
        while (true) {
            shift_left();
            answer++;
            if (dq.front() == find_num) {
                dq.pop_front();
                return;
            }
        }

    } else {
        // 찾을 때까지 shift_right()
        while (true) {
            shift_right();
            answer++;
            if (dq.front() == find_num) {
                dq.pop_front();
                return;
            }
        }
    }
}

void read_input() {
    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        dq.push_back(i);
    }
    for (int i = 1; i <= M; i++) {
        int x;
        cin >> x;
        // 1번 행동
        solve(x - 1);
    }
    cout << answer << endl;
}
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    read_input();
    return 0;
}