abstract class Student {
    protected String name;
    protected boolean budgetForm;
    protected double averageScore;
    protected boolean sessionPassedOnTime;

    public Student(String name, double averageScore, boolean sessionPassedOnTime) {
        this.name = name;
        this.averageScore = averageScore;
        this.sessionPassedOnTime = sessionPassedOnTime;
    }

    public abstract double getScholarship();
}