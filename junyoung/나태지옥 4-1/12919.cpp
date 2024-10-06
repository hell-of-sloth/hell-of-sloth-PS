/*
S를 T로 바꾸는 게임이다. 문자열을 바꿀 때는 
다음과 같은 두 가지 연산만 가능하다.

문자열의 뒤에 A를 추가한다.
문자열의 뒤에 B를 추가하고 문자열을 뒤집는다.
주어진 조건을 이용해서 S를 T로 만들 수 있는지 없는지 
알아내는 프로그램을 작성하시오.
*/

#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

void solution(string S, string T, int& result) {
    if (S.length() == T.length()) {
        if (S == T) {
            result = 1;
        }
        return;
    }

    if (T.back() == 'A') {
        solution(S, T.substr(0, T.length() - 1), result);
    }

    if (T.front() == 'B') {
        string temp = T.substr(1, T.length() - 1);
        reverse(temp.begin(), temp.end());
        solution(S, temp, result);
    }
}

int main() {

    string S, T;
    cin >> S >> T;

    int result = 0;

    solution(S, T, result);

    cout << result;

    return 0;
}