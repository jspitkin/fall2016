from random import randint

def read_data(features_path, labels_path, add_bias=True):
    examples = []
    with open(features_path) as file:
        for line in file:
            example = {}
            example['feature_vector'] = list(map(float, line.split()))
            if add_bias:
                example['feature_vector'].insert(0, 1)
            examples.append(example)
    with open(labels_path) as file:
        index = 0
        for line in file:
            examples[index]['label'] = int(line)
            index += 1
    return examples

def read_data_and_split(examples, split_count):
    splits = [[] for _ in range(split_count)]
    example_index = 0
    for example in examples:
        splits[example_index % split_count].append(example)
        example_index += 1
    return splits

def split_data(feature_path, label_path, feature_count):
    feature_values = [[] for x in range(feature_count)]
    labels = []
    feature_splits = [0 for x in range(feature_count)]
    example_count = 0
    with open(feature_path) as file:
        for line in file:
            example_count += 1
            features = line.split()
            features.insert(0, 1)
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
    examples = read_data(feature_path, label_path)
    for example in examples:
        for index in range(1, feature_count):
            if example['feature_vector'][index] < feature_splits[index]:
                example['feature_vector'][index] = 0
            else:
                example['feature_vector'][index] = 1
    return examples

def get_m_random_examples(examples, m):
    m_examples = []
    for index in range(m):
        random_index = randint(0, len(examples)-1)
        m_examples.append(examples[random_index])
    return m_examples
