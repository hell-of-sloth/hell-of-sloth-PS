#include <iostream>
#define endl '\n'
using namespace std;
using ll = long long;
ll A, B, C;
void readUserInput()
{
    cin >> A >> B >> C;
}
// 어차피 a는 고정이니깐 고려 X
ll power(ll b)
{
    // base case
    if (b == 1)
        return A % C;

    // 계산 결과를 dp로 처리하기에는 너무 메모리가 많이 소모됨
    // 어차피 싱글 쓰레드니깐,  재귀 트리는 하나로 계속 파고 들어 가다가 반대편 트리로 가는 구조 그래서 변수 하나로 제어 가능함
    // 중국인 나머지 정리가 기본적으로 쓰임
    ll memo = power(b / 2);
    // 짝수면
    if (b % 2 == 0)
        return memo * memo % C;
    // 홀수면
    return memo * memo % C * A % C;
}
void solve()
{
    cout << power(B) << endl;
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