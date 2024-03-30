def solution(sizes):
    
    min_w = 0
    min_h = 0
    
    for m in sizes:
        if m[0] > m[1]:
            w = m[0]
            h = m[1]
        else:
            w = m[1]
            h = m[0]
        
        if min_w < w:
            min_w = w
        if min_h < h:
            min_h = h
                 
    return min_h * min_w