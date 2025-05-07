#include <iostream>
#include <string>
#include <queue>
#include <stack>

#define endl '\n'
#define fastio cin.tie(0)->sync_with_stdio(0)
#define watch(x) cout << (#x) << " is " << (x) << endl
using namespace std;
using ll = long long;

ll N;
string s;
stack<char> parenStack;
stack<ll> numStack;
stack<char> operatorStack;

void readInput() {
    cin >> s;
    watch(s);
    N = s.size();
    watch(N);
}

ll getWeight(char ch) {

}

void solve() {
    string ans;
    stack<ch> operators;
    for (const auto &ch : s) {
        if (isupper(ch)) {
            ans += ch;
        }
        else if (ch == '(') operators.push(ch);
        else if (ch == ')')
        else {

        }
    }
}

int main() {
    fastio;
    readInput();
    solve();
    return 0;
}