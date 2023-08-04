for i in range(int(input())):
    m, n = map(int, input().split())
    c = m*n
    while(m%n):
        m , n = n , m%n
    print(int(c/n))