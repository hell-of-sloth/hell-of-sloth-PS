#include <algorithm>
#include <iostream>
#include <queue>
#include <tuple>
#include <vector>

#define F first
#define S second
#define VI vector<int>
#define PI pair<int, int>
#define PQ priority_queue
#define endl '\n'

using namespace std;

auto cmp = [](const PI &root, const PI &child) -> bool {
    if (root.S == child.S)
        return root.F > child.F;
    return root.S < child.S;
};

// ans는 pull_over_cnt, cur는 현재 위치
int N, L, P, ans, cur;
PQ<PI, vector<PI>, decltype(cmp)> pq(cmp);
vector<PI> stations;

void solve() {
    // P 남아 있는 용량, L 남은 거리
    int i = 0;
    while (P < L) {
        // P의 용량만큼 달린다
        cur += P;
        L -= P;
        P = 0;
        // 지나왔던 주유소들을 우선 순위 큐에 넣어 놓는다
        while (i < N && stations[i].F <= cur) {
            pq.push(stations[i]);
            i++;
        }
        if (pq.size() == 0) {
            cout << -1;
            return;
        }
        int supply = pq.top().S;
        pq.pop();
        ans++;
        P += supply;
    }
    cout << ans;
}

void read_user_input() {
    cin >> N;
    for (int i = 0; i < N; i++) {
        int a, b;
        cin >> a >> b;
        stations.push_back({a, b});
    }
    cin >> L >> P;
    // 거리 순으로 정렬한다.
    sort(stations.begin(), stations.end(),
         [](const auto &X, const auto &Y) -> bool { return X.F < Y.F; });
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    read_user_input();
    solve();
}