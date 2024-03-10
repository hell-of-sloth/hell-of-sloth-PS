#include <iostream>
#include <vector>
#define endl '\n'
using namespace std;

int N, K;
vector<int> summation;
void query(int s, int e) {
    // log N
    cout << fixed;
    cout.precision(2);
    if (s == 0) {
        cout << summation[e] / (double)(e - s + 1) << endl;
        return;
    }
    cout << (summation[e] - summation[s - 1]) / (double)(e - s + 1) << endl;
}

void read_user_input() {
    cin >> N >> K;

    for (int i = 0; i < N; i++) {
        int e;
        cin >> e;
        if (i == 0) {
            summation.push_back(e);
            continue;
        }
        summation.push_back(summation[i - 1] + e);
    }

    //DEBUG
    //for (auto s: summation) cout << s << " ";
    //cout << endl;
    for (int i = 0; i < K; i++) {
        int s, e;
        cin >> s >> e;
        query(s - 1, e - 1);
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    read_user_input();
    return 0;
}
