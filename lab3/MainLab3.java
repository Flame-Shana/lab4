import java.util.Arrays;
import java.util.Scanner;

public class MainLab3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();
        int[] array = new int[size];

        System.out.println("Введите элементы массива:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        Arrays.sort(array);
        System.out.println("Отсортированный массив: " + Arrays.toString(array));

        System.out.print("Введите число для поиска: ");
        int target = scanner.nextInt();

        Solution solution = new Solution();
        int index = solution.ternarySearch(array, 0, size - 1, target);

        if (index != -1) {
            System.out.println("Элемент найден на индексе: " + index);
        } else {
            System.out.println("Элемент не найден.");
        }
    }
}