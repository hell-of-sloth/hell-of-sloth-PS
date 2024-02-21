#include <iostream>
#include <cassert>
#include <cstring>

using namespace std;

const int N = 10;
int matrix[N][N], tmp_matrix[N][N];
void read_user_input() {
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> matrix[i][j];
        }
    }
}

void rotate_clock_wise() {
    for (int i = 0; i < N; i++) {
        for (int j = 0 ; j < N; j++) {
            tmp_matrix[i][j] = matrix[N - 1 - j][i];
        }
    }
    memmove(matrix, tmp_matrix, sizeof(matrix));
}
void print_formatted_matrix() {
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cout.width(2);
            cout << matrix[i][j] << " ";
        }
        cout << endl;
    }
}
int main() {
    ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    read_user_input();
    cout << "====Before a Rotation=====" << endl;
    print_formatted_matrix();
    rotate_clock_wise();
    cout << "====After a Rotation=====" << endl;
    print_formatted_matrix();
    return 0;
}