import decisionTree as dt
import sys

def main():
    'Program entry point.'
    if len(sys.argv) != 6:
        printUsage()
        return -1

    trainingDataFilePathB = sys.argv[1]
    testDataFilePathB = sys.argv[2]
    trainingDataFilePathA = sys.argv[3]
    testDataFilePathA = sys.argv[4]
    possibleFeatureValuesFilePath = sys.argv[5]
    print "# Setting B #####"
    print "Question One"
    decisionTree = dt.DecisionTree()
    decisionTree.constructTree([trainingDataFilePathB], possibleFeatureValuesFilePath)
    error = decisionTree.reportError([trainingDataFilePathB])
    print "(a) Error on training set B: " + str(1 - error[0])
    print "(a) " + str(error[2]) + "/" + str(error[4])
    decisionTree = dt.DecisionTree()
    decisionTree.constructTree([trainingDataFilePathB], possibleFeatureValuesFilePath)
    error = decisionTree.reportError([testDataFilePathB])
    print "(b) Error on test set B: " + str(1 - error[0])
    print "(b) " + str(error[2]) + "/" + str(error[4])
    decisionTree = dt.DecisionTree()
    decisionTree.constructTree([trainingDataFilePathB], possibleFeatureValuesFilePath)
    error = decisionTree.reportError([trainingDataFilePathA])
    print "(c) Error on training set A: " + str(1 - error[0])
    print "(c) " + str(error[2]) + "/" + str(error[4])
    decisionTree = dt.DecisionTree()
    decisionTree.constructTree([trainingDataFilePathB], possibleFeatureValuesFilePath)
    error = decisionTree.reportError([testDataFilePathA])
    print "(d) Error on test set A: " + str(1 - error[0])
    print "(d) " + str(error[2]) + "/" + str(error[4])
    decisionTree = dt.DecisionTree()
    decisionTree.constructTree([trainingDataFilePathB], possibleFeatureValuesFilePath)
    depth = decisionTree.reportError([trainingDataFilePathB])
    print "(e) Depth of the decision tree: " + str(error[1])

def printUsage():
    print "usage: python settingA.py <training data> <test data> <possible features>"

if __name__ == "__main__":
    main()
