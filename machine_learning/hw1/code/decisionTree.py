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

    def readDataFile(self, path):
        'Reads in a data file separated by commas and returns a matrix'
        matrixInitialized = False
        with open(path) as file:
            for line in file:
                if matrixInitialized is False:
                    matrix = [[] for i in range(len(line)/2)]
                    matrixInitialized = True
                lineData = line.strip().split(",")
                matrix[0][0].append("hi")
                print matrix[0][0]
