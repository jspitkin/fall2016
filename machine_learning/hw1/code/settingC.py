import decisionTree as dt
import sys
import numpy as np

def main():
    'Program entry point.'
    if len(sys.argv) != 8:
        printUsage()
        return -1

    splitOneFilePath = sys.argv[1]
    splitTwoFilePath = sys.argv[2]
    splitThreeFilePath = sys.argv[3]
    splitFourFilePath = sys.argv[4]
    splitFiveFilePath = sys.argv[5]
    splitSixFilePath = sys.argv[6]
    possibleFeatureValuesFilePath = sys.argv[7]

    splitOne = []
    splitOne.append(splitOneFilePath)
    splitOne.append(splitTwoFilePath)
    splitOne.append(splitThreeFilePath)
    splitOne.append(splitFourFilePath)
    splitOne.append(splitFiveFilePath)
    splitOne.append(splitSixFilePath)
    splitTwo = list(splitOne)
    splitThree = list(splitOne)
    splitFour = list(splitOne)
    splitFive = list(splitOne)
    splitSix = list(splitOne)
    splitOne.remove(splitOneFilePath)
    splitTwo.remove(splitTwoFilePath)
    splitThree.remove(splitThreeFilePath)
    splitFour.remove(splitFourFilePath)
    splitFive.remove(splitFiveFilePath)
    splitSix.remove(splitSixFilePath)

    print "Question Three"
    replaceMethods = [1, 2, 3]
    error = []
    for i in range(len(replaceMethods)):
        decisionTree = dt.DecisionTree(99999, replaceMethods[i])
        decisionTree.constructTree(splitOne, possibleFeatureValuesFilePath)
        error.append(decisionTree.reportError([splitOneFilePath])[0])
        decisionTree = dt.DecisionTree(99999, replaceMethods[i])
        decisionTree.constructTree(splitTwo, possibleFeatureValuesFilePath)
        error.append(decisionTree.reportError([splitTwoFilePath])[0])
        decisionTree = dt.DecisionTree(9999999,replaceMethods[i])
        decisionTree.constructTree(splitThree, possibleFeatureValuesFilePath)
        error.append(decisionTree.reportError([splitThreeFilePath])[0])
        decisionTree = dt.DecisionTree(999999, replaceMethods[i])
        decisionTree.constructTree(splitFour, possibleFeatureValuesFilePath)
        error.append(decisionTree.reportError([splitFourFilePath])[0])
        decisionTree = dt.DecisionTree(999999,replaceMethods[i])
        decisionTree.constructTree(splitFive, possibleFeatureValuesFilePath)
        error.append(decisionTree.reportError([splitFiveFilePath])[0])
        decisionTree = dt.DecisionTree(999999, replaceMethods[i])
        decisionTree.constructTree(splitSix, possibleFeatureValuesFilePath)
        error.append(decisionTree.reportError([splitSixFilePath])[0])
        a = np.array(error)
        errorSum = 0
        for e in error:
            errorSum = errorSum + e
        overall = errorSum / 6
        error = []
        print "Tree Size: " + str(replaceMethods[i]) + " Error: " + str(overall) + " St. Dev: " + str(np.std(a))

    decisionTree = dt.DecisionTree(1)
    decisionTree.constructTree(["SettingC/training.data"], possibleFeatureValuesFilePath)
    error = decisionTree.reportError(["SettingC/test.data"])
    print "Error of tree with best cross-validation accuracy: " + str(1 - error[0])
    print str(error[2]) + "/" + str(error[4])
        

def printUsage():
    print "usage: python settingA.py <training data> <test data> <possible features>"

if __name__ == "__main__":
    main()
