#include <bits/stdc++.h>

#define fastio cin.tie(0)->sync_with_stdio(0)
#define watch(x) cout << (#x) << " is " << (x) << endl
#define ASSERT(exp, msg) assert((exp) &&(msg))
#define EACH(x, a) for (const auto x: (a))

using namespace std;
using ll = long long;
using VL = vector<ll>;

ll N;

char root1, root2;
vector<char> tree1[30], tree2[30];

vector<string> split(string str, char deli) {
    istringstream iss(str);
    string buf;

    vector<string> res;
    while (getline(iss, buf, deli)) res.push_back(buf);
    return res;
}

void makeTree1(vector<string> &splited) {
    for (ll i = 0; i < 30; ++i) tree1[i].clear();
    stack<char> s;
    for (ll i = 0; i < splited.size(); ++i) {
        char toBePushed = splited[i][0];
        if (toBePushed == '#') {
            char toBeAddedChild = s.top();
            s.pop();

            // root
            if (s.empty()) {
                root1 = toBeAddedChild;
                continue;
            }
            ASSERT(s.top() - 'a' >= 0 and s.top() - 'a' < 30, "should be in boundary");
            tree1[s.top() - 'a'].push_back(toBeAddedChild);
            continue;
        }
        else s.push(splited[i][0]);
    }
}

void makeTree2(vector<string> &splited) {
    for (ll i = 0; i < 30; ++i) tree2[i].clear();
    stack<char> s;
    for (ll i = 0; i < splited.size(); ++i) {
        char toBePushed = splited[i][0];
        if (toBePushed == '#') {
            char toBeAddedChild = s.top();
            s.pop();

            // root
            if (s.empty()) {
                root2 = toBeAddedChild;
                continue;
            }
            ASSERT(s.top() - 'a' >= 0 and s.top() - 'a' < 30,
                   "should be in boundary");
            tree2[s.top() - 'a'].push_back(toBeAddedChild);
            continue;
        } else s.push(splited[i][0]);
    }
}

bool isIsomorphic(char node1, char node2) {
    // Retrieve the children of node1 and node2
    vector<char> children1 = tree1[node1 - 'a'];
    vector<char> children2 = tree2[node2 - 'a'];


    // 둘 다 자식이 없으면 isomorhpic
    if (children1.size() == 0 and children2.size() == 0) return true;
    

    if (children1.size() == 0 or children2.size() == 0) return false;

    if (children1.size() != children2.size()) return false;
    
    bool orSum = false;

    sort(children1.begin(), children1.end());
    sort(children2.begin(), children2.end());
    do {
        
        bool andSum = true;
        for (ll i = 0; i < children1.size(); ++i) {
            andSum = andSum and isIsomorphic(children1[i], children2[i]);
        }
        orSum = orSum or andSum;
    } while(next_permutation(children2.begin(), children2.end()));
    return orSum;
}

int main() {
    fastio;
    cin >> N;
    cin.ignore();
    for (ll i = 0; i < N; ++i) {
        string s1, s2;
        getline(cin, s1), getline(cin, s2);
        vector<string> splitedS1 = split(s1, ' '), splitedS2 = split(s2, ' ');
        makeTree1(splitedS1), makeTree2(splitedS2);
        if (isIsomorphic(root1, root2)) {
            cout << "The two trees are isomorphic." << endl;
        } else {
            cout << "The two trees are not isomorphic." << endl;
        }
    }
    
    return 0;
}