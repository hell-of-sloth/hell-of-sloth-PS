#include <cassert>
#include <cstdlib>
#include <iostream>
#include <tuple>
#include <vector>

#define ASSERT(exp, msg) assert(exp &&msg)
#define endl '\n'
#define WATCH(X) cout << (#X) << " is " << X << endl
#define F first
#define S second
using namespace std;
const int MAX = 50;
int N, M, num_chicken, ans = (int)1e9;
int MATRIX[MAX][MAX];
vector<pair<int, int>> chicken_poses, home_poses;
int chicken_dists[2 * MAX];

int get_chicken_dist(pair<int, int> &home, pair<int, int> &chicken) {
    return abs(home.F - chicken.F) + abs(home.S - chicken.S);
}

int get_chicken_dist_for_city(int chicken_set) {
    int num_home = home_poses.size(), summation = 0;
    for (int i = 0; i < num_home; i++) {
        chicken_dists[i] = (int)1e9;
        for (int j = 0; j < num_chicken; j++) {
            if ((chicken_set & (1 << j)) == 0)
                continue;
            int tmp_dist = get_chicken_dist(home_poses[i], chicken_poses[j]);
            if (tmp_dist < chicken_dists[i])
                chicken_dists[i] = tmp_dist;
        }
    }
    for (int i = 0; i < num_home; i++) {
        summation += chicken_dists[i];
    }
    return summation;
}

int get_num_elements(int set) {
    if (set == 0)
        return 0;
    return set % 2 + get_num_elements(set / 2);
}
void solve() {
    num_chicken = chicken_poses.size();
    // WATCH(num_chicken);
    int max_chicken_set = (1 << num_chicken) - 1;
    for (int i = 1; i <= max_chicken_set; i++) {
        if (get_num_elements(i) > M) continue;
        int tmp_ans = get_chicken_dist_for_city(i);
        if (tmp_ans < ans)
            ans = tmp_ans;
    }
    cout << ans << endl;
}

void read_user_input() {

    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> MATRIX[i][j];
            if (MATRIX[i][j] == 1)
                home_poses.push_back({i, j});
            else if (MATRIX[i][j] == 2)
                chicken_poses.push_back({i, j});
        }
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    read_user_input();
    solve();
    return 0;
}
