m , n = map(int, input().split())

ans = []

def dfs():
    if len(ans) == n:
        print(*ans)
        return
    
    if not len(ans): val = 0
    else: val = ans[-1]
    for i in range(val+1,m+1):
        ans.append(i)
        dfs()
        ans.pop()
dfs()


