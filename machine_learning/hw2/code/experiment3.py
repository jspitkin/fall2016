from __future__ import division
import perceptron as pt

print "# QUESTION THREE ####"
print "Classic Perceptron algorithm in a batch setting -------------------------------------"
learningRates = [0.5, 0.1, 0.001, 0.0001]
print "Determining best learning rate with three epochs. . ."
bestRate = 1
bestMistakes = 9999 #6,414 examples in the training data
for rate in learningRates:
    perceptron = pt.Perceptron(rate, 3)
    perceptron.classicTrain('a5a.train')
    if perceptron.mistakes < bestMistakes:
        bestRate = rate
        bestMistakes = perceptron.mistakes
        bestPerceptron = perceptron
print "Best learning rate with three epochs: " + str(bestRate) + "\tUpdates made: " + str(bestMistakes) + "/" + str(bestPerceptron.trainingSpace * 3) + " or " + str(bestPerceptron.getTrainingAccuracy()) + "%"

print
bestPerceptron.test('a5a.train')
print "Training set classified with classic Perceptron with three epochs:"
print "Learning rate: " + str(bestRate) + "\tAccuracy: " + str(bestPerceptron.correctClassifications) + "/" + str(bestPerceptron.testSpace) + " or " + str(bestPerceptron.getTestAccuracy()) + "%"
print

bestPerceptron.test('a5a.test')
print "Test set classified with classic Perceptron with three epochs:"
print "Learning rate: " + str(bestRate) + "\tAccuracy: " + str(bestPerceptron.correctClassifications) + "/" + str(bestPerceptron.testSpace) + " or " + str(bestPerceptron.getTestAccuracy()) + "%"

print "Determining best learning rate with five epochs. . ."
bestRate = 1
bestMistakes = 9999 #6,414 examples in the training data
for rate in learningRates:
    perceptron = pt.Perceptron(rate, 5)
    perceptron.classicTrain('a5a.train')
    if perceptron.mistakes < bestMistakes:
        bestRate = rate
        bestMistakes = perceptron.mistakes
        bestPerceptron = perceptron
print "Best learning rate with five epochs: " + str(bestRate) + "\tUpdates made: " + str(bestMistakes) + "/" + str(bestPerceptron.trainingSpace * 5) + " or " + str(bestPerceptron.getTrainingAccuracy()) + "%"

print
bestPerceptron.test('a5a.train')
print "Training set classified with classic Perceptron with five epochs:"
print "Learning rate: " + str(bestRate) + "\tAccuracy: " + str(bestPerceptron.correctClassifications) + "/" + str(bestPerceptron.testSpace) + " or " + str(bestPerceptron.getTestAccuracy()) + "%"
print

bestPerceptron.test('a5a.test')
print "Test set classified with classic Perceptron:"
print "Learning rate: " + str(bestRate) + "\tAccuracy with five epochs: " + str(bestPerceptron.correctClassifications) + "/" + str(bestPerceptron.testSpace) + " or " + str(bestPerceptron.getTestAccuracy()) + "%"

print "Margin Perceptron algorithm ----------------------------------------"
print "Determining best margin with three epochs. . ."
margins = [0, 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5]
bestMargin = 0
bestMistakes = 9999 #6,414 examples in the training data
for margin in margins:
    perceptron = pt.Perceptron(bestRate, 3)
    perceptron.margin = margin
    perceptron.marginTrain('a5a.train')
    if perceptron.mistakes < bestMistakes:
        bestMistakes = perceptron.mistakes
        bestMargin = margin
        bestPerceptron = perceptron
print "Best margin: " + str(bestMargin) + "\tUpdates made: " + str(bestMistakes) + "/" + str(perceptron.trainingSpace * 3) + " or " + str(bestPerceptron.getTrainingAccuracy()) + "%"
print
print "Determining best learning rate with three epochs. . ."
bestRate = 1
bestMistakes = 9999 #6,414 examples in the training data
for rate in learningRates:
    perceptron = pt.Perceptron(rate, 3)
    perceptron.classicTrain('a5a.train')
    if perceptron.mistakes < bestMistakes:
        bestRate = rate
        bestMistakes = perceptron.mistakes
        bestPerceptron = perceptron
print "Best learning rate: " + str(bestRate) + "\tUpdates made: " + str(bestMistakes) + "/" + str(perceptron.trainingSpace * 3) + " or " + str(bestPerceptron.getTrainingAccuracy()) + "%"
print
bestPerceptron.test('a5a.train')
print "Training set classified with margin Perceptron:"
print "Learning rate: " + str(bestRate) + "\tAccuracy: " + str(bestPerceptron.correctClassifications) + "/" + str(bestPerceptron.testSpace) + " or " + str(bestPerceptron.getTestAccuracy()) + "%"
print

bestPerceptron.test('a5a.test')
print "Test set classified with margin Perceptron:"
print "Learning rate: " + str(bestRate) + "\tAccuracy: " + str(bestPerceptron.correctClassifications) + "/" + str(bestPerceptron.testSpace) + " or " + str(bestPerceptron.getTestAccuracy()) + "%"

print "Determining best margin with five epochs. . ."
margins = [0, 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5]
bestMargin = 0
bestMistakes = 9999 #6,414 examples in the training data
for margin in margins:
    perceptron = pt.Perceptron(bestRate, 5)
    perceptron.margin = margin
    perceptron.marginTrain('a5a.train')
    if perceptron.mistakes < bestMistakes:
        bestMistakes = perceptron.mistakes
        bestMargin = margin
        bestPerceptron = perceptron
print "Best margin: " + str(bestMargin) + "\tUpdates made: " + str(bestMistakes) + "/" + str(perceptron.trainingSpace * 5) + " or " + str(bestPerceptron.getTrainingAccuracy()) + "%"
print
print "Determining best learning rate with five epochs. . ."
bestRate = 1
bestMistakes = 9999 #6,414 examples in the training data
for rate in learningRates:
    perceptron = pt.Perceptron(rate, 5)
    perceptron.classicTrain('a5a.train')
    if perceptron.mistakes < bestMistakes:
        bestRate = rate
        bestMistakes = perceptron.mistakes
        bestPerceptron = perceptron
print "Best learning rate: " + str(bestRate) + "\tUpdates made: " + str(bestMistakes) + "/" + str(perceptron.trainingSpace * 5) + " or " + str(bestPerceptron.getTrainingAccuracy()) + "%"
print
bestPerceptron.test('a5a.train')
print "Training set classified with margin Perceptron:"
print "Learning rate: " + str(bestRate) + "\tAccuracy: " + str(bestPerceptron.correctClassifications) + "/" + str(bestPerceptron.testSpace) + " or " + str(bestPerceptron.getTestAccuracy()) + "%"
print

bestPerceptron.test('a5a.test')
print "Test set classified with margin Perceptron:"
print "Learning rate: " + str(bestRate) + "\tAccuracy: " + str(bestPerceptron.correctClassifications) + "/" + str(bestPerceptron.testSpace) + " or " + str(bestPerceptron.getTestAccuracy()) + "%"


