#include <iostream>
#include <string>
#include <cctype>
#include <algorithm>
#include <map>
#define endl '\n'
#define watch(x) cout << (#x) << ": " << (x) << endl

using namespace std;
const int MAX = 100'000;
// N: 도감에 등록된 포켓몬 개수
// M: 맞춰야 하는 문제 수 
int N, M;
map<string, string> table;

void solve(string &str) {
    // 검색 시 lg(N)
    cout << table[str] << endl;
}

void readUserInput() {
    string s;
    cin >> N >> M;
    // '\n' 흡수
    cin.ignore();
    // N*lgN
    for (int i = 1; i <= N; i++) {
        getline(cin, s);
        // map은 rb tree 삽입 시 lgN
        table[s] = to_string(i);
        table[to_string(i)] = s;
    }
    // M*lgN
    for (int i = 1; i <= M; i++) {
        getline(cin, s);
        solve(s);
    }
}
int main() {
    ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    readUserInput();
    return 0;
}