class Solution {
    public int ternarySearch(int[] arr, int left, int right, int target) {
        // Инициализация результата как -1 (элемент не найден)
        int result = -1;

        // Пока левая граница не пересекается с правой
        while (left <= right) {
            // Вычисление первой и второй трети
            int m1 = left + (right - left) / 3;
            int m2 = right - (right - left) / 3;

            // Если элемент найден в первой трети
            if (arr[m1] == target) {
                result = m1;
                break;
            }
            // Если элемент найден во второй трети
            else if (arr[m2] == target) {
                result = m2;
                break;
            }
            // Если элемент меньше, чем значение в первой трети
            else if (target < arr[m1]) {
                right = m1 - 1; // Ищем в левой части
            }
            // Если элемент больше, чем значение во второй трети
            else if (target > arr[m2]) {
                left = m2 + 1; // Ищем в правой части
            }
            // Если элемент в пределах между m1 и m2
            else {
                left = m1 + 1; // Смещаем левую границу
                right = m2 - 1; // Смещаем правую границу
            }
        }
        // Возвращаем индекс найденного элемента или -1
        return result;
    }
}