from __future__ import division
import math

class Example:
    def __init__(self):
        'An example - including it\'s features and label'
        self.features = []
        self.label = ""

    def addFeature(self, feature):
        self.features.append(feature)

    def updateLabel(self, label):
        self.label = label

class Node:
    def __init__(self, value, edgeInFeatureChoice=None):
        'A node of a decision tree. Knows its children nodes and the label of'
        'the edge between this node and it\'s parent'
        self.value = value
        self.edgeInFeatureChoice = edgeInFeatureChoice
        self.children = []

    def addChild(self, childNode):
        self.children.append(childNode)

    def getChildren(self):
        return self.children

    def getEdgeInFeatureChoice(self):
        return self.edgeInFeatureChoice

    def getValue(self):
        return self.value

class DecisionTree:
    def __init__(self):
        'A decision tree - used for the ID3 algorithm'
        self.root = None
        self.nodes = []

    def getFeatureVectors(self, examples):
        'Returns a list of the feature vectors'
        if len(examples) < 1:
            return []

        featureVectors= [[] for i in range(len(examples[0].features))]
        labels = []
        for example in examples:
            for index, feature in enumerate(example.features):
                featureVectors[index].append(feature)
            labels.append(example.label)
        return (featureVectors, labels)

    def entropy(self, vector):
        'Returns the entropy of a vector'
        valueCounts = {}
        for value in vector:
            if value in valueCounts:
                valueCounts[value] += 1
            else:
                valueCounts[value] = 1
        entropy = 0
        for label, count in valueCounts.iteritems():
            probability = count / len(vector)
            entropy = entropy - (probability * math.log(probability, 2))
        return entropy


    def informationGain(self, featureVector, labels):
        'Returns the informationGain of a feature vector'
        'Dictionary of lists. Key: featureValue. Value: [0] count of feature value [1] dictionary of label count'
        featureValuesInfo = {}
        for index, featureValue in enumerate(featureVector):
            if featureValue in featureValuesInfo:
                featureValuesInfo[featureValue][0] += 1
            else:
                featureValuesInfo[featureValue] = []
                featureValuesInfo[featureValue].append(1)
                featureValuesInfo[featureValue].append({})
                for label in labels:
                    featureValuesInfo[featureValue][1][label] = 0
            labelValue = labels[index] 
            featureValuesInfo[featureValue][1][labelValue] += 1 

        expectedEntropy = 0
        for featureValue, featureValueInfo in featureValuesInfo.iteritems():
            currentEntropy = 0
            for count in featureValueInfo[1].values():
                if count != 0 and featureValueInfo[0] != 0:
                    probability = count / featureValueInfo[0]
                    currentEntropy = currentEntropy - (probability * math.log(probability, 2))
            expectedEntropy += (featureValueInfo[0]/len(featureVector)) * currentEntropy

        return self.entropy(labels) - expectedEntropy

    def readDataFile(self, path):
        'Reads in a data file separated by commas and returns a list of Examples'
        examples = []
        with open(path) as file:
            for line in file:
                line = line.strip().split(',')
                example = Example()
                for feature in line[:-1]:
                    example.addFeature(feature)
                if len(line) >= 1:
                    example.updateLabel(line[-1])
                examples.append(example)
            return examples
