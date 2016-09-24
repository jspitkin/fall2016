from __future__ import division
import perceptron as pt

print "# QUESTION FOUR  #####"
print "Aggresive Perceptron with Margin algorithm ----------------------------------------"
print "Training on 'a5a.train' with a margin of 0, a epoch of 3, and shuffling"

bestPerceptron = pt.Perceptron(0.5, 3)
bestPerceptron.margin = 0
bestPerceptron.marginTrain('a5a.train')
print "Updates made: " + str(bestPerceptron.mistakes) + "/" + str(bestPerceptron.trainingSpace * 3) + " or " + str(bestPerceptron.getTrainingAccuracy()) + "%"
print

bestPerceptron.test('a5a.train')
print "Training set classified with Aggresive Perceptron with Margin:"
print "Accuracy: " + str(bestPerceptron.correctClassifications) + "/" + str(bestPerceptron.testSpace) + " or " + str(bestPerceptron.getTestAccuracy()) + "%"
print

bestPerceptron.test('a5a.test')
print "Test set classified with margin Perceptron:"
print "Accuracy: " + str(bestPerceptron.correctClassifications) + "/" + str(bestPerceptron.testSpace) + " or " + str(bestPerceptron.getTestAccuracy()) + "%"
print
print "Training on 'a5a.train' with a margin of 0, a epoch of 5, and shuffling"

bestPerceptron = pt.Perceptron(0.5, 5)
bestPerceptron.margin = 0
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
print

print "Training on 'a5a.train' with a margin of 0, a epoch of 3, and no shuffling"
bestPerceptron = pt.Perceptron(0.5, 3)
bestPerceptron.margin = 0
bestPerceptron.shuffling = False    
bestPerceptron.marginTrain('a5a.train')
print "Updates made: " + str(bestPerceptron.mistakes) + "/" + str(bestPerceptron.trainingSpace * 3) + " or " + str(bestPerceptron.getTrainingAccuracy()) + "%"
print

bestPerceptron.test('a5a.train')
print "Training set classified with Aggresive Perceptron with Margin:"
print "Accuracy: " + str(bestPerceptron.correctClassifications) + "/" + str(bestPerceptron.testSpace) + " or " + str(bestPerceptron.getTestAccuracy()) + "%"
print

bestPerceptron.test('a5a.test')
print "Test set classified with margin Perceptron:"
print "Accuracy: " + str(bestPerceptron.correctClassifications) + "/" + str(bestPerceptron.testSpace) + " or " + str(bestPerceptron.getTestAccuracy()) + "%"
print

print "Training on 'a5a.train' with a margin of 0, a epoch of 5, and no shuffling"
bestPerceptron = pt.Perceptron(0.5, 5)
bestPerceptron.margin = 0
bestPerceptron.shuffling = False
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
