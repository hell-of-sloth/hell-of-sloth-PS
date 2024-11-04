# 체감 난이도 2/10, 슬라이싱 이용하면 쉬움, 명령어 파싱만 주의


def solve():
    N = int(input())
    original = list(map(int, input().split()))
    com_num = int(input())
    com = list(input().split())
    
    com_idx = 0
    for i in range(com_num):
        if com[com_idx] == 'I':
            idx = int(com[com_idx + 1])
            num = int(com[com_idx + 2])
            com_idx += 3
            original = original[:idx] + list(map(int, com[com_idx:com_idx + num])) + original[idx:]
            com_idx += num
            
    return original[:10]

for t in range(1, 11):
    print("#{} {}".format(t, ' '.join(map(str, solve()))))
