import ioutil
import math

class ExampleStats:
    def __init__(self):
        self.min_value = 0
        self.max_value = 0
        self.average_value = 0
        self.count = 0
        self.feature_number = 0

    def __str__(self):
        rep = "#: " + str(self.feature_number) + '\t'
        rep += " min: " + str(self.min_value) + '\t'
        rep += " max: " + str(self.max_value) + '\t'
        rep += " avg: " + str(self.average_value) + '\t'
        rep += " count: " + str(self.count)
        return rep

def get_ranges_and_averages(examples):
    feature_count = 360
    min_values = [float("inf") for x in range(feature_count)]
    max_values = [float("-inf") for x in range(feature_count)]
    average_values = [0 for x in range(feature_count)]
    count = [0 for x in range(feature_count)]
    data = [None for x in range(feature_count)]
    
    for example in examples:
        for index, feature in enumerate(example[1]):
            if feature == 0:
                continue
            if data[index] is None:
                example_stats = ExampleStats()
                example_stats.feature_number = index + 1
                example_stats.average_value = feature
                example_stats.count += 1
                example_stats.min_value = feature
                example_stats.max_value = feature
                data[index] = example_stats
            else:
                data[index].count += 1
                data[index].average_value += feature
                if feature < data[index].min_value:
                    data[index].min_value = feature
                if feature > data[index].max_value:
                    data[index].max_value = feature

    for example in data:
        if example is None:
            continue
        example.average_value = round(example.average_value / example.count, 2)
    return data

def bucket_data(examples, data, bucket_count):
    feature_count = 360
    bucket_size = [1 for x in range(feature_count)]
    for index, stat in enumerate(data):
        if stat is None:
            continue
        bucket_size[index] = math.floor(data[index].max_value / bucket_count)
        if bucket_size[index] < 1:
            bucket_size[index] = 1
    for example in examples:
        for index in range(len(example[1])):
            example[1][index] = math.ceil(example[1][index] / bucket_size[index])

def print_data(data):
    newData = []
    for d in data:
        if d is not None:
            newData.append(d)
    print("Present Features:", len(newData))
    newData.sort(key=lambda x: x.count, reverse=True)
    print("Ten Most Frequent Features:")
    for index in range(65):
        print(newData[index])

    
examples = ioutil.read_svm_file('data-splits/data.train')
data = get_ranges_and_averages(examples)
print_data(data)
