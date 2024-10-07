money = int(input())

coin = [500, 100, 50, 10, 5, 1] # 돈이다

money = 1000 - money

count = 0

for i in coin: # 계산이다
    count += money // i
    money %= i
    
print(count) # 답이다