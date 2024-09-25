def solution(n, lost, reserve):
    temp_lost = set(lost) - set(reserve)
    temp_reserve = set(reserve) - set(lost)

    for l in temp_reserve:
        if l-1 in temp_lost:
            temp_lost.remove(l-1)
        elif l+1 in temp_lost:
            temp_lost.remove(l+1)
            
    return n-len(temp_lost)