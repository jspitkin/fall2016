def read_file(path, feature_count):
    'Takes in a file path and returns a list of examples.'
    examples = []
    with open(path) as file:
        for line in file:
            line = line.strip().split(' ')
            feature_vector = [0 for x in range(feature_count)]
            feature_vector[0] = 1
            label = int(line[0])
            for feature in line[1:]:
                colon_index = feature.index(':')
                index = int(feature[:colon_index])
                value = int(feature[colon_index + 1:])
                feature_vector[index] = value
            examples.append({'label' : label, 'feature_vector' : feature_vector})
    return examples

def write_csv(path, likelihoods):
    file = open(path, 'w')
    index = 1
    for likelihood in likelihoods:
        entry = str(index) + ',' + str(likelihood) + '\n'
        file.write(entry)
        index += 1
    file.close()
