#include <iostream>
#include <string>
#include <set>

using namespace std;

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    int N;
    string game;
    set<string> players;

    cin >> N >> game;

    for (int i = 0; i < N; i++) {
        string name;
        cin >> name;
        players.insert(name);
    }

    int count;
    count = players.size();

    if (game == "Y") {
        cout << count;
    } else if (game == "F") {
        cout << (count / 2);
    } else if (game == "O") {
        cout << (count / 3);
    }
    
    return 0;
}