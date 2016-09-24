from __future__ import division
from random import randint

class Perceptron:
    def __init__(self, learningRate, randomInit=True):
        self.FEATURE_COUNT = 124
        if randomInit:
            self.bias = randint(-10,10)
            self.weightVector = [randint(-10,10) for x in range(self.FEATURE_COUNT)]
        else:
            self.weightVector = [0 for x in range(self.FEATURE_COUNT)]
            self.bias = 0
        self.learningRate = learningRate
        self.mistakes = 0
        self.correctClassifications = 0
        self.trainingSpace = 0
        self.testSpace = 0
        self.margin = 0

    def readFile(self, path):
        examples = []
        with open(path) as file:
            for line in file:
                line = line.strip().split(' ')
                featureVector = [0 for x in range(self.FEATURE_COUNT)]
                label = int(line[0])
                for feature in line[1:]:
                    colonIndex = feature.index(':')
                    index = int(feature[:colonIndex])
                    value = int(feature[colonIndex + 1:])
                    featureVector[index] = value
                examples.append((label, featureVector))
        return examples

    def sign(self, value):
        if value >= 0:
            return 1
        else:
            return -1

    def getTestAccuracy(self):
        if self.testSpace == 0:
            return 0
        return round(((self.correctClassifications/self.testSpace)*100),2)

    def test(self, path):
        self.correctClassifications = 0
        examples = self.readFile(path)
        self.testSpace = len(examples)
        for example in examples:
            label, featureVector = example
            vectorSum = self.bias
            for index in range(self.FEATURE_COUNT):
                vectorSum += self.weightVector[index] * featureVector[index]
            if self.sign(vectorSum) == label:
                self.correctClassifications += 1

    def marginTrain(self, path):
        self.mistakes = 0
        examples = self.readFile(path)
        self.trainingSpace = len(examples)
        for example in examples:
            label, featureVector = example
            vectorSum = self.bias
            for index in range(self.FEATURE_COUNT):
                vectorSum += self.weightVector[index] * featureVector[index]
            if label * (vectorSum + self.bias) < self.margin:
                self.mistakes += 1
                for index in range(self.FEATURE_COUNT):
                    self.weightVector[index] += self.learningRate * (label * featureVector[index])


    def classicTrain(self, path):
        self.mistakes = 0
        examples = self.readFile(path)
        self.trainingSpace = len(examples)
        for example in examples:
            label, featureVector = example
            vectorSum = self.bias
            for index in range(self.FEATURE_COUNT):
                vectorSum += self.weightVector[index] * featureVector[index]
            if self.sign(vectorSum) != label:
                self.mistakes += 1
                for index in range(self.FEATURE_COUNT):
                    self.weightVector[index] += self.learningRate * (label * featureVector[index])
