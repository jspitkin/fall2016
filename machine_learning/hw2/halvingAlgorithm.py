import math

def sign(value):
    if value >= 0:
        return 1
    else:
        return -1

for targetFunction in range(1, 81):
    allPoints = []
    for x1 in range(-80, 81):
        for x2 in range(-80, 81):
            allPoints.append((x1, x2))

    mistakeCount = 0
    remainingFunctions = []
    for x in range(1, 81):
        remainingFunctions.append(x)

    while len(remainingFunctions) > 1:
        for point in allPoints:
            x1 = point[0]
            x2 = point[1]
            if math.pow(x1, 2) + math.pow(x2, 2) <= math.pow(targetFunction, 2):
                label = 1
            else:
                label = -1
        
            positiveFunctions = []
            negativeFunctions = []
            for function in remainingFunctions:
                if math.pow(x1, 2) + math.pow(x2, 2) <= math.pow(function, 2):
                    positiveFunctions.append(function)
                else:
                    negativeFunctions.append(function)
            
            if len(positiveFunctions) >= len(negativeFunctions):
                if 1 != label:
                    mistakeCount = mistakeCount + 1
                    remainingFunctions = [x for x in negativeFunctions]

            if len(negativeFunctions) >= len(positiveFunctions):
                if -1 != label:
                    mistakeCount = mistakeCount + 1
                    remainingFunctions = [x for x in positiveFunctions]


    print "Found function: " + str(remainingFunctions)
    print "Number of mistakes: " + str(mistakeCount)
