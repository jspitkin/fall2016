def read_file(path, feature_count):
    'Takes in a file path and returns a list of examples.'
    examples = []
    with open(path) as file:
        for line in file:
            line = line.strip().split(' ')
            feature_vector = [0 for x in range(feature_count)]
            label = int(line[0])
            for feature in line[1:]:
                colon_index = feature.index(':')
                index = int(feature[:colon_index])
                value = int(feature[colon_index + 1:])
                feature_vector[index] = value
            examples.append({'label' : label, 'feature_vector' : feature_vector})
    print(examples)
    return examples