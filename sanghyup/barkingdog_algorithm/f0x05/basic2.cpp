#include <bits/stdc++.h>
using namespace std;

int target[100001];
queue<int> input;  // 1,2,3,4....
stack<int> proc;
queue<char> ans;

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  int N;
  cin >> N;
  for (int i = 1; i <= N; i++) input.push(i);
  for (int i = 0; i < N; i++) cin >> target[i];

  for (int i : target) {
    if (i == 0) break;
    while (1) {
      if (!proc.empty() && i == proc.top()) {
        proc.pop();
        ans.push('-');
        break;
      } else {
        proc.push(input.front());
        if (input.empty()) {
          cout << "NO";
          return (0);
        }
        input.pop();
        ans.push('+');
      }
    }
  }
  while (!ans.empty()) {
    cout << ans.front() << '\n';
    ans.pop();
  }
}

// 바킹독 풀이
//  Authored by : haneulkimdev
//  Co-authored by : BaaaaaaaaaaarkingDog
//  http://boj.kr/7a18dc82bd4041aaa59c745f06d4ba60

/*
#include <bits/stdc++.h>
using namespace std;

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);
  int n;
  cin >> n;
  stack<int> S;
  int cnt = 1;
  string ans;
  while (n--) {
    int t;
    cin >> t;
    while (cnt <= t) {
      S.push(cnt++);
      ans += "+\n";
    }
    if (S.top() != t) {
      cout << "NO\n";
      return 0;
    }
    S.pop();
    ans += "-\n";
  }
  cout << ans;
}
*/

/*
현재 처리를 필요로 하는 수는 cnt이다. cnt 이상인 값 t가 주어지면 그 값을 pop할
수 있게 cnt가 t가 될 때 까지 push를 해야 한다(18-21줄). 만약 22번째 줄과 같이
S.top()과 t가 일치하지 않는다면 올바르지 않은 수열이다.
*/