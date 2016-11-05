# Jake Pitkin November 5 2016
import sys

def get_feature_count(path):
    """ Input  - path: source of data file in lib-SVM format.
        Output - the number of features the data contains. """
    with open(path) as file:
        largest_feature_found = 0
        for line in file:
            for entry in line.split():
                entry = entry.split(":")
                if len(entry) == 2:
                    if int(entry[0]) > largest_feature_found:
                        largest_feature_found = int(entry[0])
    return largest_feature_found

def read_svm_file(path):
    """ Input  - path: source of data file in lib-SVM format.
        Output - a list of examples where each example is a tuple
                 of the form (label, feature vector). """
    examples = []
    feature_count = get_feature_count(path)
    with open(path) as file:
        for line in file:
            feature_vector = [0 for x in range(feature_count)]
            line = line.split()
            if len(line) > 0:
                label = int(line[0])
                for feature in line[1:]:
                    feature = feature.split(':')
                    if len(feature) == 2:
                        feature_vector[int(feature[0])-1] = int(feature[1])
            examples.append((label, feature_vector))
    return examples

def write_csv_file(path, labels, data):
    """ Input - path: write destination for csv file.
                labels: a list of the labels for each column of data.
                data: a list of rows of data.
        Writes a CSV file to 'path'. """
    with open(path, 'w') as file:
        labels = ",".join(labels)
        file.write(labels + '\n')
        for row in data[:-1]:
            row = ",".join(row)
            file.write(row + '\n')
        row = ",".join(data[-1])
        file.write(row)
