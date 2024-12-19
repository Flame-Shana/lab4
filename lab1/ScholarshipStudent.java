class ScholarshipStudent extends Student {
    public ScholarshipStudent(String name, double averageScore, boolean sessionPassedOnTime) {
        super(name, averageScore, sessionPassedOnTime);
        this.budgetForm = true;
    }

    @Override
    public double getScholarship() {
        if (averageScore < 5.0 && sessionPassedOnTime) {
            return 0.0;
        } else if (averageScore < 6.0 && sessionPassedOnTime) {
            return 100.0;
        } else if (averageScore < 8.0 && sessionPassedOnTime) {
            return 125.0;
        } else if (averageScore <= 10.0 && sessionPassedOnTime) {
            return 150.0;
        }
        return 0;
    }
    @Override
    public String toString() {
        return "Имя: " + name + ", Средний балл: " + averageScore + ", Сессия сдана вовремя: " + sessionPassedOnTime +
                ", Форма обучения: Бюджет, Стипендия: " + getScholarship();
    }
}