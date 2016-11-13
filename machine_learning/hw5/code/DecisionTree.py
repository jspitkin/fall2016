# Jake Pitkin November 13 2016
import math
from collections import Counter

class Edge:
    def __init__(self, feature_value):
        self.feature_value = feature_value

    def set_child(self, child_node):
        self.child_node = child_node

    def get_value(self):
        return self.feature_value

class Node:
    def __init__(self, value):
        self.value = value
        self.edges = []

    def add_edge(self, edge):
        self.edges.append(edge)

    def get_edges(self):
        return self.edges

    def get_value(self):
        return self.value

class DecisionTree:
    def __init__(self, feature_count):
        self.feature_count = feature_count

    def get_feature_columns(self, examples):
        if len(examples) < 1:
            return []
        feature_columns = [[] for i in range(feature_count)]
        labels = []
        for example in examples:
            for index, feature in enumerate(example['feature_vector'])
                feature_columns[index].append(feature)
            labels.append(example['label'])
        columns = {'feature_columns' : feature_columns, 'labels_column' : labels}
        return columns

    def entropy(self, vector):
        value_counts = {}
        for value in vector:
            if value in value_counts:
                value_counts[value] += 1
            else:
                value_counts[value] = 1
        entropy = 0
        for label, count in value_counts.iteritems():
            probability = count / len(vector)
            entropy = entropy - (probability * math.log(probability, 2))
        return entropy

    def information_gain(self, feature_vector, labels):
        feature_values_info = {}
        for index, feature_value in enumerate(feature_vector):
            if feature_value in feature_values_info:
                feature_values_info[feature_value]['count'] += 1
            else:
                feature_values_info[feature_value] = {}
                feature_values_info[feature_value]['count'] = 1
                feature_values_info[feature_value]['label_count'] = {}
                for label in labels:
                    feature_values_info[feature_value]['label_count'][label] = 0
            label_value = labels[index]
            feature_values_info[feature_value]['label_count'][label_value] += 1

        expected_entropy = 0
        for feature_value, feature_value_info in feature_values_info.iteritems():
            current_entropy = 0
            for count in feature_value_info['label_count'].values():
                if count != 0 and feature_value_info['count'] != 0:
                    probability = count / feature_value_info['count']
                    current_entropy = current_entropy - (probability * math.log(probability, 2))
            expected_entropy += (feature_value_info['count'] / len(feature_vector)) * current_entropy
        return self.entropy(labels) - expected_entropy

    def ID3(self, examples, attributes, labels, possible_attribute_values):
        # All examples have the same label - base case
        if all(label == labels[0] for label in labels):
            return Node(labels[0])
        # Find the attribute that best classifies the examples
        best_attribute = attributes[0]
        best_attribute_column = 0
        best_information_gain = self.information_gain(best_attribute, labels)
        for index, attribute in enumerate(attribute[1:]):
            information_gain = self.information_gain(attribute, labels)
            if information_gain > best_information_gain:
                best_attribute = attribute
                best_attribute_column = index + 1
                best_information_gain = information_gain
        # Create a root node with this attribute
        root = Node(best_attribute_column)
        # Find the most common attribute value for the best attribute
        most_common_label = Counter(labels).most_common()[0]
        # Add a new branch for each possible value the best attribute can take
        for attribute in possible_attribute_values[best_attribute_column]:
            new_edge = Edge(attribute)
            root.add_edge(new_edge)
            # If there are no examples of this attribute - common label
            if attribute not in best_attribute:
                new_leaf = Node(most_common_label[0])
                new_edge.set_child(new_leaf)
            # Otherwise, branch and add the subtree
            else:
                sub_examples = []
                for index, example in enumerate(examples):
                    if example['feature_vector'][best_attribute_column] == attribute:
                        sub_examples.append(example)
                sub_columns = self.get_feature_columns(sub_examples)
                new_edge.set_child(self.ID3(sub_examples, sub_attributes, sub_labels, possible_attribute_values)
        return root
