from __future__ import division
import perceptron as pt

print "# QUESTION THREE #####"
print "Classic Perceptron algorithm ----------------------------------------"

print "Training on 'a5a.train' with a learning rate of 0.25 and a epoch of 3"
bestPerceptron = pt.Perceptron(0.25, 3)
bestPerceptron.classicTrain('a5a.train')
print "Updates made: " + str(bestPerceptron.mistakes) + "/" + str(bestPerceptron.trainingSpace * 3) + " or " + str(bestPerceptron.getTrainingAccuracy()) + "%"
print

bestPerceptron.test('a5a.train')
print "Training set classified with classic Perceptron:"
print "Accuracy: " + str(bestPerceptron.correctClassifications) + "/" + str(bestPerceptron.testSpace) + " or " + str(bestPerceptron.getTestAccuracy()) + "%"
print

bestPerceptron.test('a5a.test')
print "Test set classified with classic Perceptron:"
print "Accuracy: " + str(bestPerceptron.correctClassifications) + "/" + str(bestPerceptron.testSpace) + " or " + str(bestPerceptron.getTestAccuracy()) + "%"
print

print "Training on 'a5a.train' with a learning rate of 0.25 and a epoch of 5"
bestPerceptron = pt.Perceptron(0.25, 5)
bestPerceptron.classicTrain('a5a.train')
print "Updates made: " + str(bestPerceptron.mistakes) + "/" + str(bestPerceptron.trainingSpace * 5) + " or " + str(bestPerceptron.getTrainingAccuracy()) + "%"
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
print "Training on 'a5a.train' with a learning rate of 0.1, margin of 4, and a epoch of 3"

bestPerceptron = pt.Perceptron(0.1, 3)
bestPerceptron.margin = 4
bestPerceptron.marginTrain('a5a.train')
print "Updates made: " + str(bestPerceptron.mistakes) + "/" + str(bestPerceptron.trainingSpace * 3) + " or " + str(bestPerceptron.getTrainingAccuracy()) + "%"
print

bestPerceptron.test('a5a.train')
print "Training set classified with margin Perceptron:"
print "Accuracy: " + str(bestPerceptron.correctClassifications) + "/" + str(bestPerceptron.testSpace) + " or " + str(bestPerceptron.getTestAccuracy()) + "%"
print

bestPerceptron.test('a5a.test')
print "Test set classified with margin Perceptron:"
print "Accuracy: " + str(bestPerceptron.correctClassifications) + "/" + str(bestPerceptron.testSpace) + " or " + str(bestPerceptron.getTestAccuracy()) + "%"
print
print "Training on 'a5a.train' with a learning rate of 0.1, margin of 4, and a epoch of 5"

bestPerceptron = pt.Perceptron(0.1, 5)
bestPerceptron.margin = 4
bestPerceptron.marginTrain('a5a.train')
print "Updates made: " + str(bestPerceptron.mistakes) + "/" + str(bestPerceptron.trainingSpace * 5) + " or " + str(bestPerceptron.getTrainingAccuracy()) + "%"
print

bestPerceptron.test('a5a.train')
print "Training set classified with margin Perceptron:"
print "Accuracy: " + str(bestPerceptron.correctClassifications) + "/" + str(bestPerceptron.testSpace) + " or " + str(bestPerceptron.getTestAccuracy()) + "%"
print

bestPerceptron.test('a5a.test')
print "Test set classified with margin Perceptron:"
print "Accuracy: " + str(bestPerceptron.correctClassifications) + "/" + str(bestPerceptron.testSpace) + " or " + str(bestPerceptron.getTestAccuracy()) + "%"
