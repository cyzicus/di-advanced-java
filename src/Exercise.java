
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Arrays.asList;

public class Exercise {

    public static void main(String[] args) {
        List<Student> students = asList(
                Student.getFromNameGpaListOfCourses("Fred", 3.2F, "Math", "Physics", "Art"),
                Student.getFromNameGpaListOfCourses("Jim", 2.2F, "History", "Biology"),
                Student.getFromNameGpaListOfCourses("Sheila", 3.8F, "Math", "Astronomy", "Art", "Fencing")
        );

        System.out.println("unsorted students: " + students);
        Collections.sort(students);
        System.out.println("sorted students: " + students);
        students.sort(Student.getNameComparator());
        System.out.println("by name: " + students);
        students.sort(Student.getEnthusiasmComparator());
        System.out.println("by enthusiasm: " + students);
        students.sort(Student.getReversedEnthusiasmComparatorAsLambda());
        System.out.println("by reverse enthusiasm: " + students);
        List<Student> goodStudents = Student.getSubsetOfStudents(students, Student.isSmart);
        System.out.println("goodStudents: " + goodStudents);
        List<Student> flunkies = Student.getBadStudents(students);
        System.out.println("flunkies = " + flunkies);
        List<Student> badStudents = Student.getSubsetOfStudents(students, Student.isDumb);
        System.out.println("badStudents = " + badStudents);
        List<Student> enthusiasticStudents = Student.getSubsetOfStudents(students, new Predicate<Student>() {
            @Override
            public boolean test(Student student) {
                return student.getCourses().size() > 3;
            }
        });
        System.out.println("enthusiasticStudents = " + enthusiasticStudents);
        List<Student> unEnthusiasticStudents =
                Student.getSubsetOfStudents(students, student -> student.getCourses().size() <=3);
        System.out.println("unEnthusiasticStudents = " + unEnthusiasticStudents);
    }


}
