from __future__ import division
import perceptron as pt

print "# QUESTION TWO #####"
print "Classic Perceptron algorithm ----------------------------------------"

print "Training on 'a5a.train' with a learning rate of 0.25 and a epoch of 1"
bestPerceptron = pt.Perceptron(0.25, 1)
bestPerceptron.classicTrain('a5a.train')
print "Updates made: " + str(bestPerceptron.mistakes) + "/" + str(bestPerceptron.trainingSpace) + " or " + str(bestPerceptron.getTrainingAccuracy()) + "%"
print

bestPerceptron.test('a5a.train')
print "Training set classified with classic Perceptron:"
print "Accuracy: " + str(bestPerceptron.correctClassifications) + "/" + str(bestPerceptron.testSpace) + " or " + str(bestPerceptron.getTestAccuracy()) + "%"
print

bestPerceptron.test('a5a.test')
print "Test set classified with classic Perceptron:"
print "Accuracy: " + str(bestPerceptron.correctClassifications) + "/" + str(bestPerceptron.testSpace) + " or " + str(bestPerceptron.getTestAccuracy()) + "%"
print

print "Margin Perceptron algorithm ----------------------------------------"
print "Training on 'a5a.train' with a learning rate of 0.1, a margin of 4 and a epoch of 1"
bestPerceptron = pt.Perceptron(0.1, 1)
bestPerceptron.margin = 4
bestPerceptron.marginTrain('a5a.train')
print "Updates made: " + str(bestPerceptron.mistakes) + "/" + str(bestPerceptron.trainingSpace) + " or " + str(bestPerceptron.getTrainingAccuracy()) + "%"
bestPerceptron.test('a5a.train')
print

print "Training set classified with margin Perceptron:"
print "Accuracy: " + str(bestPerceptron.correctClassifications) + "/" + str(bestPerceptron.testSpace) + " or " + str(bestPerceptron.getTestAccuracy()) + "%"
print

bestPerceptron.test('a5a.test')
print "Test set classified with margin Perceptron:"
print "Accuracy: " + str(bestPerceptron.correctClassifications) + "/" + str(bestPerceptron.testSpace) + " or " + str(bestPerceptron.getTestAccuracy()) + "%"
print
