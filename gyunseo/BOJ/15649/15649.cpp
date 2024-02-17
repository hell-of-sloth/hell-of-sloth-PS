#include <cassert>
#include <iostream>
#include <vector>
#include <algorithm>
#define endl '\n'
#define ASSERT(exp, msg) assert(exp &msg)
using namespace std;
const int MAX = 8;
int N, M;
vector<int> v;
bool is_issued[MAX + 10];

void read_user_input() { cin >> N >> M; }

void backtrack(int cur_num, int level, int set) {
	if (level == M) {
		for (auto num : v) cout << num << " ";
		cout << endl;
		return;
	}
	for (int i = 1; i <= N; i++) {
		if (set & (1 << i)) continue;
		v.push_back(i);
		backtrack(i, level + 1, set | (1 << i));
		v.pop_back();
	}
}

void dfs(int cur_num, int level) {
	if (level == M) {
		for (auto num : v) cout << num << " ";
		cout << endl;
		return;
	}
	for (int i = 1; i <= N; i++) {
		if (find(v.begin(), v.end(), i) != v.end()) {
			continue;
		}	

		v.push_back(i);
		dfs(i, level + 1);
		v.pop_back();
	}
}

//int pow(int n, int k) {
	//int ret = 1;
	//for (int i = 0; i < k; i++) {
		//ret *= n;
	//}
	//return ret;
//}

void solve() {
    // nPm을 구현하는 문제
    for (int i = 1; i <= N; i++){
		v.push_back(i);
        backtrack(i, 1, (1 << i));
		//dfs(i, 1);
		v.pop_back();
	}
	// 진법을 이용하여 구하기
	//int all_cases = pow(N + 1, M);
	//cout << "all_cases: " << all_cases << endl;
	//for (int brute_case = 0; brute_case < all_cases; brute_case++) {
		//int tmp_case = brute_case;
		//while (!v.empty()) v.pop_back();
		//fill(is_issued, is_issued + MAX + 10, false);
		//for (int i = 0; i < M; i++) {
			//int cur_digit = tmp_case % (N + 1);
			//if (cur_digit == 0) break;
			//if (is_issued[cur_digit]) break;
			//v.push_back(cur_digit);
			//is_issued[cur_digit] = true;
			//tmp_case /= (N + 1);
		//}
		//if (v.size() == M) {
			//for(auto it = v.rbegin(); it < v.rend(); it++)
				//cout << *it << " ";
			//cout << endl;
		//}
	//}		
}
int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    read_user_input();
    solve();
    return 0;
}
