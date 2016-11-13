def read_data(features_path, labels_path):
    examples = []
    with open(features_path) as file:
        for line in file:
            example = {}
            example['feature_vector'] = list(map(float, line.split()))
            example['feature_vector'].insert(0, 1.0)
            examples.append(example)
    with open(labels_path) as file:
        index = 0
        for line in file:
            examples[index]['label'] = int(line)
            index += 1
    return examples

def read_data_and_split(features_path, labels_path, split_count):
    splits = [[] for _ in range(split_count)]
    line_number = 0
    with open(features_path) as file:
        for line in file:
            example = {}
            example['feature_vector'] = list(map(float, line.split()))
            example['feature_vector'].insert(0, 1.0)
            splits[line_number % split_count].append(example)
            line_number += 1
    line_number = 0
    with open(labels_path) as file:
        for line in file:
            example_index = line_number // split_count
            splits[line_number % split_count][example_index]['label'] = int(line) 
            line_number += 1
    return splits

def read_possible_feature_values(path, feature_count):
    feature_values = [set() for x in range(feature_count)]
    with open(path) as file:
        for line in file:
            features = line.split()
            for index, feature in enumerate(features):
                feature_values[index].add(feature)
    return feature_values
            
