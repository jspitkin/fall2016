import math
from random import shuffle
import DecisionTree as dt

class RandomForest:
    def __init__(self, m, N, feature_count):
        self.m = m
        self.N = N
        self.feature_count = feature_count
        self.k = math.log(self.feature_count, 2)

    def get_m_random_examples(self, examples):
        shuffle(examples)
        m_examples = []
        for index in range(self.m):
            m_examples.append(examples[index])
        return examples


    
