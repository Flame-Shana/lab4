class Solution {
    public int ternarySearch(int[] arr, int left, int right, int target) {
        int result = -1;
        while (left <= right) {
            int m1 = left + (right - left) / 3;
            int m2 = right - (right - left) / 3;

            if (arr[m1] == target) {
                result = m1;
                break;
            } else if (arr[m2] == target) {
                result = m2;
                break;
            } else if (target < arr[m1]) {
                right = m1 - 1;
            } else if (target > arr[m2]) {
                left = m2 + 1;
            } else {
                left = m1 + 1;
                right = m2 - 1;
            }
        }
        return result;
    }
}
