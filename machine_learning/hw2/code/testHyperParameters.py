from __future__ import division
import perceptron as pt

learningRates = [1, 0.25,  0.5, 0.75, 0.1, 0.001, 0.0001]
margins = [0, 1, 1.5, 2, 2.5, 3, 4, 5]
trainFilePaths = ['splits/split1.train', 'splits/split2.train', 'splits/split3.train', 'splits/split4.train', 'splits/split5.train', 'splits/split6.train']
testFilePaths = ['splits/split1.test', 'splits/split2.test', 'splits/split3.test', 'splits/split4.test', 'splits/split5.test', 'splits/split6.test']

print "## Classic Perceptron 6-fold cross validation for learning rate ##"
bestCorrect = -1
bestRate = 1
for index in range(len(learningRates)):
    currentCorrect = 0
    for fileIndex in range(6):
        perceptron = pt.Perceptron(learningRates[index], 1)
        perceptron.classicTrain(trainFilePaths[fileIndex])
        perceptron.test(testFilePaths[fileIndex])
        currentCorrect += perceptron.correctClassifications
    currentCorrect = currentCorrect / 6
    print "Learning Rate: " + str(learningRates[index]) + "\tAccuracy: " + str(round((currentCorrect/perceptron.testSpace)*100,3)) + "%"
    if currentCorrect > bestCorrect:
        bestRate = learningRates[index]
        bestCorrect = currentCorrect
print "Best learning rate for Classic Perceptron: " + str(bestRate)
print

print "## Margin Perceptron 6-fold cross validation for learning rate ##"
bestCorrect = -1
bestRate = 1
for index in range(len(learningRates)):
    currentCorrect = 0
    for fileIndex in range(6):
        perceptron = pt.Perceptron(learningRates[index], 1)
        perceptron.marginTrain(trainFilePaths[fileIndex])
        perceptron.test(testFilePaths[fileIndex])
        currentCorrect += perceptron.correctClassifications
    currentCorrect = currentCorrect / 6
    print "Learning Rate: " + str(learningRates[index]) + "\tAccurracy: " + str(round((currentCorrect/perceptron.testSpace)*100,3)) + "%"
    if currentCorrect > bestCorrect:
        bestRate = learningRates[index]
        bestCorrect = currentCorrect
print "Best learning rate for Margin Perceptron: " + str(bestRate)
print

print "## Margin Perceptron 6-fold cross validation for margin ##"
bestCorrect = -1
bestMargin = 0
for index in range(len(margins)):
    currentCorrect = 0
    for fileIndex in range(6):
        perceptron = pt.Perceptron(0.5, 1)
        perceptron.margin = margins[index]
        perceptron.marginTrain(trainFilePaths[fileIndex])
        perceptron.test(testFilePaths[fileIndex])
        currentCorrect += perceptron.correctClassifications
    currentCorrect = currentCorrect / 6
    print "Margin: " + str(margins[index]) + "\tAccurracy: " + str(round((currentCorrect/perceptron.testSpace)*100,3)) + "%"
    if currentCorrect > bestCorrect:
        bestMargin = margins[index]
        bestCorrect = currentCorrect
print "Best margin for Margin Perceptron: " + str(bestMargin)
print

print "## Aggressive Perceptron with margin 6-fold cross validation for margin ##"
bestCorrect = -1
bestMargin = 0
for index in range(len(margins)):
    currentCorrect = 0
    for fileIndex in range(6):
        perceptron = pt.Perceptron(0.5, 1)
        perceptron.margin = margins[index]
        perceptron.aggressiveMarginTrain(trainFilePaths[fileIndex])
        perceptron.test(testFilePaths[fileIndex])
        currentCorrect += perceptron.correctClassifications
    currentCorrect = currentCorrect / 6
    print "Margin: " + str(margins[index]) + "\tAccurracy: " + str(round((currentCorrect/perceptron.testSpace)*100,3)) + "%"
    if currentCorrect > bestCorrect:
        bestMargin = margins[index]
        bestCorrect = currentCorrect
print "Best margin for Aggressive Perceptron with margin: " + str(bestMargin)

