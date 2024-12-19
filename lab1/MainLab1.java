public class MainLab1 {
    public static void main(String[] args) {
        Student[] students = {
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

        System.out.println("\nИнформация о студентах:");
        for (Student student : students) {
            System.out.println(student.toString());
        }

        System.out.println("\nСтуденты получившие стипендию увеличенную на 25%:");
        for (Student student : students) {
            if (student.getScholarship()==125) {
                System.out.println(student.name);
            }
        }
        System.out.println("\nСтуденты обучающиеся на платной основе:");
        for (Student student : students) {
            if (!student.budgetForm) {
                System.out.println(student.name);
            }
        }
        double totalScore = 0;
        int count = 0;
        for (Student student : students) {
            totalScore += student.averageScore;
            count++;
        }
        double averageScore = totalScore / count;
        System.out.printf("Средний балл студентов: %.2f\n", averageScore);
    }
}