import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    @Test
    void testElementExistsInMiddle() {
        Solution solution = new Solution();
        int[] array = {1, 3, 5, 7, 9};
        int target = 5;
        int result = solution.ternarySearch(array, 0, array.length - 1, target);
        assertEquals(2, result, "Элемент 5 должен находиться на индексе 2");
    }

    @Test
    void testElementExistsAtStart() {
        Solution solution = new Solution();
        int[] array = {1, 3, 5, 7, 9};
        int target = 1;
        int result = solution.ternarySearch(array, 0, array.length - 1, target);
        assertEquals(0, result, "Элемент 1 должен находиться на индексе 0");
    }

    @Test
    void testElementExistsAtEnd() {
        Solution solution = new Solution();
        int[] array = {1, 3, 5, 7, 9};
        int target = 9;
        int result = solution.ternarySearch(array, 0, array.length - 1, target);
        assertEquals(4, result, "Элемент 9 должен находиться на индексе 4");
    }

    @Test
    void testElementDoesNotExist() {
        Solution solution = new Solution();
        int[] array = {1, 3, 5, 7, 9};
        int target = 6;
        int result = solution.ternarySearch(array, 0, array.length - 1, target);
        assertEquals(-1, result, "Элемент 6 отсутствует в массиве, должен вернуть -1");
    }

    @Test
    void testEmptyArray() {
        Solution solution = new Solution();
        int[] array = {};
        int target = 5;
        int result = solution.ternarySearch(array, 0, array.length - 1, target);
        assertEquals(-1, result, "Поиск в пустом массиве должен вернуть -1");
    }

    @Test
    void testSingleElementArrayFound() {
        Solution solution = new Solution();
        int[] array = {5};
        int target = 5;
        int result = solution.ternarySearch(array, 0, array.length - 1, target);
        assertEquals(0, result, "Элемент 5 в массиве из одного элемента должен быть на индексе 0");
    }

    @Test
    void testSingleElementArrayNotFound() {
        Solution solution = new Solution();
        int[] array = {5};
        int target = 3;
        int result = solution.ternarySearch(array, 0, array.length - 1, target);
        assertEquals(-1, result, "Элемент 3 отсутствует в массиве из одного элемента, должен вернуть -1");
    }

    @Test
    void testMultipleOccurrences() {
        Solution solution = new Solution();
        int[] array = {1, 2, 2, 2, 3};
        int target = 2;
        int result = solution.ternarySearch(array, 0, array.length - 1, target);
        assertEquals(1, result, "Элемент 2 должен быть найден (один из индексов с элементом 2)");
    }
}
