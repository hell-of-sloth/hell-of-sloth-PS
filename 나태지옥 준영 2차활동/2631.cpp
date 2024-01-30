/*
N명의 아이들이 임의의 순서로 줄을 서 있을 때, 번호 순서대로 
배치하기 위해 옮겨지는 아이의 최소 수를 구하는 프로그램을 작성하시오.
*/

#include <iostream>
#include <vector>

using namespace std;

int main() {

    int N;
    cin >> N;

    vector<int> children;


    for (int i = 0; i < N; i++) {
        int child;
        cin >> child;

        if (children.empty()) {
            children.push_back(child);
        } else {
            if (child > children.back()) {
                children.push_back(child);
            } else {
                for (int j = 0; j < children.size(); j++) {
                    if (child <= children[j]) {
                        children[j] = child;
                        break;
                    }
                }
            }
        }
    }

    cout << N - children.size() << endl;
    return 0;
}