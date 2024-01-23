#include <iostream>
#include <tuple>
#include <vector>
#define endl '\n'
using namespace std;
const int MAX = 8;
const int INF = (int)1e9;

// 방향
enum eDir
{
    Up,
    Down,
    Left,
    Right
};

// CCTV type
enum eCamType
{
    One = 1,
    Two,
    Three,
    Four,
    Five
};
// 상하좌우
int di[4] = {-1, 1, 0, 0};
int dj[4] = {0, 0, -1, 1};
int N, M, ans = INF;
int matrix[MAX][MAX];
vector<pair<int, int>> cam_pos;
vector<int> cam_type;
int cam_cnt = 0;

// out of bound
bool OOB(int i, int j)
{
    if (i < 0 || i >= N)
        return true;
    if (j < 0 || j >= M)
        return true;
    return false;
}
// CCTV 최대 개수 8개
// 사각지대를 카운트해서 반환
int GetZeroCount()
{
    int cnt = 0;
    // O(NM) 최악 64
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            if (matrix[i][j] == 0)
                cnt++;
        }
    }
    return cnt;
}

void UpdateMatrix(int cur_i, int cur_j, int dir)
{
    while (true)
    {
        cur_i += di[dir];
        cur_j += dj[dir];
        // matrix 범위 밖이거나, 벽을 만나면 리턴
        if (OOB(cur_i, cur_j) || matrix[cur_i][cur_j] == 6)
            return;
        // cam이 있거나, 이미 -1로 업데이트 돼있다면, 건너 뛰기
        if (matrix[cur_i][cur_j] >= 1 && matrix[cur_i][cur_j] <=5)
            continue;
        matrix[cur_i][cur_j] += -1;
    }
}

void UndoUpdateMatrix(int cur_i, int cur_j, int dir)
{
    while (true)
    {
        cur_i += di[dir];
        cur_j += dj[dir];
        // matrix 범위 밖이거나, 벽을 만나면 리턴
        if (OOB(cur_i, cur_j) || matrix[cur_i][cur_j] == 6)
            return;
        if (matrix[cur_i][cur_j] <= -1)
            matrix[cur_i][cur_j] += +1;
    }
}
void FireLaser(pair<int, int> &cur_cam_pos, int cur_cam_type, int cur_cam_dir)
{
    int cur_i, cur_j;
    tie(cur_i, cur_j) = cur_cam_pos;
    switch (cur_cam_type)
    {
    case One:
        UpdateMatrix(cur_i, cur_j, cur_cam_dir);
        break;
    case Two:
        UpdateMatrix(cur_i, cur_j, cur_cam_dir);
        switch (cur_cam_dir)
        {
        case Up:
            UpdateMatrix(cur_i, cur_j, Down);
            break;
        case Down:
            UpdateMatrix(cur_i, cur_j, Up);
            break;
        case Left:
            UpdateMatrix(cur_i, cur_j, Right);
            break;
        case Right:
            UpdateMatrix(cur_i, cur_j, Left);
            break;
        }
        break;
    case Three:
        UpdateMatrix(cur_i, cur_j, cur_cam_dir);
        switch (cur_cam_dir)
        {
        case Up:
            UpdateMatrix(cur_i, cur_j, Right);
            break;
        case Down:
            UpdateMatrix(cur_i, cur_j, Left);
            break;
        case Left:
            UpdateMatrix(cur_i, cur_j, Up);
            break;
        case Right:
            UpdateMatrix(cur_i, cur_j, Down);
            break;
        }
        break;
    case Four:
        UpdateMatrix(cur_i, cur_j, cur_cam_dir);
        switch (cur_cam_dir)
        {
        case Up:
            UpdateMatrix(cur_i, cur_j, Left);
            UpdateMatrix(cur_i, cur_j, Right);
            break;
        case Down:
            UpdateMatrix(cur_i, cur_j, Right);
            UpdateMatrix(cur_i, cur_j, Left);
            break;
        case Left:
            UpdateMatrix(cur_i, cur_j, Down);
            UpdateMatrix(cur_i, cur_j, Up);
            break;
        case Right:
            UpdateMatrix(cur_i, cur_j, Up);
            UpdateMatrix(cur_i, cur_j, Down);
            break;
        }
        break;
    case Five:
        UpdateMatrix(cur_i, cur_j, Up);
        UpdateMatrix(cur_i, cur_j, Down);
        UpdateMatrix(cur_i, cur_j, Left);
        UpdateMatrix(cur_i, cur_j, Right);
        break;
    }
}

