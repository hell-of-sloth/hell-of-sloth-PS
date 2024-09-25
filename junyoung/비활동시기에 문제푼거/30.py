import sys

N = sys.stdin.readline().rstrip()

def thirty_find():
    global N
    
    if '0' not in N:
        print(-1)
        return
    else:
        sum = 0
        for i in range(len(N)):
            sum += int(N[i])
        if sum % 3 != 0:
            print(-1)
            return
        else:
            N = sorted(N, reverse=True)
            print(''.join(N))
            return
    
thirty_find()