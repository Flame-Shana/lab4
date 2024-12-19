import java.util.Scanner;

public class MainLab1 {
    public static void main(String[] args) {
        // Инициализация массива студентов
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

        // Создание репозитория для работы с коллекцией студентов
        Repository<Student> studentRepository = new Repository<>(studentsArray);
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            printMenu(); // Вынесено в отдельный метод для улучшения читаемости
            choice = getValidatedInt(scanner, "Выберите действие: ");

            switch (choice) {
                case 1:
                    // Показ всех студентов
                    showAllStudents(studentRepository);
                    break;

                case 2:
                    // Добавление нового студента
                    addStudent(scanner, studentRepository);
                    break;

                case 3:
                    // Удаление студента
                    removeStudent(scanner, studentRepository);
                    break;

                case 4:
                    System.out.println("Выход из программы.");
                    break;

                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        } while (choice != 4);

        scanner.close();
    }

    // Метод для вывода меню
    private static void printMenu() {
        System.out.println("\nМеню:");
        System.out.println("1. Показать всех студентов");
        System.out.println("2. Добавить студента");
        System.out.println("3. Удалить студента");
        System.out.println("4. Выход");
    }

    // Метод для отображения всех студентов
    private static void showAllStudents(Repository<Student> repository) {
        System.out.println("\nИнформация о студентах:");
        for (Student student : repository.getAllItems()) {
            System.out.println(student);
        }
    }

    // Метод для добавления студента
    private static void addStudent(Scanner scanner, Repository<Student> repository) {
        System.out.print("Введите имя студента: ");
        String name = scanner.nextLine();

        double score = getValidatedDouble(scanner, "Введите средний балл: ");

        int sessionPassedOption;
        boolean sessionPassed;
        while (true) {
            sessionPassedOption = getValidatedInt(scanner, "Сессия сдана вовремя (1 - да, 2 - нет): ");
            if (sessionPassedOption == 1) {
                sessionPassed = true;
                break;
            } else if (sessionPassedOption == 2) {
                sessionPassed = false;
                break;
            } else {
                System.out.println("Некорректный ввод. Попробуйте снова.");
            }
        }

        int form;
        while (true) {
            form = getValidatedInt(scanner, "Форма обучения (1 - Платная, 2 - Бюджет): ");
            if (form == 1) {
                repository.addItem(new PaidStudent(name, score, sessionPassed));
                break;
            } else if (form == 2) {
                repository.addItem(new ScholarshipStudent(name, score, sessionPassed));
                break;
            } else {
                System.out.println("Некорректный ввод. Попробуйте снова.");
            }
        }
    }

    // Метод для удаления студента
    private static void removeStudent(Scanner scanner, Repository<Student> repository) {
        System.out.print("Введите имя студента для удаления: ");
        String removeName = scanner.nextLine();
        Student studentToRemove = null;
        for (Student student : repository.getAllItems()) {
            if (student.name.equals(removeName)) {
                studentToRemove = student;
                break;
            }
        }
        if (studentToRemove != null) {
            repository.removeItem(studentToRemove);
            System.out.println("Студент удален.");
        } else {
            System.out.println("Студент не найден.");
        }
    }

    // Метод для получения валидного целого числа
    private static int getValidatedInt(Scanner scanner, String message) {
        int result;
        while (true) {
            System.out.print(message);
            try {
                result = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Попробуйте снова.");
            }
        }
        return result;
    }

    // Метод для получения валидного числа с плавающей точкой
    private static double getValidatedDouble(Scanner scanner, String message) {
        double result;
        while (true) {
            System.out.print(message);
            try {
                result = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Попробуйте снова.");
            }
        }
        return result;
    }
}
