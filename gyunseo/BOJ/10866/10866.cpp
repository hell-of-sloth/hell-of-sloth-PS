#include <iostream>
#include <deque>
#define endl '\n'

using namespace std;
int N;
deque<int> dq; 

void Solve(string &cmd) {
  int x;
  if (cmd == "push_front") {
    cin >> x;
    dq.push_front(x);
  }
  else if (cmd == "push_back") {
    cin >> x;
    dq.push_back(x);
  }
  else if (cmd == "pop_front") {
    if (dq.empty()) cout << -1 << endl;
    else{
      cout << dq.front() << endl;
      dq.pop_front();
    }
  }
  else if (cmd == "pop_back") {
    if (dq.empty()) cout << -1 << endl;
    else{
      cout << dq.back() << endl;
      dq.pop_back();
    }
  }
  else if (cmd == "size") {
    cout << dq.size() << endl;
  }
  else if (cmd == "empty") {
    if (dq.empty()) cout << 1 << endl;
    else cout << 0 << endl;
  }
  else if (cmd == "front") {
    if (dq.empty()) cout << -1 << endl;
    else cout << dq.front() << endl; 
  }
  else if (cmd == "back") {
    if (dq.empty()) cout << -1 << endl;
    else cout << dq.back() << endl;
  }
}

void ReadUserInput() {
  cin >> N; 
  string cmd;
  int x;
  for (int i = 0; i < N; i++) {
    cin >> cmd;
    //debug
    // cout << i << ": " << cmd << " " << endl;
    Solve(cmd); 
    
  }
}

int main() {
  ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
  ReadUserInput();
  return 0;
}
