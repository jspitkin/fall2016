from __future__ import division
import perceptron as pt

learningRates = [1, 0.5, 0.75, 0.1, 0.001, 0.0001]
margins = [0, 1, 1.5, 2, 3, 5]
trainFilePaths = ['splits/split1.train', 'splits/split2.train', 'splits/split3.train', 'splits/split4.train', 'splits/split5.train', 'splits/split6.train']
testFilePaths = ['splits/split1.test', 'splits/split2.test', 'splits/split3.test', 'splits/split4.test', 'splits/split5.test', 'splits/split6.test']

print "## Classic Perceptron 6-fold cross validation for learning rate ##"
bestCorrect = -1
bestRate = 1
for index in range(6):
    perceptron = pt.Perceptron(learningRates[index], 1)
    perceptron.classicTrain(trainFilePaths[index])
    perceptron.test(testFilePaths[index])
    print "Learning Rate: " + str(learningRates[index]) + "\tAccurracy: " + str(perceptron.correctClassifications) + "/" + str(perceptron.testSpace) + "\t" + str(perceptron.getTestAccuracy()) + "%"
    if perceptron.correctClassifications > bestCorrect:
        bestRate = learningRates[index]
        bestCorrect = perceptron.correctClassifications
print "Best learning rate for Classic Perceptron: " + str(bestRate)
print

print "## Margin Perceptron 6-fold cross validation for learning rate ##"
bestCorrect = -1
bestRate = 1
for index in range(6):
    perceptron = pt.Perceptron(learningRates[index], 1)
    perceptron.marginTrain(trainFilePaths[index])
    perceptron.test(testFilePaths[index])
    print "Learning Rate: " + str(learningRates[index]) + "\tAccurracy: " + str(perceptron.correctClassifications) + "/" + str(perceptron.testSpace) + "\t" + str(perceptron.getTestAccuracy()) + "%"
    if perceptron.correctClassifications > bestCorrect:
        bestRate = learningRates[index]
        bestCorrect = perceptron.correctClassifications
print "Best learning rate for Margin Perceptron: " + str(bestRate)
print

print "## Margin Perceptron 6-fold cross validation for margin ##"
bestCorrect = -1
bestMargin = 0
for index in range(6):
    perceptron = pt.Perceptron(0.5, 1)
    perceptron.margin = margins[index]
    perceptron.marginTrain(trainFilePaths[index])
    perceptron.test(testFilePaths[index])
    print "Margin: " + str(margins[index]) + "\tAccurracy: " + str(perceptron.correctClassifications) + "/" + str(perceptron.testSpace) + "\t" + str(perceptron.getTestAccuracy()) + "%"
    if perceptron.correctClassifications > bestCorrect:
        bestRate = learningRates[index]
        bestCorrect = perceptron.correctClassifications
print "Best margin for Margin Perceptron: " + str(bestMargin)
print

print "## Classic Perceptron 6-fold cross validation for learning rate. Epoch of 3 ##"
bestCorrect = -1
bestRate = 1
for index in range(6):
    perceptron = pt.Perceptron(learningRates[index], 3)
    perceptron.classicTrain(trainFilePaths[index])
    perceptron.test(testFilePaths[index])
    print "Learning Rate: " + str(learningRates[index]) + "\tAccurracy: " + str(perceptron.correctClassifications) + "/" + str(perceptron.testSpace * 3) + "\t" + str(perceptron.getTestAccuracy()) + "%"
    if perceptron.correctClassifications > bestCorrect:
        bestRate = learningRates[index]
        bestCorrect = perceptron.correctClassifications
print "Best learning rate for Classic Perceptron with epoch of 3: " + str(bestRate)
print

print "## Classic Perceptron 6-fold cross validation for learning rate. Epoch of 5 ##"
bestCorrect = -1
bestRate = 1
for index in range(6):
    perceptron = pt.Perceptron(learningRates[index], 5)
    perceptron.classicTrain(trainFilePaths[index])
    perceptron.test(testFilePaths[index])
    print "Learning Rate: " + str(learningRates[index]) + "\tAccurracy: " + str(perceptron.correctClassifications) + "/" + str(perceptron.testSpace * 5) + "\t" + str(perceptron.getTestAccuracy()) + "%"
    if perceptron.correctClassifications > bestCorrect:
        bestRate = learningRates[index]
        bestCorrect = perceptron.correctClassifications
print "Best learning rate for Classic Perceptron with epoch of 5: " + str(bestRate)
print

print "## Margin Perceptron 6-fold cross validation for learning rate. Epoch of 3 ##"
bestCorrect = -1
bestRate = 1
for index in range(6):
    perceptron = pt.Perceptron(learningRates[index], 3)
    perceptron.marginTrain(trainFilePaths[index])
    perceptron.test(testFilePaths[index])
    print "Learning Rate: " + str(learningRates[index]) + "\tAccurracy: " + str(perceptron.correctClassifications) + "/" + str(perceptron.testSpace * 3) + "\t" + str(perceptron.getTestAccuracy()) + "%"
    if perceptron.correctClassifications > bestCorrect:
        bestRate = learningRates[index]
        bestCorrect = perceptron.correctClassifications
print "Best learning rate for Margin Perceptron with epoch of 3: " + str(bestRate)
print

print "## Margin Perceptron 6-fold cross validation for learning rate. Epoch of 5 ##"
bestCorrect = -1
bestRate = 1
for index in range(6):
    perceptron = pt.Perceptron(learningRates[index], 5)
    perceptron.marginTrain(trainFilePaths[index])
    perceptron.test(testFilePaths[index])
    print "Learning Rate: " + str(learningRates[index]) + "\tAccurracy: " + str(perceptron.correctClassifications) + "/" + str(perceptron.testSpace * 5) + "\t" + str(perceptron.getTestAccuracy()) + "%"
    if perceptron.correctClassifications > bestCorrect:
        bestRate = learningRates[index]
        bestCorrect = perceptron.correctClassifications
print "Best learning rate for Margin Perceptron with epoch of 5: " + str(bestRate)
print

print "## Margin Perceptron 6-fold cross validation for margin. Epoch of 3 ##"
bestCorrect = -1
bestMargin = 0
for index in range(6):
    perceptron = pt.Perceptron(0.5, 3)
    perceptron.margin = margins[index]
    perceptron.marginTrain(trainFilePaths[index])
    perceptron.test(testFilePaths[index])
    print "Margin: " + str(margins[index]) + "\tAccurracy: " + str(perceptron.correctClassifications) + "/" + str(perceptron.testSpace * 3) + "\t" + str(perceptron.getTestAccuracy()) + "%"
    if perceptron.correctClassifications > bestCorrect:
        bestRate = learningRates[index]
        bestCorrect = perceptron.correctClassifications
print "Best margin for Margin Perceptron: " + str(bestMargin)
print

print "## Margin Perceptron 6-fold cross validation for margin. Epoch of 5 ##"
bestCorrect = -1
bestMargin = 0
for index in range(6):
    perceptron = pt.Perceptron(0.5, 5)
    perceptron.margin = margins[index]
    perceptron.marginTrain(trainFilePaths[index])
    perceptron.test(testFilePaths[index])
    print "Margin: " + str(margins[index]) + "\tAccurracy: " + str(perceptron.correctClassifications) + "/" + str(perceptron.testSpace * 5) + "\t" + str(perceptron.getTestAccuracy()) + "%"
    if perceptron.correctClassifications > bestCorrect:
        bestRate = learningRates[index]
        bestCorrect = perceptron.correctClassifications
print "Best margin for Margin Perceptron: " + str(bestMargin)
print
