#include <iostream>
#include <queue>
#include <tuple>
#define endl '\n'
using namespace std;
const int MAX = 500;
// 2000ms, n, m <=500
// 전형적인 BFS 문제
// adjacent matrix 이용

int n, m;
int matrix[MAX][MAX], is_visited[MAX][MAX];
// 상하좌우
int di[4] = {-1, +1, 0, 0}, dj[4] = {0, 0, -1, +1};
int ans_cnt, ans_area;
queue<pair<int, int>> q;
void readUserInput()
{
    cin >> n >> m;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cin >> matrix[i][j];
        }
    }
    // DEBUG
    // for (int i = 0; i < n; i++)
    // {
    //     for (int j = 0; j < m; j++)
    //     {
    //         cout << matrix[i][j] << " ";
    //     }
    //     cout << endl;
    // }
    // DEBUG
}

void bfs(int start_i, int start_j)
{
    // queue를 비운다 미리
    while (!q.empty())
        q.pop();
    q.push(make_pair(start_i, start_j));
    // queue가 비워져 있지 않다면
    int cur_i, cur_j, next_i, next_j, tmp_area = 0;
    while (!q.empty())
    {
        cur_i = q.front().first;
        cur_j = q.front().second;
        q.pop();
        if (is_visited[cur_i][cur_j])
            continue;
        is_visited[cur_i][cur_j] = 1;
        tmp_area++;
        for (int k = 0; k < 4; k++)
        {
            next_i =  cur_i + di[k];
            next_j =  cur_j + dj[k];
            if (next_i < 0 || next_i >= n)
                continue;
            if (next_j < 0 || next_j >= m)
                continue;
            if (is_visited[next_i][next_j])
                continue;
            if (matrix[next_i][next_j] == 0) continue;
            q.push(make_pair(next_i, next_j));
        }
    }
    if (tmp_area > ans_area)
        ans_area = tmp_area;
}
void solve()
{
    // 여기서도 결국 각 파란 칸은 큐에 딱 한 번씩만 들어가서 시간복잡도는 칸의 갯수만큼만 필요합니다. 이 문제에서는 O(nm)입니다
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (is_visited[i][j] == 1)
                continue;
            if (matrix[i][j] == 0)
                continue;
            // 도화지에 그림이 그려져 있고, 이미 방문했던 도화지 좌표가 아니라면 bfs 진행
            bfs(i, j);
            ans_cnt++;
        }
    }
    cout << ans_cnt << endl;
    cout << ans_area << endl;
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