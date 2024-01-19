#include <iostream>
#include <vector>
#define endl '\n'
using namespace std;

int N, M;
vector<int> v;
void readUserInput()
{
	// cin ==> console input 정도로 생각하면 됩니다.
	// console input에 들어간 버퍼? 를 N과 M 밀어 넣는다
	cin >> N >> M;
}

void backtrack(int cur_num, int level, int set)
{
	if (level > M)
	{
		return;
	}
	v.push_back(cur_num);
	for (int i = 1; i <= N; i++)
	{
		if (set & (1 << i))
			continue;
		backtrack(i, level + 1, set | (1 << i));
	}
	if (level == M)
	{
		for (auto e : v)
			cout << e << " ";
		cout << endl;
	}
	v.pop_back();
}
void solve()
{
	// nPm을 구현하는 문제
	for (int i = 1; i <= N; i++)
		backtrack(i, 1, (1 << i));
}
int main()
{
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	readUserInput();
	solve();
	return 0;
}
