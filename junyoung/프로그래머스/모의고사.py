def solution(answers):
    
    supo = [
        [1, 2, 3, 4, 5],
        [2, 1, 2, 3, 2, 4, 2, 5],
        [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]
    ]
    supo1_l = 5
    supo2_l = 8
    supo3_l = 10
    
    counts = [0, 0, 0]
    
    for i in range(len(answers)):
        if answers[i] == supo[0][i%supo1_l]:
            counts[0] += 1
        if answers[i] == supo[1][i%supo2_l]:
            counts[1] += 1
        if answers[i] == supo[2][i%supo3_l]:
            counts[2] += 1
    
    max_count = max(counts)
    result = []
    for i in range(3):
        if max_count == counts[i]:
            result.append(i+1)
    return result