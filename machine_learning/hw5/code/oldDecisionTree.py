import math
from collections import Counter

class Example:
    def __init__(self):
        'An example - including it\'s features and label'
        self.features = []
        self.label = ""

    def add_feature(self, feature):
        self.features.append(feature)

    def update_label(self, label):
        self.label = label

class Edge:
    def __init__(self, feature_value):
        'An edge of a decision tree. has a feature value and a child node'
        self.feature_value = feature_value

    def set_child(self, child_node):
        self.child_node = child_node

    def get_value(self):
        return self.feature_value

class Node:
    def __init__(self, value):
        'A node of a decision tree. Knows its children nodes and the label of'
        'the edge between this node and it\'s parent'
        self.value = value
        self.edges = []

    def add_edge(self, edge):
        self.edges.append(edge)

    def get_edges(self):
        return self.edges

    def get_value(self):
        return self.value

class DecisionTree:
    'A decision tree - used for the ID3 algorithm'
    def get_feature_vectors(self, examples):
        'Returns a list of the feature vectors'
        if len(examples) < 1:
            return []

        feature_vectors= [[] for i in range(len(examples[0].features))]
        labels = []
        for example in examples:
            for index, feature in enumerate(example.features):
                feature_vectors[index].append(feature)
            labels.append(example.label)
        return (feature_vectors, labels)

    def entropy(self, vector):
        'Returns the entropy of a vector'
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


    def informationGain(self, feature_vector, labels):
        'Returns the informationGain of a feature vector'
        'Dictionary of lists. Key: featureValue. Value: [0] count of feature value [1] dictionary of label count'
        feature_values_info = {}
        for index, feature_value in enumerate(feature_vector):
            if feature_value in feature_values_info:
                feature_values_info[feature_value][0] += 1
            else:
                feature_values_info[feature_value] = []
                feature_values_info[feature_value].append(1)
                feature_values_info[feature_value].append({})
                for label in labels:
                    feature_values_info[feature_value][1][label] = 0
            label_value = labels[index] 
            feature_values_info[feature_value][1][label_value] += 1 

        expected_entropy = 0
        for feature_value, feature_value_info in feature_values_info.iteritems():
            current_entropy = 0
            for count in feature_value_info[1].values():
                if count != 0 and feature_value_info[0] != 0:
                    probability = count / feature_value_info[0]
                    current_entropy = current_entropy - (probability * math.log(probability, 2))
            expected_entropy += (feature_value_info[0]/len(feature_vector)) * current_entropy

        return self.entropy(labels) - expected_entropy

     def read_possible_feature_values(self, path):
        'Reads in the possible values for a feature'
        feature_values = []
        with open(path) as file:
            for line in file:
                line = line.strip().split(',')
                feature_values.append(line)
        return feature_values


    def ID3(self, examples, attributes, labels, possible_attribute_values, cur_depth):
        # Check if max depth has been reached, if so take the most common value
        if cur_depth + 1 > self.max_depth:
            most_common_label = Counter(labels).most_common()[0]
            return Node(most_common_label[0])
         
        # All examples have the same label
        if all(label == labels[0] for label in labels):
            return Node(labels[0])
        # Find the attribute that best classifes the examples
        best_attribute = attributes[0]
        best_attribute_column = 0
        best_information_gain = self.information_gain(best_attribute, labels)
        for index, attribute in enumerate(attributes[1:]):
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
            # If there are no examples of this attribute, give it the most common label
            if attribute not in best_attribute:
                new_leaf = Node(most_common_label[0])
                new_edge.set_child(new_leaf)
            # Otherwise, branch and add the subtree
            else:
                sub_examples = []
                for index, example in enumerate(examples):
                    if example.features[best_attribute_column] == attribute:
                        sub_examples.append(example)
                sub_attributes, sub_labels= self.get_feature_vectors(sub_examples)
                new_edge.set_child(self.ID3(sub_examples, sub_attributes, sub_labels, possible_attribute_values, cur_depth + 1))
        return root

    def construct_tree(self, training_data_file_paths, possible_feature_values_file_path):
        self.possible_attribute_values = self.read_possible_feature_values(possible_feature_values_file_path)
        training_data = self.read_data_file(training_data_file_paths)
        feature_vectors, labels = self.get_feature_vectors(training_data)
        self.root = self.ID3(training_data, feature_vectors, labels, self.possible_attribute_values, 0)

    def report_error(self, examples_filePath):
        examples = self.read_data_file(examples_file_path)
        feature_vectors, labels = self.get_feature_vectors(examples)
        correct_predictions = 0 
        max_depth = 0
        for example in examples:
            current_node = self.root
            current_depth = 0
            while len(current_node.edges) > 0:
                example_attribute_value = example.features[current_node.value]
                for edge in current_node.edges:
                    if example_attribute_value == edge.get_value():
                        current_node = edge.child_node
                        current_depth = current_depth + 1
                        if current_depth > max_depth:
                            max_depth = current_depth
                            current_depth = 0
            current_depth = 0
            if current_node.value == example.label:
                correct_predictions = correct_predictions + 1
        return ((correct_predictions / len(examples)), max_depth, correct_predictions, (len(examples) - correct_predictions), len(examples))
