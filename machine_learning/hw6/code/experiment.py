import ioutil
import SGD

def main():
    FEATURE_COUNT = 124
    EPOCH = 1
    TRAIN_DATA_PATH = 'a5a.train'
    TEST_DATA_PATH = 'a5a.test'
    SPLIT_DATA_PATHS = []
    SPLIT_DATA_PATHS.append('splits/split1.test')
    SPLIT_DATA_PATHS.append('splits/split2.test')
    SPLIT_DATA_PATHS.append('splits/split3.test')
    SPLIT_DATA_PATHS.append('splits/split4.test')
    SPLIT_DATA_PATHS.append('splits/split5.test')
    SPLIT_DATA_PATHS.append('splits/split6.test')

    training_examples = ioutil.read_file(TRAIN_DATA_PATH, FEATURE_COUNT)
    test_examples = ioutil.read_file(TEST_DATA_PATH, FEATURE_COUNT)

if __name__ == "__main__":
    main()
