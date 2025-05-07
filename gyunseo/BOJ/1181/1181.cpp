#include <iostream>
#include <set>
#include <string>

#define endl '\n'
#define fastio cin.tie(0)->sync_with_stdio(0)

using namespace std;
using ll = long long;

struct Compare {
    bool operator() (const string &lhs, const string &rhs) const {
        if (lhs.size() == rhs.size()) return lhs < rhs;
        return lhs.size() < rhs.size();
    }
};

int main() {
    fastio;
    set<string, Compare> s;
    ll n;
    cin >> n;
    for (ll i = 0; i < n; ++i) {
        string ss;
        cin >> ss;
        s.insert(ss);
    }
    for (auto it = s.begin(); it != s.end(); ++it) {
        cout << *it << endl;
    }
    return 0;
}