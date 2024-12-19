import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentManagerGUI {
    private Repository<Student> studentRepository;
    private DefaultListModel<String> listModel;

    public StudentManagerGUI() {
        // Инициализация репозитория с начальным массивом студентов
        Student[] studentsArray = {
                new PaidStudent("Григорий", 4.4, true),
                new ScholarshipStudent("Олег", 5.9, true),
                new ScholarshipStudent("Андрей", 6.7, true),
                new PaidStudent("Глеб", 5.1, false),
                new PaidStudent("Артем", 6.1, false),
                new PaidStudent("Александр", 5.5, false),
                new ScholarshipStudent("Кирилл", 8.9, true),
                new PaidStudent("Егор", 5.2, false),
                new ScholarshipStudent("Павел", 7.4, true)
        };
        studentRepository = new Repository<>(studentsArray);

        // Создание интерфейса
        JFrame frame = new JFrame("Управление студентами");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        listModel = new DefaultListModel<>();
        updateStudentList();

        JList<String> studentList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(studentList);

        JButton addButton = new JButton("Добавить студента");
        JButton removeButton = new JButton("Удалить студента");
        JButton updateButton = new JButton("Обновить студента");

        // Панель управления
        JPanel controlPanel = new JPanel();
        controlPanel.add(addButton);
        controlPanel.add(removeButton);
        controlPanel.add(updateButton);

        // Добавление компонентов на фрейм
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);

        // Обработчики событий
        addButton.addActionListener(e -> addStudent());
        removeButton.addActionListener(e -> {
            int selectedIndex = studentList.getSelectedIndex();
            if (selectedIndex != -1) {
                removeStudent(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(frame, "Выберите студента для удаления.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        updateButton.addActionListener(e -> {
            int selectedIndex = studentList.getSelectedIndex();
            if (selectedIndex != -1) {
                updateStudent(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(frame, "Выберите студента для обновления.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setVisible(true);
    }

    private void updateStudentList() {
        listModel.clear();
        for (Student student : studentRepository.getAllItems()) {
            listModel.addElement(student.toString());
        }
    }

    private void addStudent() {
        JTextField nameField = new JTextField();
        JTextField scoreField = new JTextField();
        JCheckBox sessionPassedCheckBox = new JCheckBox("Сессия сдана вовремя");
        String[] options = {"Платная", "Бюджет"};
        JComboBox<String> formComboBox = new JComboBox<>(options);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Имя:"));
        panel.add(nameField);
        panel.add(new JLabel("Средний балл:"));
        panel.add(scoreField);
        panel.add(sessionPassedCheckBox);
        panel.add(new JLabel("Форма обучения:"));
        panel.add(formComboBox);

        int result = JOptionPane.showConfirmDialog(null, panel, "Добавить студента", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                double score = Double.parseDouble(scoreField.getText());
                boolean sessionPassed = sessionPassedCheckBox.isSelected();
                if (formComboBox.getSelectedIndex() == 0) {
                    studentRepository.addItem(new PaidStudent(name, score, sessionPassed));
                } else {
                    studentRepository.addItem(new ScholarshipStudent(name, score, sessionPassed));
                }
                updateStudentList();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Некорректный ввод данных.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void removeStudent(int index) {
        studentRepository.removeItem(studentRepository.getAllItems().get(index));
        updateStudentList();
    }

    private void updateStudent(int index) {
        Student student = studentRepository.getAllItems().get(index);

        JTextField nameField = new JTextField(student.name);
        JTextField scoreField = new JTextField(String.valueOf(student.averageScore));
        JCheckBox sessionPassedCheckBox = new JCheckBox("Сессия сдана вовремя", student.sessionPassedOnTime);
        String[] options = {"Платная", "Бюджет"};
        JComboBox<String> formComboBox = new JComboBox<>(options);
        formComboBox.setSelectedIndex(student instanceof PaidStudent ? 0 : 1);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Имя:"));
        panel.add(nameField);
        panel.add(new JLabel("Средний балл:"));
        panel.add(scoreField);
        panel.add(sessionPassedCheckBox);
        panel.add(new JLabel("Форма обучения:"));
        panel.add(formComboBox);

        int result = JOptionPane.showConfirmDialog(null, panel, "Обновить студента", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                double score = Double.parseDouble(scoreField.getText());
                boolean sessionPassed = sessionPassedCheckBox.isSelected();
                Student updatedStudent;
                if (formComboBox.getSelectedIndex() == 0) {
                    updatedStudent = new PaidStudent(name, score, sessionPassed);
                } else {
                    updatedStudent = new ScholarshipStudent(name, score, sessionPassed);
                }
                studentRepository.updateItem(student, updatedStudent);
                updateStudentList();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Некорректный ввод данных.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentManagerGUI::new);
    }
}
