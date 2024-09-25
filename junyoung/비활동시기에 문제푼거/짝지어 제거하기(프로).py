def solution(s):
    # 스택을 사용하면 빠름
    
    s = list(s)
    
    if len(s) % 2 == 1:
        return 0
    
    
    stack = [s[0]]
    
    for c in s[1:]:
        if stack and stack[-1] == c:
            stack.pop()
        else:
            stack.append(c)
        
    if stack:
        return 0
    else:
        return 1
    
    