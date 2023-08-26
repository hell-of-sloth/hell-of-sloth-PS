import sys

input = sys.stdin.readline

nums = []

for i in range(int(input())):
    nums.append(int(input()))

nums.sort()

print(round(sum(nums)/len(nums)))
print(nums[len(nums)//2])

dic={}
for i in nums:
    if i in dic:
        dic[i]+=1
    else:
        dic[i]=1
        
maximum=max(dic.values())
frequency=[]

for i in dic:
    if maximum==dic[i]:
        frequency.append(i)

try:
    print(frequency[1])
except:
    print(frequency[0])
print(max(nums)-min(nums))