import ioutil
import math

class ExampleStats:
    def __init__(self):
        self.min_value_positive = 0
        self.max_value_positive = 0
        self.min_value_negative = 0
        self.max_value_negative = 0
        self.average_value = 0
        self.count = 0
        self.feature_number = 0
        self.positive_count = 0
        self.negative_count = 0

    def __str__(self):
        rep = "#: " + str(self.feature_number) + '\t'
        rep += " min: " + str(self.min_value) + '\t'
        rep += " max: " + str(self.max_value) + '\t'
        rep += " avg: " + str(self.average_value) + '\t'
        rep += " count: " + str(self.count) + '\n'
        rep += "pos min: " + str(self.min_value_positive) + " pos max: " + str(self.max_value_positive) + '\n'
        rep += "neg min: " + str(self.min_value_negative) + " neg max: " + str(self.max_value_negative) + '\n'
        return rep

def get_ranges_and_averages(examples):
    feature_count = 360
    min_values = [float("inf") for x in range(feature_count)]
    max_values = [float("-inf") for x in range(feature_count)]
    average_values = [0 for x in range(feature_count)]
    count = [0 for x in range(feature_count)]
    data = [None for x in range(feature_count)]
    positive_example_count = 0
    negative_example_count = 0
    
    for example in examples:
        if example['label'] == 1:
            positive_example_count += 1
        else:
            negative_example_count += 1
        for index, feature in enumerate(example['feature_vector']):
            if feature == 0:
                continue
            if data[index] is None:
                example_stats = ExampleStats()
                example_stats.feature_number = index
                example_stats.average_value = feature
                example_stats.count += 1
                if example['label'] == 1:
                    example_stats.min_value_positive = feature
                    example_stats.max_value_positive = feature
                else:
                    example_stats.min_value_negative = feature
                    example_stats.max_value_negative = feature
                example_stats.min_value = feature
                example_stats.max_value = feature
                data[index] = example_stats
            else:
                data[index].count += 1
                data[index].average_value += feature

            if example['label'] == 1:
                data[index].positive_count += 1
                if feature < data[index].min_value_positive:
                    data[index].min_value_positive = feature
                if feature > data[index].max_value_positive:
                    data[index].max_value_positive = feature
            else:
                data[index].negative_count += 1
                if feature < data[index].min_value_negative:
                    data[index].min_value_negative = feature
                if feature > data[index].max_value_negative:
                    data[index].max_value_negative = feature

    for example in data:
        if example is None:
            continue
        example.average_value = round(example.average_value / example.count, 2)
    print("positive:", positive_example_count, "negative:", negative_example_count, sep='\t')
    return data

def print_data(data):
    newData = []
    for d in data:
        if d is not None:
            newData.append(d)
    print("Present Features:", len(newData))
    newData.sort(key=lambda x: x.count, reverse=True)
    print("Ten Most Frequent Features:")
    for index in range(10):
        print(newData[index])

def generate_csv(examples):
    f = open('positive_examples.txt','w')
    for example in examples:
        for index in range(len(example['feature_vector'])):
            if index == 73 and example['label'] == -1:
                f.write(str(example['feature_vector'][index]) + '\n')

   
examples = ioutil.read_svm_file('data-splits/data.train')
generate_csv(examples)
data = get_ranges_and_averages(examples)
print_data(data)
