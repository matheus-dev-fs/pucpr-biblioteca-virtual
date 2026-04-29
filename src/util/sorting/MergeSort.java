package util.sorting;

public class MergeSort {
    private long comparisons = 0;

    public String[] sort(String[] originalArray) {
        String[] arr = originalArray.clone();
        comparisons = 0;
        return mergeSort(arr);
    }

    private String[] mergeSort(String[] arr) {
        if (arr.length <= 1) return arr;

        int mid = arr.length / 2;
        String[] left = new String[mid];
        String[] right = new String[arr.length - mid];

        System.arraycopy(arr, 0, left, 0, mid);
        System.arraycopy(arr, mid, right, 0, arr.length - mid);

        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);

        return arr;
    }

    private void merge(String[] arr, String[] left, String[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            comparisons++;
            if (left[i].compareToIgnoreCase(right[j]) <= 0) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    public long getComparisons() {
        return comparisons;
    }
}