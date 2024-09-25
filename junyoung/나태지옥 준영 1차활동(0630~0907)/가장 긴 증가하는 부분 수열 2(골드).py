# 이런 방법은 도대체 어떻게 생각해내는 거지??
# 세상에는 개 미친 사람들이 많다
# 이분 탐색 활용 LIS 알고리즘
# 수열의 원소를 앞에서 부터 탐색, 축적, 가장 큰 원소보다 작은 원소 발견시
# 축적된 수열에서 해당원소보다 큰 원소 중 가장 작은 원소와 교체(여기서 이진탐색 활용)


N = int(input())

A = list(map(int, input().split()))

def LIS_Algorithm(array):
    '''LIS 알고리즘, 가장 긴 수열 길이 반환'''
    result = [0]
    
    for item in array:
        if result[-1] < item:
            result.append(item)
        else:
            result = LIS_Binary_Search(result, item)
    
    print(len(result) - 1)


def LIS_Binary_Search(array, target):
    '''이진 탐색 후 값 교환한 수열 반환'''
    start = 0
    end = len(array) - 1
    mid = (start + end) // 2
    
    while start <= end:
        if array[mid] < target:
            start = mid + 1
        else:
            end = mid - 1
        mid = (start + end) // 2
    array[start] = target
    
    return array
        
LIS_Algorithm(A)