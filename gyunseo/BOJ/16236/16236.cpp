#include <bits/stdc++.h>
#define F first
#define S second
#define FOR(idx, s, e) for (int idx = (s); idx < (e); ++idx)
#define RFOR(idx, e, s) for (int idx = (e); idx >= (s); --idx)
#define VI vector<int>
#define PI pair<int, int>
#define TI tuple<int, int, int>
#define VL vector<ll>
#define PL pair<ll, ll>

#define endl '\n'
#define watch(x) cout << (#x) << " is " << x << endl
#define ASSERT(exp, msg) assert((exp) && (msg))
using namespace std;
using ll = long long;
using ull = unsigned long long;

struct Baby {
    int size = 2;
    int eaten = 0;
    PI pos;
};

const int MAX = 20, BABY = 9;
int di[] = {-1, 0, 0, 1}, dj[] = {0, -1, 1, 0};
vector<VI> BOARD(MAX, VI(MAX, 0));
int dist[MAX][MAX];
int N, ans;
auto cmp = [](TI &root, TI &child) -> bool {
    // 거리가 같으면
    int root_dist, root_i, root_j, child_dist, child_i, child_j;
    tie(root_dist, root_i, root_j) = root;
    tie(child_dist, child_i, child_j) = child;
    if (root_dist == child_dist) {
        PI root_pos = {root_i, root_j}, child_pos = {child_i, child_j};

        if (root_pos.F == child_pos.F) {
            return root_pos.S > child_pos.S;
        }
        return root_pos.F > child_pos.F;
    }
    // 거리가 다르면
    return root_dist > child_dist;
};

priority_queue<TI, vector<TI>, decltype(cmp)> PQ(cmp);
queue<PI> Q;
Baby baby;
bool OOB(int i, int j) {
    if (i < 0 || i >= N)
        return true;
    if (j < 0 || j >= N)
        return true;
    return false;
}
bool is_other_fish(int num) {
    if (num >= 1 and num <= 6)
        return true;
    return false;
}

// 거리, 좌표
TI bfs() {
    PQ.push({(int)1e9, 100, 100});
    Q.push(baby.pos);
    dist[baby.pos.F][baby.pos.S] = 1;
    while (!Q.empty()) {
        ASSERT(!Q.empty(), "Q is not empty");
        int ci, cj;
        tie(ci, cj) = Q.front();
        Q.pop();

        FOR(k, 0, 4) {
            int ni, nj;
            ni = ci + di[k];
            nj = cj + dj[k];
            if (OOB(ni, nj))
                continue;
            // 이미 왔던 곳은 되돌아 가지 않는다
            if (dist[ni][nj])
                continue;
            // 자기 초과의 사이즈면 못 지나간다
            if (is_other_fish(BOARD[ni][nj]) and BOARD[ni][nj] > baby.size)
                continue;
            dist[ni][nj] = dist[ci][cj] + 1;
            Q.push({ni, nj});
            if (is_other_fish(BOARD[ni][nj]) and BOARD[ni][nj] < baby.size) {
                PQ.push({dist[ci][cj], ni, nj});
            }
        }
    }
    // 잡아 먹지 못하면 bfs를 끝낸다
    return PQ.top();
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    FOR(i, 0, N) {
        FOR(j, 0, N) {
            cin >> BOARD[i][j];
            if (BOARD[i][j] == BABY) {
                // 아기 상어 위치 초기화
                baby.pos = {i, j};
            }
        }
    }

    bool iteration = true;
    while (iteration) {
        int delta, ni, nj;
        tie(delta, ni, nj) = bfs();
        // watch(delta);
        // watch(ni);
        // watch(nj);
        if (ni == 100 and nj == 100) {
            iteration = false;
            continue;
        }
        BOARD[baby.pos.F][baby.pos.S] = 0;
        BOARD[ni][nj] = 0;
        baby.eaten += 1;
        if (baby.eaten == baby.size) {
            baby.size += 1;
            baby.eaten = 0;
        }
        ans += delta;
        baby.pos = {ni, nj};
        // PQ, Q, dist 초기화
        while (!PQ.empty())
            PQ.pop();
        while (!Q.empty())
            Q.pop();
        fill(&dist[0][0], &dist[MAX - 1][MAX], 0);
    }
    cout << ans << endl;

    return 0;
}