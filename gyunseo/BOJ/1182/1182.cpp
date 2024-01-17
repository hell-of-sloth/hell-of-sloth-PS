#include <iostream>
#define endl '\n'
using namespace std;
const int MAX = 20;
int N, S, ans = 0;
int seq[MAX + 1];
void readUserInput()
{
    cin >> N >> S;
    for (int i = 0; i < N; i++)
    {
        cin >> seq[i];
    }
}
void backtrack(int n, int summation, int bitmask_set)
{
    if (n == N)
    {
        if (summation == S && bitmask_set != 0x00)
        {
            // cout << "n: " << n << " summation: " << summation << endl;
            ans++;
        }

        return;
    }
    // n선택
    backtrack(n + 1, summation + seq[n], bitmask_set | (1 << n));
    // n 비선택
    backtrack(n + 1, summation, bitmask_set);
}
void solve()
{
    // N <= 20이고, time limit 2000ms이니깐
    // 시간 복잡도는 여유가 있음
    // 0이 아니고 S다!!
    // 부분 수열은 연속적이지 않아도 된다.
    // 아무 것도 선택안한 거는 제외 해야 된다.
    backtrack(0, 0, 0);
    cout << ans << endl;
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