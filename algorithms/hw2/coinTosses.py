L = {}
L["0 0"] = 1

def coinTosses(n, k):
    for i in range(1, n + 1):
        for j in range(0, i + 1):
            if j == 0:
                0.5 * L[str(i-1) + " " + str(j)]
            else:
                L[str(i) + " " + str(j)] = 0.5 * L[str(i-1) + " " + str(j-1)] + 0.5 * L[str(i-1) + " " + str(j)]


print coinTosses(2, 2)
