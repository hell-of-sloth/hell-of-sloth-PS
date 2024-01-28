#include <iostream>
#include <string>

using namespace std;

int main()
{
    int N;

    cin >> N;

    int sum = 0;
    for (int i = 1; i <= N; i++)
    {
        if (i % 3 == 0 || i % 5 == 0)
        {
            sum += i;
        }
    }
    cout << sum;
}