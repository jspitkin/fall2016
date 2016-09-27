import math
import random

def sign(value):
    if value >= 0:
        return 1
    else:
        return -1

targetFunction = 80
allPoints = []
for x1 in range(-80, 81):
    for x2 in range(-80, 81):
        if (math.pow(x1, 2) + math.pow(x2, 2)) <= math.pow(targetFunction, 2):
            allPoints.append((x1, x2, 1))
        else:
            allPoints.append((x1, x2, -1))

mistakeCount = 0
currentFunction = 40
notAllPointsPassed = True
while notAllPointsPassed:
    notAllPointsPassed = False
    for point in allPoints:
        x1 = point[0]
        x2 = point[1]
        label = point[2]

        if sign(math.pow(x1, 2) + math.pow(x2, 2) - math.pow(currentFunction, 2) - 1) == sign(label):
            if label == -1:
                currentFunction  = currentFunction - 1
            else:
                currentFunction = currentFunction + 1
            notAllPointsPassed = True
            mistakeCount = mistakeCount + 1
            break

print "Mistakes: " + str(mistakeCount)
print "True function: r = " + str(currentFunction)
