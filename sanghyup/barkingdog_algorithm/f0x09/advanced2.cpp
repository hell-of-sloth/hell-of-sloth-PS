#include <bits/stdc++.h>
using namespace std;

int main() {
  ios::sync_with_stdio(0);
  cin.tie();
  int it;
  cin >> it;
  while (it--) {
    int N;
    cin >> N;
    int choice[100002];
    int vis[100002];
    int order[100002];
    fill(choice, choice + 100002, 0);
    fill(vis, vis + 100002, 0);
    fill(order, order + 100002, 0);

    for (int i = 1; i < N + 1; i++) {
      cin >> choice[i];
    }
    int count = 0;
    for (int i = 1; i < N + 1; i++) {
      if (vis[i] == 1) continue;
      if (choice[i] == i) {
        vis[i] = 1;
        continue;
      }
      queue<int> buffer;
      order[i] = 1;
      buffer.push(i);
      int cur = i;
      while (true) {
        if (vis[choice[cur]]) {
          count += order[cur];
          break;
        }
        if (order[choice[cur]]) {
          count += order[choice[cur]] - 1;
          break;
        }
        if (choice[cur] == choice[choice[cur]]) {
          vis[choice[cur]] = 1;
          count += order[cur];
          break;
        }
        buffer.push(choice[cur]);
        order[choice[cur]] = order[cur] + 1;
        cur = choice[cur];
      }
      while (!buffer.empty()) {
        vis[buffer.front()] = 1;
        buffer.pop();
      }
    }
    cout << count << '\n';
  }
}

// 버퍼에 기록하는 것이 아니라 한번 더 visited를 돌면서 진행
// 그냥 과감하게 한번 더 돌면서 횟수 카운트하면 이전 방문/현재 방문을 더 쉽게 처리 가능

// // Authored by : BaaaaaaaaaaarkingDog
// // Co-authored by : -
// // http://boj.kr/65dc47124e3c4f53a9e5e710e78fa881
// #include <bits/stdc++.h>
// using namespace std;

// int arr[100005];
// int n;
// int state[100005]; 

// const int NOT_VISITED = 0;
// const int CYCLE_IN = -1;

// void run(int x){
//   int cur = x;
//   while(true){
//     state[cur] = x;
//     cur = arr[cur];
//     // 이번 방문에서 지나간 학생에 도달했을 경우
//     if(state[cur] == x){
//       while(state[cur] != CYCLE_IN){
//         state[cur] = CYCLE_IN;
//         cur = arr[cur];
//       }
//       return;
//     }
//     // 이전 방문에서 지나간 학생에 도달했을 경우
//     else if(state[cur] != 0) return;
//   }
// }

// int main(void){
//   ios::sync_with_stdio(0);
//   cin.tie(0);
//   int t;
//   cin >> t;
//   while(t--){
//     cin >> n;
//     fill(state+1, state+n+1, 0);
//     for(int i = 1; i <= n; i++)
//       cin >> arr[i];
//     int ans = 0;
//     for(int i = 1; i <= n; i++){
//       if(state[i] == NOT_VISITED) run(i);
//     }
//     int cnt = 0;
//     for(int i = 1; i <= n; i++){
//       if(state[i] != CYCLE_IN) cnt++;
//     }
//     cout << cnt << '\n';
//   }
// }

// /*
// 블로그 풀이 : https://blog.encrypted.gg/499
// 유튜브 풀이 : https://youtu.be/yPuow6aACvE
// */