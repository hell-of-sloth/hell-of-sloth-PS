def lcm(a, b):
    mul = a*b
    while a%b:
        a,b = b, a%b
    return mul//b

_ = input()
nums = list(map(int, input().split()))

ans = 1

for i in nums:
    ans = lcm(ans, i)

if ans==1 or ans in nums:
    ans =ans*min(nums)

print(ans)