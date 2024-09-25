def calcul(a, b): # 게산함수
    temp = [a+b, a-b, a*b]
    if b != 0:
        temp.append(a//b)
    return temp

def solution(N, number):
    dp = [0] # 인덱스 별로 N이 몇개 들어간지 나타냄 ex)dp[4] : N이 4개로 이루어진 계산 결과들
    for i in range(1,9):
        cass_set = set() # 중복 결과 제거를 위해 set사용
        cass_set.add(int(str(N) * i))
        for j in range(1, i):
            for num1 in dp[j]:
                for num2 in dp[i - j]:
                    cass_set.update(calcul(num1, num2))
                    
        if number in cass_set:
            return i
        
        dp.append(cass_set)
    return -1