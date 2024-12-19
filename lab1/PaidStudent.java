class PaidStudent extends Student {
    public PaidStudent(String name, double averageScore, boolean sessionPassedOnTime) {
        super(name, averageScore, sessionPassedOnTime);
        this.budgetForm = false;
    }

    @Override
    public double getScholarship() {
        return 0.0;
    }
    @Override
    public String toString() {
        return "Имя: " + name + ", Средний балл: " + averageScore + ", Сессия сдана вовремя: " + sessionPassedOnTime +
                ", Форма обучения: Платная, Стипендия: " + getScholarship();
    }
}