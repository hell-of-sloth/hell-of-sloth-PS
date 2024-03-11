#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int ziphap[101] = {0, };

// DFS로 사이클을 찾는다.
vector<int> DFS(int start, int current, vector<int> visited) {
    // 시작점과 현재 노드가 같으면 정답 사이클이므로 visited를 반환한다.
    if (start == current) {
        return visited;
    }
    
    // 이미 방문한 노드를 다시 방문하면 사이클이므로 visited를 초기화하고 빈 벡터를 반환한다.
    if (find(visited.begin(), visited.end(), current) != visited.end()) {
        visited.clear();
        return visited;
    }

    visited.push_back(current);
    return DFS(start, ziphap[current], visited);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N;
    cin >> N;
    for (int i = 1; i <= N; i++) {
        cin >> ziphap[i];
    }

    vector<int> answer;

    for (int i = 1; i <= N; i++) {
        vector<int> visited;
        visited.push_back(i);
        vector<int> result = DFS(i, ziphap[i], visited);
        if (result.size() > 0) {
            for (int j = 0; j < result.size(); j++) {
                answer.push_back(result[j]);
            }
        }
    }

    // 중복 제거
    sort(answer.begin(), answer.end());
    vector<int>::iterator it = unique(answer.begin(), answer.end());
    answer.erase(it, answer.end());

    cout << answer.size() << '\n'; // 갯수 출력
    for (int i = 0; i < answer.size(); i++) {
        cout << answer[i] << '\n';
    }
    


    return 0;
}