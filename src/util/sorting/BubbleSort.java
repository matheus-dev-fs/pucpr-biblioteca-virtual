package util.sorting;

public class BubbleSort {
    private long comparisons = 0;

    public String[] sort(String[] originalArray) {
        String[] arr = originalArray.clone();
        comparisons = 0;
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                comparisons++;

                if (arr[j].compareToIgnoreCase(arr[j + 1]) > 0) {
                    String temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        return arr;
    }

    public long getComparisons() {
        return comparisons;
    }
}