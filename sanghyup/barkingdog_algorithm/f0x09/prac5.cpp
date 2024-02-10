#include <bits/stdc++.h>
using namespace std;

int board[100002];

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);

  int N, K;
  cin >> N >> K;
  fill(board, board + 100001, -1);
  queue<int> Q;
  Q.push(N);
  board[N] = 0;
  while (true) {
    if (Q.front() == K) {
      cout << board[Q.front()];
      break;
    }  // ranged based for 사용!
    if (Q.front() <= 50000 && board[2 * Q.front()] == -1) {
      Q.push(2 * Q.front());
      board[2 * Q.front()] = board[Q.front()] + 1;
    }
    if (Q.front() < 100000 && board[1 + Q.front()] == -1) {
      Q.push(1 + Q.front());
      board[1 + Q.front()] = board[Q.front()] + 1;
    }
    if (board[Q.front() - 1] == -1) {
      board[Q.front() - 1] = board[Q.front()] + 1;
      Q.push(Q.front() - 1);
    }
    Q.pop();
  }
}

// Authored by : BaaaaaaaaaaarkingDog
// Co-authored by : -
// http://boj.kr/ba53d62b7651443cbf7b2028c28ce197

/*
#include <bits/stdc++.h>
using namespace std;
#define X first
#define Y second
int dist[100002];
int n,k;
int main(void){
  ios::sync_with_stdio(0);
  cin.tie(0);
  cin >> n >> k;
  fill(dist, dist+100001,-1);
  dist[n] = 0;
  queue<int> Q;
  Q.push(n);
  while(dist[k] == -1){
    int cur = Q.front(); Q.pop();
    for(int nxt : {cur-1, cur+1, 2*cur}){
      if(nxt < 0 || nxt > 100000) continue;
      if(dist[nxt] != -1) continue;
      dist[nxt] = dist[cur]+1;
      Q.push(nxt);
    }
  }
  cout << dist[k];
}
*/