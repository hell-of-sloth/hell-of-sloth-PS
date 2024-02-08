#include <iostream>
#include <queue>
#define endl '\n'

using namespace std;
const int MAX = (int)1e5;
int N, K;
queue<int> q;
int find_time[MAX+1];
bool OOB(int x) {
    if (x < 0 || x > MAX) return true;
    return false;
}
void bfs() {
    q.push(N); 
    find_time[N] = 1;
    while(!q.empty()) {
        auto cur_pos = q.front();
        q.pop();
        if (cur_pos == K) {
            cout << find_time[cur_pos] - 1 << endl;
            return;
        }
        for (int k = 0; k < 3; k++) {
            int next_pos;
            if (k == 0) next_pos = cur_pos - 1;
            else if (k == 1) next_pos = cur_pos + 1;
            else if (k == 2) next_pos = cur_pos * 2;
            
            if (OOB(next_pos)) continue;
            if (find_time[next_pos]) continue;
            find_time[next_pos] = find_time[cur_pos] + 1;
            q.push(next_pos);
        }
    }
}
void solve() {
    bfs();
}
void read_user_input() {
    cin >> N >> K; 
    //cout << N << " " << K << endl;
}
int main() {
    ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    read_user_input();
    solve();
    return 0;
}
