from __future__ import division
import perceptron as pt

learningRates = [0.5, 0.75, 0.1, 0.001, 0.0001]
margins = [0, 1, 1.5, 2, 2.5, 3, 4, 5]
print "Determining best learning rate for classical Perceptron algorithm. . ."
bestRate = 0.5
bestMistakes = 9999 #6,414 examples in the training data
for rate in learningRates:
    perceptron = pt.Perceptron(rate, 1)
    perceptron.classicTrain('a5a.train')
    if perceptron.mistakes < bestMistakes:
        bestRate = rate
        bestMistakes = perceptron.mistakes
        bestPerceptron = perceptron
print "Best learning rate: " + str(bestRate) + "\tUpdates made: " + str(bestMistakes) + "/" + str(bestPerceptron.trainingSpace) + " or " + str(bestPerceptron.getTrainingAccuracy()) + "%"

print
print "Determining best margin and learning rate for Margin Perceptron algorithm. . ."
bestMargin = 0
bestRate = 0.5
bestMistakes = 9999 #6,414 examples in the training data
for margin in margins:
    for rate in learningRates:
        perceptron = pt.Perceptron(rate, 1)
        perceptron.margin = margin
        perceptron.marginTrain('a5a.train')
        if perceptron.mistakes < bestMistakes:
            bestMistakes = perceptron.mistakes
            bestMargin = margin
            bestRate = rate
            bestPerceptron = perceptron
print "Best margin: " + str(bestMargin) + "\tUpdates made: " + str(bestMistakes) + "/" + str(perceptron.trainingSpace) + " or " + str(bestPerceptron.getTrainingAccuracy()) + "%"
print "Best learning rate: " + str(bestRate) + "\tUpdates made: " + str(bestMistakes) + "/" + str(perceptron.trainingSpace) + " or " + str(bestPerceptron.getTrainingAccuracy()) + "%"
print

print "Determining best learning rate for classical Perceptron algorithm with 3 epochs. . ."
bestRate = 0.5
bestMistakes = 9999 #6,414 examples in the training data
for rate in learningRates:
    perceptron = pt.Perceptron(rate, 3)
    perceptron.classicTrain('a5a.train')
    if perceptron.mistakes < bestMistakes:
        bestRate = rate
        bestMistakes = perceptron.mistakes
        bestPerceptron = perceptron
print "Best learning rate: " + str(bestRate) + "\tUpdates made: " + str(bestMistakes) + "/" + str(bestPerceptron.trainingSpace * 3) + " or " + str(bestPerceptron.getTrainingAccuracy()) + "%"

print "Determining best learning rate for classical Perceptron algorithm with 5 epochs. . ."
bestRate = 0.5
bestMistakes = 9999 #6,414 examples in the training data
for rate in learningRates:
    perceptron = pt.Perceptron(rate, 5)
    perceptron.classicTrain('a5a.train')
    if perceptron.mistakes < bestMistakes:
        bestRate = rate
        bestMistakes = perceptron.mistakes
        bestPerceptron = perceptron
print "Best learning rate: " + str(bestRate) + "\tUpdates made: " + str(bestMistakes) + "/" + str(bestPerceptron.trainingSpace * 5) + " or " + str(bestPerceptron.getTrainingAccuracy()) + "%"

print
print "Determining best margin and learning rate for Margin Perceptron algorithm with three epochs. . ."
bestMargin = 0
bestRate = 0.5
bestMistakes = 9999 #6,414 examples in the training data
for margin in margins:
    for rate in learningRates:
        perceptron = pt.Perceptron(rate, 3)
        perceptron.margin = margin
        perceptron.marginTrain('a5a.train')
        if perceptron.mistakes < bestMistakes:
            bestMistakes = perceptron.mistakes
            bestMargin = margin
            bestRate = rate
            bestPerceptron = perceptron
print "Best margin: " + str(bestMargin) + "\tUpdates made: " + str(bestMistakes) + "/" + str(perceptron.trainingSpace * 3) + " or " + str(bestPerceptron.getTrainingAccuracy()) + "%"
print "Best learning rate: " + str(bestRate) + "\tUpdates made: " + str(bestMistakes) + "/" + str(perceptron.trainingSpace * 3) + " or " + str(bestPerceptron.getTrainingAccuracy()) + "%"

print
print "Determining best margin and learning rate for Margin Perceptron algorithm with five epochs. . ."
bestMargin = 0
bestRate = 0.5
bestMistakes = 9999 #6,414 examples in the training data
for margin in margins:
    for rate in learningRates:
        perceptron = pt.Perceptron(rate, 5)
        perceptron.margin = margin
        perceptron.marginTrain('a5a.train')
        if perceptron.mistakes < bestMistakes:
            bestMistakes = perceptron.mistakes
            bestMargin = margin
            bestRate = rate
            bestPerceptron = perceptron
print "Best margin: " + str(bestMargin) + "\tUpdates made: " + str(bestMistakes) + "/" + str(perceptron.trainingSpace * 5) + " or " + str(bestPerceptron.getTrainingAccuracy()) + "%"
print "Best learning rate: " + str(bestRate) + "\tUpdates made: " + str(bestMistakes) + "/" + str(perceptron.trainingSpace * 5) + " or " + str(bestPerceptron.getTrainingAccuracy()) + "%"
print

print "Determining best margin for Aggressive Perceptron with Margin algorithm with three epochs. . ."
bestMargin = 0
bestMistakes = 9999 #6,414 examples in the training data
for margin in margins:
    perceptron = pt.Perceptron(0.5, 3)
    perceptron.margin = margin
    perceptron.aggressiveMarginTrain('a5a.train')
    if perceptron.mistakes < bestMistakes:
        bestMistakes = perceptron.mistakes
        bestMargin = margin
        bestPerceptron = perceptron
print "Best margin: " + str(bestMargin) + "\tUpdates made: " + str(bestMistakes) + "/" + str(perceptron.trainingSpace * 3) + " or " + str(bestPerceptron.getTrainingAccuracy()) + "%"
print

print "Determining best margin for Aggressive Perceptron with Margin algorithm with five epochs. . ."
bestMargin = 0
bestMistakes = 9999 #6,414 examples in the training data
for margin in margins:
    perceptron = pt.Perceptron(0.5, 5)
    perceptron.margin = margin
    perceptron.aggressiveMarginTrain('a5a.train')
    if perceptron.mistakes < bestMistakes:
        bestMistakes = perceptron.mistakes
        bestMargin = margin
        bestPerceptron = perceptron
print "Best margin: " + str(bestMargin) + "\tUpdates made: " + str(bestMistakes) + "/" + str(perceptron.trainingSpace * 5) + " or " + str(bestPerceptron.getTrainingAccuracy()) + "%"
print