void UndoFireLaser(pair<int, int> &cur_cam_pos, int cur_cam_type, int cur_cam_dir)
{
    int cur_i, cur_j;
    tie(cur_i, cur_j) = cur_cam_pos;
    switch (cur_cam_type)
    {
    case One:
        UndoUpdateMatrix(cur_i, cur_j, cur_cam_dir);
        break;
    case Two:
        UndoUpdateMatrix(cur_i, cur_j, cur_cam_dir);
        switch (cur_cam_dir)
        {
        case Up:
            UndoUpdateMatrix(cur_i, cur_j, Down);
            break;
        case Down:
            UndoUpdateMatrix(cur_i, cur_j, Up);
            break;
        case Left:
            UndoUpdateMatrix(cur_i, cur_j, Right);
            break;
        case Right:
            UndoUpdateMatrix(cur_i, cur_j, Left);
            break;
        }
        break;
    case Three:
        UndoUpdateMatrix(cur_i, cur_j, cur_cam_dir);
        switch (cur_cam_dir)
        {
        case Up:
            UndoUpdateMatrix(cur_i, cur_j, Right);
            break;
        case Down:
            UndoUpdateMatrix(cur_i, cur_j, Left);
            break;
        case Left:
            UndoUpdateMatrix(cur_i, cur_j, Up);
            break;
        case Right:
            UndoUpdateMatrix(cur_i, cur_j, Down);
            break;
        }
        break;
    case Four:
        UndoUpdateMatrix(cur_i, cur_j, cur_cam_dir);
        switch (cur_cam_dir)
        {
        case Up:
            UndoUpdateMatrix(cur_i, cur_j, Left);
            UndoUpdateMatrix(cur_i, cur_j, Right);
            break;
        case Down:
            UndoUpdateMatrix(cur_i, cur_j, Right);
            UndoUpdateMatrix(cur_i, cur_j, Left);
            break;
        case Left:
            UndoUpdateMatrix(cur_i, cur_j, Down);
            UndoUpdateMatrix(cur_i, cur_j, Up);
            break;
        case Right:
            UndoUpdateMatrix(cur_i, cur_j, Up);
            UndoUpdateMatrix(cur_i, cur_j, Down);
            break;
        }
        break;
    case Five:
        UndoUpdateMatrix(cur_i, cur_j, Up);
        UndoUpdateMatrix(cur_i, cur_j, Down);
        UndoUpdateMatrix(cur_i, cur_j, Left);
        UndoUpdateMatrix(cur_i, cur_j, Right);
        break;
    }
}
void PrintMatrix() {
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cout << matrix[i][j] << " ";
        }cout << endl;
    }
}
// 상태 공간 트리를 DFS로 순회한다.
// level이 N번째 캠을 의미
void DFS(int level, int direction)
{
    if (level >= cam_cnt) {
        int tmp_ans = GetZeroCount();
        if (tmp_ans < ans) {
            // cout << "tmp_ans: " << tmp_ans << endl;
            // cout << "-------------\n";
            // PrintMatrix();
            ans = tmp_ans;
        }
        return;
    }
    // level번째 캠을 direction 방향으로 돌린다.
    // cout << "cur_level_cam_pos: " << cam_pos[level].first << " " << cam_pos[level].second << endl;
    // cout << "cam_type:" << cam_type[level] << endl;
    // cout << "cur_cam_dir: " << direction << endl;
    FireLaser(cam_pos[level], cam_type[level], direction);
    // PrintMatrix();
    // 0이 상, 1이 하, 2가 좌, 3이 우
    for (int n_dir = Up; n_dir <= Right; n_dir++)
    {
        DFS(level + 1, n_dir);
    }
    // undo latest laser fire
    UndoFireLaser(cam_pos[level], cam_type[level], direction);
}
// 문제를 푼다.
void Solve()
{
    int cur_i, cur_j;
    // 4의 8승 65000정도이다.
    // pos는 최대 8개이다.
    // 1000ms이니깐, 브루트포스로 풀면된다.
    for (int dir = Up; dir <= Right; dir++)
    {
        DFS(0, dir);
    }
    cout << ans << endl;
}
// user의 cli input을 읽어서 변수에 집어 넣는다.
void ReadUserInput()
{
    cin >> N >> M;
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            cin >> matrix[i][j];
            if (matrix[i][j] < 1)
                continue;
            if (matrix[i][j] > 5)
                continue;
            // 1~5이면 cctv
            cam_pos.push_back(make_pair(i, j));
            cam_type.push_back(matrix[i][j]);
            cam_cnt++;
        }
    }
    // cout << "cam_cnt: " << cam_cnt << endl;
}

int main()
{
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    ReadUserInput();
    Solve();
    return 0;
}