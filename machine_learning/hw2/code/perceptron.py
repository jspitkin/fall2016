from __future__ import division
import random

class Perceptron:
    def __init__(self, learningRate, epoch, randomInit=True):
        # There are 124 features in the test and training data.
        self.FEATURE_COUNT = 124
        random.seed(124)
        # Should the weight vector and bias be randomly initalizied.
        if randomInit:
            self.bias = random.uniform(-1,1)
            self.weightVector = [random.uniform(-1,1) for x in range(self.FEATURE_COUNT)]
        else:
            self.weightVector = [0 for x in range(self.FEATURE_COUNT)]
            self.bias = 0
        self.learningRate = learningRate
        self.mistakes = 0
        self.correctClassifications = 0
        self.trainingSpace = 0
        self.testSpace = 0
        self.margin = 0
        self.epoch = epoch
        self.shuffle = True

    def readFile(self, path):
        'Takes in a file path and returns a list of examples'
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
        'Returns 1 if value is positive and -1 otherwise.'
        if value >= 0:
            return 1
        else:
            return -1

    def getTestAccuracy(self):
        'Returns the percent of correctly classified examples when testing.'
        if self.testSpace == 0:
            return 0
        return round(((self.correctClassifications/self.testSpace)*100),2)

    def getTrainingAccuracy(self):
        'Returns the percent of updates when training.'
        if self.trainingSpace == 0:
            return 0
        return round(((self.mistakes/(self.trainingSpace * self.epoch))*100),2)

    def test(self, path):
        'Takes in a path and runs all the examples it contains through the current Perceptron.'
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
        'Trains a margin Perceptron. The weight vector is stored in self.weightVector.'
        self.mistakes = 0
        examples = self.readFile(path)
        self.trainingSpace = len(examples)
        for _ in range(self.epoch):
            for example in examples:
                label, featureVector = example
                vectorSum = self.bias
                for index in range(self.FEATURE_COUNT):
                    vectorSum += self.weightVector[index] * featureVector[index]
                if label * (vectorSum) < self.margin:
                    self.mistakes += 1
                    for index in range(self.FEATURE_COUNT):
                        self.weightVector[index] += self.learningRate * (label * featureVector[index])
            random.shuffle(examples)

    def getAggressiveLearningRate(self, label, vectorSum, featureVector):
        'Returns the learning rate for aggressive Perceptron with margin.'
        learningRate = self.margin - (label * vectorSum)
        denominator = 1
        for index in range(self.FEATURE_COUNT):
            denominator += featureVector[index] * featureVector[index]
        learningRate = learningRate / denominator
        return learningRate

    def aggressiveMarginTrain(self, path):
        'Trains a aggressive Perceptron with margin. The weight vector is stored in self.weightVector.'
        self.mistakes = 0
        examples = self.readFile(path)
        self.trainingSpace = len(examples)
        for _ in range(self.epoch):
            for example in examples:
                label, featureVector = example
                vectorSum = self.bias
                for index in range(self.FEATURE_COUNT):
                    vectorSum += self.weightVector[index] * featureVector[index]
                if label * (vectorSum) < self.margin:
                    self.mistakes += 1
                    aggressiveLearningRate = self.getAggressiveLearningRate(label, vectorSum, featureVector)
                    for index in range(self.FEATURE_COUNT):
                        self.weightVector[index] += aggressiveLearningRate * (label * featureVector[index])
            if self.shuffle:
                random.shuffle(examples)
   

    def classicTrain(self, path):
        'Trains a classic Perceptron. The weight vector is stored in self.weightVector.'
        self.mistakes = 0
        examples = self.readFile(path)
        self.trainingSpace = len(examples)
        for _ in range(self.epoch):
            for example in examples:
                label, featureVector = example
                vectorSum = self.bias
                for index in range(self.FEATURE_COUNT):
                    vectorSum += self.weightVector[index] * featureVector[index]
                if (label * vectorSum) <= 0:
                    self.mistakes += 1
                    for index in range(self.FEATURE_COUNT):
                        self.weightVector[index] += self.learningRate * (label * featureVector[index])
            random.shuffle(examples)
