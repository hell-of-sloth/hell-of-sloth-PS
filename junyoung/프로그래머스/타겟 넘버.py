answer = 0

def DFS(numbers, target, result):
    global answer
    
    if not numbers:
        if target == result:
            answer += 1
        return
        
    temp = numbers.pop(0)
    DFS(numbers, target, result+temp)
    DFS(numbers, target, result-temp)
    numbers.insert(0, temp)

def solution(numbers, target):
    global answer
    
    DFS(numbers, target, 0)
    
    return answer