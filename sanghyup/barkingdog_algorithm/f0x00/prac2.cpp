#include <bits/stdc++.h>
using namespace std;

bool func2(int arr[], int N)
{
    for (int i = 0; i < N; i++)
    {
        for (int j = i + 1; j < N; j++)
        {
            if (arr[i] + arr[j] == 100)
            {
                return 1;
            }
        }
    }
    return 0;
}

int main()
{
    int a[3] = {1, 52, 48};
    int b[2] = {50, 42};
    int c[4] = {4, 13, 63, 87};

    cout << func2(a, 3) << endl;
    cout << func2(b, 2) << endl;
    cout << func2(c, 4) << endl;
}