#include <string>
#include <vector>
#include <iostream>

using namespace std;

int find_non_visited(vector<bool> visited) {
    for (unsigned int i = 0; i < visited.size(); i++) {
        if (!visited[i]) {
            return i;
        }
    }
    return -1;
}

int solution(int n, vector<vector<int>> computers) {
    int answer = 0;
    vector<bool> visited(n, false);
    
    while (true) {
        int start = find_non_visited(visited);
        if (start == -1) {
            break;
        }
        answer++;
        vector<int> stack;
        stack.push_back(start);
        while (!stack.empty()) {
            int current = stack.back();
            stack.pop_back();
            visited[current] = true;
            for (int i = 0; i < n; i++) {
                if (computers[current][i] == 1 && !visited[i]) {
                    stack.push_back(i);
                }
            }
        }
    }
    
    return answer;
}

int main() {
    int n = 3;
    vector<vector<int>> computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
    cout << solution(n, computers) << endl;
    n = 3;
    computers = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
    cout << solution(n, computers) << endl;
    return 0;
}