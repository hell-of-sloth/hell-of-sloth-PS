#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int dijkstra(int N, const vector<pair<int, int>> farm[]) {
    vector<int> dist(N+1, 1000000000);
    priority_queue<pair<int, int>> pq; // 우선순위 큐, 힙

    dist[1] = 0;
    pq.push({0, 1});

    while (!pq.empty()) {
        int cost = -pq.top().first;
        int current = pq.top().second;
        pq.pop();

        if (dist[current] < cost) continue;

        for (int i = 0; i < farm[current].size(); i++) {
            int next = farm[current][i].first;
            int nextCost = cost + farm[current][i].second;

            if (dist[next] > nextCost) {
                dist[next] = nextCost;
                pq.push({-nextCost, next}); // priority_queue는 기본적으로 가장 큰 값이 위로 오도록 정렬되므로 음수로 넣어줌
            }
        }
    }

    return dist[N];
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    int N, M;

    cin >> N >> M;

    vector<pair<int, int>> farm[N+1];

    int A, B, C;

    for (int i = 0; i < M; i++) {
        cin >> A >> B >> C;
        farm[A].push_back({B, C});
        farm[B].push_back({A, C});
    }

    cout << dijkstra(N, farm);

    return 0;
}