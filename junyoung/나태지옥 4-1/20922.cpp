/*
같은 원소가 
K개 이하로 들어 있는 최장 연속 부분 수열의 길이를 구하려고
한다.

100000 이하의 양의 정수로 이루어진 길이가 
N인 수열이 주어진다.  이 수열에서 같은 정수를 
K개 이하로 포함한 최장 연속 부분 수열의 길이를 구하는 
프로그램을 작성해보자.
*/

#include <iostream>

using namespace std;

int solution(int* arr, int N, int K) {
    int end = 0, result = 0;
    int visited[100001] = {0, };

    for (int start = 0; start < N; start++) {
        while (end < N && visited[arr[end]] < K) {
            visited[arr[end]]++;
            end++;
        }
        result = max(result, end - start);
        visited[arr[start]]--;
    }

    return result;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    int N, K;
    cin >> N >> K;

    int arr[N];
    for (int i = 0; i < N; i++) {
        cin >> arr[i];
    }

    cout << solution(arr, N, K);

    return 0;
}