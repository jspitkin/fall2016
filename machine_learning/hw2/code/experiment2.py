from __future__ import division
import perceptron as pt

print "# QUESTION TWO #####"
print "Classic Perceptron algorithm ----------------------------------------"

print "Training on 'a5a.train' with a learning rate of 0.5 and a epoch of 1"
bestPerceptron = pt.Perceptron(0.5, 1)
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
print "Training on 'a5a.train' with a learning rate of 0.5, a margin of 0 and a epoch of 1"
bestPerceptron = pt.Perceptron(0.5, 1)
bestPerceptron.margin = 0
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
