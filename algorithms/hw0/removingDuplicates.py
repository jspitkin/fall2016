def main():
    testArray = [100, 1, 1, 1, 1, 1, 4]
    result = removeDuplicates(testArray)

    for element in result:
        print element

# Returns a new array that is a copy
# of 'a' with duplicates removed.
# The order of the returned array is not guaranteed
def removeDuplicates(a):
    if len(a) < 2:
        return a

    sortedArray = sorted(a)
    previousElement = sortedArray[0]
    resultArray = []
    resultArray.append(previousElement)

    for element in sortedArray[1:]:
        if element != previousElement:
            resultArray.append(element)
            previousElement = element

    return resultArray

if __name__ == "__main__":
    main()
