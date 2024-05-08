import sys

input = sys.stdin.readline

    
if __name__ == "__main__":
    n = int(input().rstrip())
    nums = [*map(int, input().rstrip().split())]
    lenNums = len(nums)
    dp = [0 for _ in range(lenNums)]
    dp[0] = nums[0]
    for i in range(1, lenNums):
        dp[i] = max(dp[i - 1] + nums[i], nums[i])

    print(max(dp))
        

        
