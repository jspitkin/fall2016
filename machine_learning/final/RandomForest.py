import math
from random import shuffle
import DecisionTree as dt

class RandomForest:
    def __init__(self, m, N, feature_count):
        self.m = m
        self.N = N
        self.feature_count = feature_count
        self.k = int(math.log(self.feature_count, 2))
        self.k = 11

    def set_data_paths(self, train_data, train_labels, test_data, test_labels):
        self.train_data = train_data
        self.train_labels = train_labels
        self.test_data = test_data
        self.test_labels = test_labels

    def select_k_random_feature_indices(self):
        possible_feature_indices = []
        for index in range(self.feature_count):
            possible_feature_indices.append(index)
        shuffle(possible_feature_indices)
        random_indices = []
        for index in range(self.k):
            random_indices.append(possible_feature_indices[index])
        return random_indices

    def set_examples(self, examples, test_examples):
        self.examples = examples
        self.test_examples = test_examples

    def create_trees(self):
        results = []
        for _ in range(self.N):
            random_indices = self.select_k_random_feature_indices()
            tree = dt.DecisionTree(self.feature_count, random_indices, self.m)
            tree.construct_tree(self.examples)
            result = tree.test(self.test_examples)
            results.append(result)
        return results

    def get_transformed_examples(self, results, label_path):
        transformed_examples = []
        with open(label_path) as file:
            for line in file:
                example = {}
                example['label'] = int(line)
                example['feature_vector'] = [1]
                transformed_examples.append(example)
        for prediction_index in range(self.N):
            current_prediction_vector = results[prediction_index]['predictions']
            for feature_index in range(len(transformed_examples)):
                transformed_examples[feature_index]['feature_vector'].append(current_prediction_vector[feature_index])
        return transformed_examples
