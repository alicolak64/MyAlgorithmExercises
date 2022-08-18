import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * @author Ali Çolak
 * 17.03.2021
 */


public class Udemy {
    public static void main(String[] args) {
        Student student1 = new Student("Ali Colak", "Java Learn", "alicolak_64");

        Instructor instructor1 = new Instructor("Emre Altunbilek", "Java expert ", "emrealtunbilek_06");
        Instructor instructor2 = new Instructor("Atil Samancioğlu", "Android genius", "atilsamancioglu");

        Lesson lesson1 = new Lesson(1, "Introduction to Java", 3.5);
        Lesson lesson2 = new Lesson(2, "What is Java", 9.2);
        Lesson lesson3 = new Lesson(3, "Primitive Data Types ", 22.9);
        Lesson lesson4 = new Lesson(4, "Operators ", 25.6);
        Lesson lesson5 = new Lesson(5, "Control Structures ", 18.7);
        Lesson lesson6 = new Lesson(6, "Arrays", 18.8);

        Course java = new Course("Java", instructor1);
        java.addLesson(lesson1);
        java.addLesson(lesson2);
        java.addLesson(lesson3);
        java.addLesson(lesson4);
        java.addLesson(lesson5);
        java.addLesson(lesson6);

        Course android = new Course("Android", instructor1);
        Lesson lesson7 = new Lesson(1, "Introduction to Android", 6.5);
        Lesson lesson8 = new Lesson(2, "Objects", 28.7);
        Lesson lesson9 = new Lesson(3, "Try Catch", 36.6);
        Lesson lesson10 = new Lesson(4, "Recyclerview", 20.6);
        Lesson lesson11 = new Lesson(5, "Firebase", 12.8);

        java.addInstructorInCourse(instructor2);
        java.removeInstructorInCourse(instructor2);
        java.removeInstructorInCourse(instructor1);

        student1.joinCourse(java);

        student1.addLessonWatchList(lesson1);
        student1.addLessonWatchList(lesson3);
        student1.addLessonWatchList(lesson7);
        student1.joinCourse(android);
        student1.addLessonWatchList(lesson7);

        android.addLesson(lesson7);
        android.addLesson(lesson8);
        android.addLesson(lesson9);
        android.addLesson(lesson10);
        android.addLesson(lesson11);
        student1.joinCourse(android);
        student1.addLessonWatchList(lesson7);

        startWatchList(student1.getWatchList());

    }

    public static void startWatchList(LinkedList<Lesson> watchList) {

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        boolean next=true;

        ListIterator<Lesson> iterator = watchList.listIterator();
        if (watchList.size() == 0) {
            System.out.println("Watch list no lessons have been added yet");
        } else {
            Lesson firstLesson = iterator.next();
            System.out.println("Lesson currently watched : " + firstLesson.getTitleOfLesson() + " Minute of Lesson : " + firstLesson.getMinuteOfLesson());
        }
        showMenu();
        while (!exit) {
            System.out.print("Choice : ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 0 -> {
                    System.out.println("Exit the Udemy");
                    exit = true;
                }
                case 1 -> {
                    if (!next){
                        next=true;
                        if (iterator.hasNext()){
                            iterator.next();
                        }
                    }
                    if (iterator.hasNext()) {
                        Lesson lesson = iterator.next();
                        System.out.println("==============================================================================");
                        System.out.println("Lesson currently watched : " + lesson.getTitleOfLesson() + " Minute of Lesson : " + lesson.getMinuteOfLesson());
                        System.out.println("==============================================================================");
                    } else System.out.println("You are at the end of the watch list");
                }
                case 2 -> {
                    if (next){
                        next=false;
                        if (iterator.hasPrevious()){
                            iterator.previous();
                        }
                    }
                    if (iterator.hasPrevious()) {
                        Lesson lesson=iterator.previous();
                        System.out.println("==============================================================================");
                        System.out.println("Lesson currently watched : " + lesson.getTitleOfLesson() + " Minute of Lesson : " + lesson.getMinuteOfLesson());
                        System.out.println("==============================================================================");
                    } else System.out.println("You are at the top of the watch list");
                }
                case 3 -> showWatchList(watchList);
                case 9 -> showMenu();
            }
        }
    }

    public static void showWatchList(LinkedList<Lesson> watchList) {

        ListIterator<Lesson> iterator = watchList.listIterator();
        if (watchList.size() == 0) {
            System.out.println("Watch list no lessons have been added yet");
            return;
        } else {
            int counter = 1;
            while (iterator.hasNext()) {
                Lesson lesson = iterator.next();
                System.out.println("Order : " + counter + " Number of Lesson : " + lesson.getNumberOfLesson() + " Title of Lesson : " + lesson.getTitleOfLesson() + " Minute : " + lesson.getMinuteOfLesson());
                counter++;
            }
        }
    }

    public static void showMenu() {
        System.out.println("*************** MENU *****************");
        System.out.println("0- Exit");
        System.out.println("1- Next Lesson");
        System.out.println("2- Previous Lesson ");
        System.out.println("3- Show Watch List");
        System.out.println("9- Show Menu");
    }
}

