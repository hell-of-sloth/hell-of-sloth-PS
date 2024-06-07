from typing import List
import sys

input = sys.stdin.readline


class Solution:
    def minPathSum(self, grid: List[List[int]]) -> int:
        len_row = len(grid)
        len_col = len(grid[0])
        for i in range(len_row):
            for j in range(len_col):
                if i == 0 and j == 0:
                    continue

                if i == 0:
                    grid[i][j] = grid[i][j - 1] + grid[i][j]
                elif j == 0:
                    grid[i][j] = grid[i - 1][j] + grid[i][j]
                else:
                    grid[i][j] = min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j]
        return grid[len_row - 1][len_col - 1]


if __name__ == "__main__":
    sol = Solution()
    grid = eval(input().rstrip())
    print(sol.minPathSum(grid))
