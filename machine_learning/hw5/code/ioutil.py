def read_data(features_path, labels_path, add_bias=True):
    examples = []
    with open(features_path) as file:
        for line in file:
            example = {}
            example['feature_vector'] = list(map(float, line.split()))
            if add_bias:
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

def read_possible_feature_values(train_path, test_path, feature_count):
    feature_values = [set() for x in range(feature_count)]
    with open(train_path) as file:
        for line in file:
            features = line.split()
            for index, feature in enumerate(features):
                feature_values[index].add(float(feature))
    with open(test_path) as file:
        for line in file:
            features = line.split()
            for index, feature in enumerate(features):
                feature_values[index].add(float(feature))
    return feature_values

def split_data(feature_path, label_path, feature_count):
    feature_values = [[] for x in range(feature_count)]
    labels = []
    feature_splits = [0 for x in range(feature_count)]
    example_count = 0
    with open(feature_path) as file:
        for line in file:
            example_count += 1
            features = line.split()
            for index, feature in enumerate(features):
                feature_values[index].append(float(feature))
    with open(label_path) as file:
        for line in file:
            labels.append(float(line))
    for feature_index in range(feature_count):
        current_feature = feature_values[feature_index]
        for index, feature in enumerate(current_feature):
            feature_splits[feature_index] += feature
    for index in range(feature_count):
        feature_splits[index] = feature_splits[index] / example_count
    examples = read_data(feature_path, label_path, False)
    for example in examples:
        for index in range(feature_count):
            if example['feature_vector'][index] < feature_splits[index]:
                example['feature_vector'][index] = 0
            else:
                example['feature_vector'][index] = 1
    return examples