abstract class Person {

    private String name;
    private String introductionOfCourse;
    private String userName;

    public Person(String name, String introductionOfCourse, String userName) {
        this.name = name;
        this.introductionOfCourse = introductionOfCourse;
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public String getIntroductionOfCourse() {
        return introductionOfCourse;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "{" + "Name : " + name + " User Name : " + userName +
                " Introduction Course : " + introductionOfCourse + " }";
    }

}

class Student extends Person {

    private ArrayList<Course> joinedCourse;
    private LinkedList<Lesson> watchList;

    public Student(String name, String introductionOfCourse, String userName) {
        super(name, introductionOfCourse, userName);
        this.joinedCourse = new ArrayList<>();
        this.watchList = new LinkedList<>();
    }

    public void joinCourse(Course course) {
        if (course.isActiveCourse()) {
            course.getStudentOfCourse().add(this);
            joinedCourse.add(course);
            System.out.println(" user "+ this.getName() + "joined " + course.getNameOfCourse()+" course");
        } else
            System.out.println(course.getNameOfCourse() + " course is not active yet so you can't join .");
    }

    public void addLessonWatchList(Lesson lesson) {
        boolean check = false;
        if (joinedCourse.size() > 0) {

            for (Course course : joinedCourse) {

                if (course.getLessonsOfCourse().contains(lesson)) {
                    System.out.println(lesson.getTitleOfLesson() + " lesson in course " + course.getNameOfCourse() + "a has been added to the to watch list");
                    watchList.add(lesson);
                    check = true;
                    break;
                }
            }
            if (!check) {
                System.out.println("The course entered was not found in the courses you are enrolled in, or you did not join the course containing this course ");
            }
        }
    }

    public LinkedList<Lesson> getWatchList() {
        return watchList;
    }

    @Override
    public String toString() {
        return super.toString() + " Joined Courses : " + joinedCourse;
    }
}

class Instructor extends Student {

    public Instructor(String name, String introductionOfCourse, String userName) {
        super(name, introductionOfCourse, userName);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

class Lesson {
    private int numberOfLesson;
    private String titleOfLesson;
    private double minuteOfLesson;

    public Lesson(int numberOfLesson, String titleOfLesson, double minuteOfLesson) {
        this.numberOfLesson = numberOfLesson;
        this.titleOfLesson = titleOfLesson;
        this.minuteOfLesson = minuteOfLesson;
    }

    public int getNumberOfLesson() {
        return numberOfLesson;
    }

    public String getTitleOfLesson() {
        return titleOfLesson;
    }

    public double getMinuteOfLesson() {
        return minuteOfLesson;
    }

    @Override
    public String toString() {
        return "{" + "Number of Lesson : " + numberOfLesson + " Title of Lesson : " + titleOfLesson +
                " Lesson : " + minuteOfLesson + " minute }";
    }
}

class Course {
    private String courseName;
    private ArrayList<Lesson> lessonsOfCourse;
    private ArrayList<Instructor> instructorOfCourse;
    private ArrayList<Student> studentOfCourse;
    //At least 5 lessons and the course content must be at least 60 minutes long

    private boolean active;

    public Course(String courseName, Instructor leadInstructor) {
        this.courseName = courseName;
        this.lessonsOfCourse = new ArrayList<>();
        this.instructorOfCourse = new ArrayList<>();
        this.studentOfCourse = new ArrayList<>();
        instructorOfCourse.add(0, leadInstructor);
        this.active = false;
    }

    public void addInstructorInCourse(Instructor instructor) {
        if (!instructorOfCourse.contains(instructor)) {
            instructorOfCourse.add(instructor);
            System.out.println(instructor.getName() + " add instructor in course");
        } else
            System.out.println("Already the instructor is among the instructors of this course. ");
    }

    public void removeInstructorInCourse(Instructor instructor) {
        if (!instructor.getName().equals(instructorOfCourse.get(0).getName())) {
            instructorOfCourse.remove(instructor);
            System.out.println(instructor.getName() + " remove course instructor ");
        } else
            System.out.println(instructor.getName() + " is the lead instructor of the course so the instructor cannot be removed from the course");

    }

    public void addLesson(Lesson lesson) {
        lessonsOfCourse.add(lesson);
    }

    public void removeLesson(Lesson lesson) {
        lessonsOfCourse.remove(lesson);
    }

    private int totalLessonsOfCourse() {
        return lessonsOfCourse.size();
    }

    private int totalMinuteOfCourse() {
        double totalOfCourse = 0;
        for (Lesson lesson : lessonsOfCourse) {
            totalOfCourse += lesson.getMinuteOfLesson();
        }
        return (int) totalOfCourse;
    }

    public boolean isActiveCourse() {
        if (totalLessonsOfCourse() >= 5 && totalMinuteOfCourse() >= 60) {
            active = true;
            return true;
        } else return false;
    }

    public ArrayList<Student> getStudentOfCourse() {
        return studentOfCourse;
    }

    public String getNameOfCourse() {
        return courseName;
    }

    public ArrayList<Lesson> getLessonsOfCourse() {
        return lessonsOfCourse;
    }
}