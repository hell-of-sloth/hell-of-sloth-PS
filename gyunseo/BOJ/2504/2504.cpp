#include <iostream>
#include <stack>
#include <string>
#define endl '\n'
#define FOR(idx, s, e) for(int idx = (s); idx < (e); ++idx)
#define watch(x) cout << (#x) << " is " << x << endl
using namespace std;
using ll = long long;
string str;
stack<char> S;
ll tmp = 1, ans =0; 
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    getline(cin, str);
    // cout << str << endl;
    int len_str = str.size();
    FOR(i, 0, len_str) {
        if (S.empty()) {
            if (str[i] == '(') {
                tmp *= (ll)2;
            }
            else if (str[i] == '[') {
                tmp *= (ll)3;
            }
            // 빈 스택인데 ]나 )가 들어 오면 안된다
            else {
                cout << 0 << endl;
                return 0;
            }
            S.push(str[i]);
            continue;
        }
        if (!S.empty()) {
            if (S.top() == '(' and str[i] == ')') {
                if (str[i  - 1] == '(')
                    ans += tmp;
                tmp /= (ll)2;
                S.pop();
                continue;
            }
            else if (S.top() == '[' and str[i] == ']') {
                if (str[i - 1] == '[')
                    ans += tmp;
                tmp /= (ll)3;
                S.pop();
                continue;
            }
            else if (S.top() == '(' and str[i] == '(') {
                tmp *= (ll)2;
                S.push(str[i]);
            } else if (S.top() == '(' and str[i] == '[') {
                tmp *= (ll)3;
                S.push(str[i]);
            } else if (S.top() == '[' and str[i] == '(') {
                tmp *= (ll)2;
                S.push(str[i]);
            } else if (S.top() == '[' and str[i] == '[') {
                tmp *= (ll)3;
                S.push(str[i]);
            } else {
                cout << 0 << endl;
                return 0;
            }
        }
    }
    if (S.empty()) {
        cout << ans << endl;
    }
    else {
        cout << 0 << endl;
    }
    return 0;
}