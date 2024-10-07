/*
1부터 N까지의 수를 오름차순으로 쓴 수열 1 2 3 ... N을 생각하자.

그리고 '+'나 '-', 또는 ' '(공백)을 숫자 사이에 삽입하자(+는 더하기, 
-는 빼기, 공백은 숫자를 이어 붙이는 것을 뜻한다). 이렇게 만든 수식의 값을 
계산하고 그 결과가 0이 될 수 있는지를 살피자.

N이 주어졌을 때 수식의 결과가 0이 되는 모든 수식을 찾는 프로그램을 작성하라.
*/

#include <iostream>
#include <string>
#include <cctype>

using namespace std;

void backtrack(int N, int num, string exp) { // N = 총 길이, num = 현재 숫자, exp = 현재까지의 수식
    if (num == N) {
        int val = 0;
        string temp = ""; 

        // 수식을 계산
        for (int i = exp.size() - 1 ; i >= 0 ; i--) {
            if (exp[i] == '+') {
                val += stoi(temp);
                temp = "";
            } else if (exp[i] == '-') {
                val -= stoi(temp);
                temp = "";
            } else if (exp[i] == ' ') {
                continue;
            } else {
                temp = exp[i] + temp;
            }
        }
        val += stoi(temp);

        // 계산 결과가 0이면 출력
        if (val == 0) {
            cout << exp << "\n";
        }
        return;
    }

    backtrack(N, num + 1, exp + " " + to_string(num + 1));
    backtrack(N, num + 1, exp + "+" + to_string(num + 1));
    backtrack(N, num + 1, exp + "-" + to_string(num + 1));
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int T;
    cin >> T;

    for (int i = 0; i < T; i++)
    {
        int N;
        cin >> N;
        backtrack(N, 1, "1");
        cout << "\n";
    }
}
