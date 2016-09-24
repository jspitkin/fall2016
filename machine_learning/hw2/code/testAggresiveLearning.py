import perceptron as pt

perceptron = pt.Perceptron(0.5, 1)
perceptron.margin = 1
perceptron.aggressiveMarginTrain('table2')
