import perceptron as pt


perceptron = pt.Perceptron(0.5, 1, False)
perceptron.classicTrain('table2')
print "# QUESTION ONE #####"
weightVector = perceptron.weightVector[:5]
print "weight vector = " + str(weightVector)
print str(perceptron.mistakes) + " mistakes were made."
print
