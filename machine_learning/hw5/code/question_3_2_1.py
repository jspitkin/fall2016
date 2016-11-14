import RandomForest as rf
import ioutil

def main():
   m = 100
   N = 5
   feature_count = 256

   training_data = ioutil.read_data('handwriting/train.data', 'handwriting/train.labels', False)
   test_data = ioutil.read_data('handwriting/test.data', 'handwriting/test.labels', False)

   random_forest = rf.RandomForest(m, N, feature_count)

if __name__ == '__main__':
    main()
