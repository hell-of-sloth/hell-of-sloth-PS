#include <bits/stdc++.h>
using namespace std;

void extractNums(deque<int>& DQ, string& str) {  // [1,2,3,4] 형태
  int i = 1;
  while (1) {
    int end =
        str.find(',', i);  // find는 못찾을 시 string::npos 반환 : 통상적으로 -1
    if (end == -1) {  // 마지막 숫자 처리
      int end = str.find(']', i);
      DQ.push_back(stoi(str.substr(
          i,
          end -
              i)));  // substr은 (시작 인덱스, length)이다! 마지막 인덱스 아님!
      break;
    }
    DQ.push_back(stoi(str.substr(i, end - i)));
    i = end + 1;  // end 다음부터 다음 탐색 시작 해야함!
  }
}

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  int TN, N;
  string ops;
  string arr_s;

  cin >> TN;  // TC 횟수

  while (TN--) {
    bool isReversed = false;
    bool isError = false;
    deque<int> DQ;
    cin >> ops;  // operation
    cin >> N;    // 배열 길이
    cin >> arr_s;
    if (arr_s[1] != ']') extractNums(DQ, arr_s);  // 배열 추출
    for (auto c : ops) {
      if (c == 'D') {
        if (DQ.empty()) {
          cout << "error" << '\n';
          isError = true;
          break;
        }
        if (isReversed)
          DQ.pop_back();
        else
          DQ.pop_front();
      } else if (c == 'R') {
        isReversed = !isReversed;
      }
    }
    if (!isError) {
      cout << '[';
      if (isReversed) {
        for (auto it = DQ.end() - 1; it >= DQ.begin(); it--) {
          cout << *it;
          if (it != DQ.begin()) cout << ',';
        }
      } else {
        for (auto it = DQ.begin(); it < DQ.end(); it++) {
          cout << *it;
          if (it != DQ.end() - 1) cout << ',';
        }

        // for (auto i : DQ) {
        //   cout << i;
        //   if (i != *(DQ.end() - 1)) cout << ','; //중간의 값 i가 끝자리와
        //   같을 경우 문제 발생!
        // }
      }
      cout << ']' << '\n';
    }
  }
}