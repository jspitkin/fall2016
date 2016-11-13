# Jake Pitkin November 12 2016
import ioutil
import SVM

def main():
    training_data = ioutil.read_data('handwriting/train.data', 'handwriting/train.labels')
    test_data = ioutil.read_data('handwriting/test.data', 'handwriting/test.labels')

    initial_learning_rate = 0.01
    C = 1
    epoch = 1

    svm = SVM.SVM(initial_learning_rate, C, epoch)
    svm.train(training_data)
    training_accuracy = svm.test(training_data)
    test_accuracy = svm.test(test_data)

    print("Training Accuracy:")
    print("Mistakes:", training_accuracy['mistakes'], sep='\t')
    print("Example Count:", training_accuracy['example_count'], sep='\t')
    print("Accuracy:", training_accuracy['accuracy'], sep='\t')
    print()
    print("Test Accuracy:")
    print("Mistakes:", test_accuracy['mistakes'], sep='\t')
    print("Example Count:", test_accuracy['example_count'], sep='\t')
    print("Accuracy:", test_accuracy['accuracy'], sep='\t')

if __name__ == '__main__':
    main()
