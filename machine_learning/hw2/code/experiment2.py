from __future__ import division
import perceptron as pt


print "# QUESTION TWO #####"
print "Classic Perceptron algorithm ----------------------------------------"
learningRates = [0.5, 0.1, 0.001, 0.0001]
print "Determining best learning rate out of: 0.5, 0.1, 0.001, or 0.0001"
bestRate = 1
bestMistakes = 9999 #6,414 examples in the training data
for rate in learningRates:
    perceptron = pt.Perceptron(rate)
    perceptron.classicTrain('a5a.train')
    if perceptron.mistakes < bestMistakes:
        bestRate = rate
        bestMistakes = perceptron.mistakes
        bestPerceptron = perceptron
print "Best learning rate: " + str(bestRate) + "\tMistakes made: " + str(bestMistakes)
print
bestPerceptron.test('a5a.train')
print "Training set classified with classic Perceptron:"
print "Learning rate: " + str(bestRate) + "\tAccuracy: " + str(bestPerceptron.correctClassifications) + "/" + str(bestPerceptron.testSpace) + " or " + str(bestPerceptron.getTestAccuracy()) + "%"
print

bestPerceptron.test('a5a.test')
print "Test set classified with classic Perceptron:"
print "Learning rate: " + str(bestRate) + "\tAccuracy: " + str(bestPerceptron.correctClassifications) + "/" + str(bestPerceptron.testSpace) + " or " + str(bestPerceptron.getTestAccuracy()) + "%"

print "Margin Perceptron algorithm ----------------------------------------"
margins = [0, 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5]
bestMargin = 0
bestMistakes = 9999 #6,414 examples in the training data
for margin in margins:
    perceptron = pt.Perceptron(bestRate)
    perceptron.margin = margin
    perceptron.marginTrain('a5a.train')
    print perceptron.weightVector
