import decisionTree as dt
import sys

def main():
    'Program entry point.'
    if len(sys.argv) != 3:
        printUsage()
        return -1

    trainingDataFilePath = sys.argv[1]
    testDataFilePath = sys.argv[2]

    decisionTree = dt.DecisionTree()
    trainingData = decisionTree.readDataFile(trainingDataFilePath)
    testData = decisionTree.readDataFile(testDataFilePath)

    featureVectors, labels = decisionTree.getFeatureVectors(trainingData)

    for vector in featureVectors:
      print decisionTree.informationGain(vector, labels)

def printUsage():
    print "usage: python testA1.py <training data> <test data>"

if __name__ == "__main__":
    main()
