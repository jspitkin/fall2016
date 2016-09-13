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
    decisionTree = dt.DecisionTree()
    decisionTree.constructTree(trainingDataFilePath, possibleFeatureValuesFilePath)
    error = decisionTree.reportError(trainingDataFilePath)
    print ""
    print "(b) Success on training set: " + str(error)
    print ""
    decisionTree = dt.DecisionTree()
    decisionTree.constructTree(trainingDataFilePath, possibleFeatureValuesFilePath)
    error = decisionTree.reportError(testDataFilePath)
    print ""
    print "(c) Success on training set: " + str(error)
def printUsage():
    print "usage: python settingA.py <training data> <test data> <possible features>"

if __name__ == "__main__":
    main()
