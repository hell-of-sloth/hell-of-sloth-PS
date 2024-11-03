#include <iostream>
#include <string>

using namespace std;

void solve() {
    int t;
    cin >> t;

    string find_s;
    cin >> find_s;
    int len_s = find_s.length();
    
    string s;
    cin >> s;
    int len_s_total = s.length();

    int cnt = 0;

    for (int i = 0; i <= len_s_total - len_s; ++i) {
        if (s.substr(i, len_s) == find_s) { // substr 은 i부터 len_s만큼의 문자열을 반환
            cnt++;
        }
    }

    cout << "#" << t << " " << cnt << endl;
}

int main() {
    for (int t = 1; t <= 10; ++t) {
        solve();
    }
    return 0;
}
