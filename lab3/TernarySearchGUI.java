import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class TernarySearchGUI {
    private DefaultListModel<Integer> listModel;
    private ArrayList<Integer> numbers;

    public TernarySearchGUI() {
        numbers = new ArrayList<>();
        listModel = new DefaultListModel<>();

        JFrame frame = new JFrame("Ternary Search GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JList<Integer> numberList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(numberList);

        JButton addButton = new JButton("Добавить число");
        JButton removeButton = new JButton("Удалить число");
        JButton sortButton = new JButton("Отсортировать");
        JButton searchButton = new JButton("Поиск числа");

        JPanel controlPanel = new JPanel();
        controlPanel.add(addButton);
        controlPanel.add(removeButton);
        controlPanel.add(sortButton);
        controlPanel.add(searchButton);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addNumber());
        removeButton.addActionListener(e -> removeNumber(numberList.getSelectedIndex()));
        sortButton.addActionListener(e -> sortNumbers());
        searchButton.addActionListener(e -> searchNumber());

        frame.setVisible(true);
    }

    private void addNumber() {
        String input = JOptionPane.showInputDialog("Введите число:");
        if (input != null) {
            try {
                int number = Integer.parseInt(input);
                numbers.add(number);
                listModel.addElement(number);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Некорректный ввод числа.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void removeNumber(int index) {
        if (index >= 0 && index < numbers.size()) {
            numbers.remove(index);
            listModel.remove(index);
        } else {
            JOptionPane.showMessageDialog(null, "Выберите элемент для удаления.", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sortNumbers() {
        Collections.sort(numbers);
        listModel.clear();
        for (int number : numbers) {
            listModel.addElement(number);
        }
        JOptionPane.showMessageDialog(null, "Массив отсортирован.");
    }

    private void searchNumber() {
        String input = JOptionPane.showInputDialog("Введите число для поиска:");
        if (input != null) {
            try {
                int target = Integer.parseInt(input);
                int index = ternarySearch(numbers, 0, numbers.size() - 1, target);
                if (index != -1) {
                    JOptionPane.showMessageDialog(null, "Число найдено на индексе: " + index);
                } else {
                    JOptionPane.showMessageDialog(null, "Число не найдено.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Некорректный ввод числа.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private int ternarySearch(ArrayList<Integer> arr, int left, int right, int target) {
        while (left <= right) {
            int m1 = left + (right - left) / 3;
            int m2 = right - (right - left) / 3;

            if (arr.get(m1) == target) return m1;
            if (arr.get(m2) == target) return m2;

            if (target < arr.get(m1)) {
                right = m1 - 1;
            } else if (target > arr.get(m2)) {
                left = m2 + 1;
            } else {
                left = m1 + 1;
                right = m2 - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TernarySearchGUI::new);
    }
}
