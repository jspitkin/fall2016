import decisionTree as dt
import sys

def main():
    'Program entry point.'
    if len(sys.argv) != 4:
        printUsage()
        return -1

    trainingDataFilePath = sys.argv[1]
    testDataFilePath = sys.argv[2]
    possibleFeatureValuesFilePath = sys.argv[3]
    print "# Setting A #####"
    print "Question One"
    decisionTree = dt.DecisionTree()
    decisionTree.constructTree([trainingDataFilePath], possibleFeatureValuesFilePath)
    error = decisionTree.reportError([trainingDataFilePath])
    print "(b) Error on training set: " + str(1 - error[0])
    print "(b) " + str(error[2]) + "/" + str(error[4])
    decisionTree = dt.DecisionTree()
    decisionTree.constructTree([trainingDataFilePath], possibleFeatureValuesFilePath)
    error = decisionTree.reportError([testDataFilePath])
    print "(c) Error on test set: " + str(1 - error[0])
    print "(c) " + str(error[2]) + "/" + str(error[4])
    decisionTree = dt.DecisionTree()
    decisionTree.constructTree([trainingDataFilePath], possibleFeatureValuesFilePath)
    depth = decisionTree.reportError([trainingDataFilePath])
    print "(d) Depth of the decision tree: " + str(depth[1])

def printUsage():
    print "usage: python settingA.py <training data> <test data> <possible features>"

if __name__ == "__main__":
    main()
