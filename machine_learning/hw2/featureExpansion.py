import math


def fr(x1, x2, r):
    if (4 * math.pow(x1, 4) + 16 * math.pow(x2, 4)) <= r:
        return 1
    else:
        return -1

def expansion(x1, x2, r):
    if (-4 * math.pow(x1, 4) - 16 * math.pow(x2, 4)) >= -r:
        return 1
    else:
        return -1


for x1 in range(1, 100):
    for x2 in range(1, 100):
        for r in range(1, 100):
            if fr(x1, x2, r) != expansion(x1, x2, r):
                print str(x1) + " " + str(x2) + " " + str(r)
