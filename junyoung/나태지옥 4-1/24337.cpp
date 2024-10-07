/*
가희가 3번 건물을 볼 수 없는 이유는 3번 건물 왼쪽에 있는 2번 건물의 높이가
3번 건물보다 높기 때문입니다. 그리고, 단비가 1번 건물을 볼 수 없는 이유는
1번 건물보다 오른쪽에 있는 2번 건물과 3번 건물이 1번 건물보다 높기 때문입니다.
가희와 단비 사이에 있는 건물의 개수 N과 가희가 볼 수 있는 건물의 개수 a,
단비가 볼 수 있는 건물의 개수 b가 주어집니다.
사전 순으로 가장 앞서는 N개의 건물 높이 정보를 출력해 주세요.
*/

#include <iostream>
#include <vector>

using namespace std;

int main()
{
    // 시간줄이기
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N, a, b;
    string s;

    cin >> N >> a >> b;

    if (N < (a + b - 1))
    {
        cout << -1;
        return 0;
    }

    if (a == 1)
    {
        s += to_string(b) + " ";
        for (int i = 0; i < N - (a + b - 1); i++)
        {
            s += "1 ";
        }
        for (int i = b - 1; i > 0; i--)
        {
            s += to_string(i) + " ";
        }
    }
    else
    {
        for (int i = 0; i < N - (a + b - 1); i++)
        {
            s += "1 ";
        }
        if (a >= b)
        {
            for (int i = 1; i <= a; i++)
            {
                s += to_string(i) + " ";
            }
            for (int i = b - 1; i > 0; i--)
            {
                s += to_string(i) + " ";
            }
        }
        else
        {
            for (int i = 1; i < a; i++)
            {
                s += to_string(i) + " ";
            }
            for (int i = b; i > 0; i--)
            {
                s += to_string(i) + " ";
            }
        }
    }

    cout << s;

    return 0;
}