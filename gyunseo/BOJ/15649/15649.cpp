#include <algorithm>
#include <cassert>
#include <iostream>
#include <vector>
#define endl '\n'
#define ASSERT(exp, msg) assert(exp &&msg)
using namespace std;
const int MAX = 8;
int N, M;
vector<int> seq;
bool is_used[MAX + 1];

void read_user_input() { cin >> N >> M; }

// 상태 공간 트리를 비트 마스킹 기법을 이용해 DFS로 순회
void dfs_bitmask(int level, int set) {
    if (level == M) {
        for (auto num : seq)
            cout << num << " ";
        cout << endl;
        return;
    }
    for (int i = 1; i <= N; i++) {
        if (set & (1 << i))
            continue;
        seq.push_back(i);
        dfs_bitmask(level + 1, set | (1 << i));
        seq.pop_back();
    }
}

// 상태 공간 트리를 DFS로 순회
void dfs(int level) {
    if (level == M) {
        for (auto num : seq)
            cout << num << " ";
        cout << endl;
        return;
    }

    for (int i = 1; i <= N; i++) {
        // 이미 뽑힌 숫자라면은 건너뛴다
        if (is_used[i])
            continue;
        seq.push_back(i);
        is_used[i] = true;
        dfs(level + 1);
        is_used[i] = false;
        seq.pop_back();
    }
}

// return n^k
int get_pow(int n, int k) {
    int ret = 1;
    for (int i = 0; i < k; i++) {
        ret *= n;
    }
    return ret;
}

void bruteforce() {
    int all_cases = get_pow(N + 1, M);
    // N^M (1600만 대략)
    for (int brute_case = 0; brute_case < all_cases; brute_case++) {
        int tmp_case = brute_case;
        bool is_breaked = false;
        // M
        while (!seq.empty())
            seq.pop_back();
        // N
        fill(is_used, is_used + MAX + 1, false);
        // M
        for (int i = 0; i < M; i++) {
            int cur_digit = tmp_case % (N + 1);
            if (cur_digit == 0) {
                is_breaked = true;
                break;
            }
            if (is_used[cur_digit]) {
                is_breaked = true;
                break;
            }
            seq.push_back(cur_digit);
            is_used[cur_digit] = true;
            tmp_case /= (N + 1);
        }
        if (is_breaked == false && seq.size() == M) {
            for (auto it = seq.rbegin(); it < seq.rend(); it++)
                cout << *it << " ";
            cout << endl;
        }
    }
}

void solve() {
    // nPm을 구현하는 문제

    // is_used 배열을 사용한 백트래킹
    for (int i = 1; i <= N; i++) {
        seq.push_back(i);
        is_used[i] = true;
        dfs(1);
        seq.pop_back();
        is_used[i] = false;
    }

    // bitmasking을 이용한 백트래킹
    // for (int i = 1; i <= N; i++) {
    // seq.push_back(i);
    // dfs_bitmask(1, (1 << i));
    // seq.pop_back();
    //}

    // (N + 1) 진법을 이용하여 구하기
    // bruteforce();
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    read_user_input();
    solve();
    return 0;
}
