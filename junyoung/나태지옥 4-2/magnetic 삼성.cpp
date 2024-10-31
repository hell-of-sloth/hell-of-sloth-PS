#include <iostream>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    for (int t = 1; t <= 10; t++) {
        int n;
        cin >> n;

        int map[100][100];
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                cin >> map[i][j];
            }
        }

        int cnt = 0;
        for (int j = 0; j < 100; j++) {
            int flag = 0;
            for (int i = 0; i < 100; i++) {
                if (map[i][j] == 1) {
                    flag = 1;
                } else if (map[i][j] == 2 && flag == 1) {
                    cnt++;
                    flag = 0;
                }
            }
        }

        cout << "#" << t << " " << cnt << "\n";
    }
}