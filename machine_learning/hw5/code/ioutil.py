def read_semelion_data(features_path, labels_path):
    examples = []
    with open(features_path) as file:
        for line in file:
            example = {}
            example['feature_vector'] = list(map(float, line.split()))
            example['feature_vector'].insert(0, 1.0)
            example['feature_vector']
            examples.append(example)
    with open(labels_path) as file:
        index = 0
        for line in file:
            examples[index]['label'] = int(line)
            index += 1
    return examples
