import sys

N = int(sys.stdin.readline().strip())

tree = [0] * (N+1)

for _ in range(N):
    a, b, c = map(int, sys.stdin.readline().split())
    tree[a] = (b, c)
    
def pseudo_inoder():
    global N
    
    count = 0
    
    visited = set(range(1, N+1))
    inorder = set()
    stack= [1]
    while len(inorder) != N:
        now = stack[-1]
        count += 1
        
        if now in visited:
            visited.remove(now)
        
        if tree[now][0] in visited:
            stack.append(tree[now][0])
            continue
        elif tree[now][1] in visited:
            inorder.add(now)
            stack.append(tree[now][1])
            continue
        else:
            inorder.add(now)
            stack.pop()

    return count-1

print(pseudo_inoder())