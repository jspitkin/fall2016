import DecisionTree as dt
import ioutil

#tree = dt.DecisionTree(256)
#tree.construct_tree('handwriting/train.data', 'handwriting/train.labels')
#result = tree.test('handwriting/test.data', 'handwriting/test.labels')
#print(result)

tree = dt.DecisionTree(500, [1, 2, 4, 5, 9, 10])
tree.construct_tree('madelon/madelon_train.data', 'madelon/madelon_train.labels', 'madelon/madelon_test.data')
result = tree.test('madelon/madelon_test.data', 'madelon/madelon_test.labels')
print(result)
