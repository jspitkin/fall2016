largestWeight = 11
m = [0 for _ in range(largestWeight + 1)]
w = [7, 4, 3]
v = [13, 7, 4]


def maxValue(we):
    for weight in range(1, we + 1):
        for index in range(len(w)):
            if weight >= w[index]:  
                if v[index] + m[weight - w[index]] > m[weight]:
                    m[weight] = v[index] + m[weight - w[index]]
    return m[we]


print maxValue(11)
