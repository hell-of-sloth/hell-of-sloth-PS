#include <iostream>
#include <string>
#include <stack>
#define endl '\n'
#define watch(x) cout << (#x) << " is " << (x) << endl
using namespace std;
char small_brackets[2] = {'(', ')'};
char big_brackets[2] = {'[', ']'};
stack<char> st;
bool solve(string &str)
{
    int str_len = str.length();
    while (!st.empty()) st.pop();
    for (int i = 0; i < str_len; i++) {
        for (int j = 0; j < 2; j++) {
            if (j == 0 && (str[i] == small_brackets[j] || str[i] == big_brackets[j])) {
                st.push(str[i]);
                continue;
            }
            if (str[i] == small_brackets[j] || str[i] == big_brackets[j])
            {
                if (!st.empty() && (st.top() == small_brackets[j-1]) && (str[i] == small_brackets[j]))
                {
                    st.pop();
                    continue;
                }
                if (!st.empty() && (st.top() == big_brackets[j - 1]) && (str[i] == big_brackets[j]))
                {
                    st.pop();
                    continue;
                }
                st.push(str[i]);
            }
        }
    }
    if (st.empty()) return true;
    return false;
}
void readUserInput() {
    bool iteration_trigger = true;
    string str;
    while (iteration_trigger) {
        getline(cin, str);
        if (str.compare(".") == 0) {
            iteration_trigger = false;
            continue;
        }
        if (solve(str)) {
            cout << "yes" << endl;
        }
        else {
            cout << "no" << endl;
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(0); cout.tie(0);
    readUserInput();
    return 0;
}