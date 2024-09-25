def solution(array, commands):
    answer = []
    
    for i, j, k in commands:
        temp_arr = sorted(array[i-1:j])
        answer.append(temp_arr[k-1])
    
    return answer