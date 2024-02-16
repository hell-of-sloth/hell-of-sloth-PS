#include <cassert>
#include <iostream>
#include <tuple>
#include <vector>
#define endl '\n'
#define ASSERT(exp, msg) assert(exp &&msg)
#define F first
#define S second

using namespace std;

const int MAX = 8;
const int INF = (int)1e9;
const int MARK = 7;
int N, M, ans = INF;
int MATRIX[MAX + 1][MAX + 1];
int matrix[MAX + 1][MAX + 1];
enum e_dir { UP, RIGHT, DOWN, LEFT };
int di[4] = {-1, 0, 1, 0};
int dj[4] = {0, 1, 0, -1};
vector<pair<int, int>> cam_poses;

bool OOB(int i, int j) {
    if (i < 0 || i >= N)
        return true;
    if (j < 0 || j >= M)
        return true;
    return false;
}

void update_matrix(int i, int j, int dir) {
    bool iteration_trigger = true;
    int ni, nj;
    dir = dir % 4;
    ASSERT(dir >= 0 && dir < 4, "dir is out of bound");
    // init the ni, nj values
    ni = i;
    nj = j;
    while (iteration_trigger) {
        ni = ni + di[dir];
        nj = nj + dj[dir];
        if (OOB(ni, nj)) {
            iteration_trigger = false;
            continue;
        }
        if (matrix[ni][nj] == 6) {
            iteration_trigger = false;
            continue;
        }
        // 만약에 [ni][nj]에 감시 카메라가 있다면 그냥 지나가기
        if (matrix[ni][nj] >= 1 && matrix[ni][nj] <= 5)
            continue;
        // 만약에 [ni][nj]에 미리 누가 마킹을 했다면 그냥 지나가기
        if (matrix[ni][nj] == MARK)
            continue;
        // 위 조건 어느 것도 해당이 안된다면 0이라는 건데, 그 때는 마킹하기
        if (matrix[ni][nj] == 0) {
            matrix[ni][nj] = MARK;
            continue;
        }
        // 엥 이것도 아니면 오류를 낸다
        ASSERT(false, "you cannot reach here in update_matrix!");
    }
}

int pow(int n, int k) {
    int ret = 1;
    for (int i = 0; i < k; i++) {
        ret *= n;
    }
    return ret;
}

void copy_the_init_state_of_MATRIX() {
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            matrix[i][j] = MATRIX[i][j];
        }
    }
}

int get_num_zeroes() {
    int cnt = 0;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            if (matrix[i][j] == 0)
                cnt++;
        }
    }
    return cnt;
}

void solve() {

    int num_cam = cam_poses.size();
    // cout << "num_cam: " << num_cam << endl;
    int brute_cases = pow(4, num_cam);
    // cout << "brute_cases: " << brute_cases << endl;
    int cur_dir, cur_cam_type, tmp_case, cur_cam_i, cur_cam_j;
    for (int brute_case = 0; brute_case < brute_cases; brute_case++) {
        copy_the_init_state_of_MATRIX();
        // 4진법으로 모든 케이스를 cover
        tmp_case = brute_case;
        for (int i = 0; i < num_cam; i++) {
            cur_dir = tmp_case % 4;
            tie(cur_cam_i, cur_cam_j) = cam_poses[i];
            cur_cam_type = MATRIX[cur_cam_i][cur_cam_j];
            ASSERT(cur_cam_type >= 1 && cur_cam_type <= 5,
                   "cur_cam_type is out of bound!");
            ASSERT(cur_dir >= UP && cur_dir <= LEFT,
                   "cur_dir is out of bound!");
            tmp_case = tmp_case / 4;
            switch (cur_cam_type) {
            case 1:
                update_matrix(cur_cam_i, cur_cam_j, cur_dir);
                break;
            case 2:
                update_matrix(cur_cam_i, cur_cam_j, cur_dir);
                update_matrix(cur_cam_i, cur_cam_j, cur_dir + 2);
                break;
            case 3:
                update_matrix(cur_cam_i, cur_cam_j, cur_dir);
                update_matrix(cur_cam_i, cur_cam_j, cur_dir + 1);
                break;
            case 4:
                update_matrix(cur_cam_i, cur_cam_j, cur_dir);
                update_matrix(cur_cam_i, cur_cam_j, cur_dir + 1);
                update_matrix(cur_cam_i, cur_cam_j, cur_dir + 3);
                break;
            case 5:
                update_matrix(cur_cam_i, cur_cam_j, cur_dir);
                update_matrix(cur_cam_i, cur_cam_j, cur_dir + 1);
                update_matrix(cur_cam_i, cur_cam_j, cur_dir + 2);
                update_matrix(cur_cam_i, cur_cam_j, cur_dir + 3);
                break;
            }
        }
        int tmp_ans = get_num_zeroes();
        if (tmp_ans < ans)
            ans = tmp_ans;
    }
}

void read_user_input() {
    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> MATRIX[i][j];
            if (MATRIX[i][j] >= 1 && MATRIX[i][j] <= 5)
                cam_poses.push_back({i, j});
        }
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    read_user_input();
    solve();
    cout << ans << endl;
    return 0;
}
