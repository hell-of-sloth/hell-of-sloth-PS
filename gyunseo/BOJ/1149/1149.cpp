#include <iostream>
#define endl '\n'
using namespace std;

const int MAX = 1000;
int N;
int rgb_cost[MAX + 1][3], dp[MAX + 1][3];

void readInput()
{
    cin >> N;
    for (int i = 1; i <= N; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            cin >> rgb_cost[i][j];
        }
    }
}

void DP()
{
    for (int i = 0; i < 3; i++)
    {
        dp[1][i] = rgb_cost[1][i];
    }
    for (int i = 2; i <= N; i++)
    {
        dp[i][0] = rgb_cost[i][0] + min(dp[i - 1][1], dp[i - 1][2]);
        dp[i][1] = rgb_cost[i][1] + min(dp[i - 1][0], dp[i - 1][2]);
        dp[i][2] = rgb_cost[i][2] + min(dp[i - 1][0], dp[i - 1][1]);
    }
}

void solve()
{
    DP();
    cout << min(min(dp[N][0], dp[N][1]), dp[N][2]) << endl;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    readInput();
    solve();
    return 0;
}
