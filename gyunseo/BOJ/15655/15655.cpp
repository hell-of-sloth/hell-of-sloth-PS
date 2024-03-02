#include <iostream>
#include <vector>
#include <algorithm>
#define endl '\n'
#define PB push_back
using namespace std;
// 첫째 줄에 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)
// 둘째 줄에 N개의 수가 주어진다. 입력으로 주어지는 수는 10,000보다 작거나 같은 자연수이다.
const int MAX = 8;
int N, M;
vector<int> v;

void print_elements_in_set(int set) {
    for (int i = 0; i < N; i++) {
        if (set & (1 << i)) cout << v[i] << " ";
    }
    cout << endl;
}
// 상태 공간 트리를 backtrack한다
// bitmasking으로 set param 정의
void backtrack(int level, int cur_idx, int set) {
    if (level == M) {
        print_elements_in_set(set);
        return;
    }

    for (int i = cur_idx + 1; i < N; i++) { 
        backtrack(level + 1, i, set | (1 << i));
    }
}

void solve() {
    sort(v.begin(), v.end());
    for (int i = 0; i < N; i++)
        backtrack(1, i, 1 << i);
}

void read_user_input() {
    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        int num;
        cin >> num;
        v.PB(num);
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    read_user_input();
    solve();
    return 0;
}
