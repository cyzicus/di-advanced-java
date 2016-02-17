
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Arrays.asList;

public class Exercise {

    public static void main(String[] args) {
        List<Student> students = asList(
                Student.getFromNameGpaListOfCourses("Fred", 3.2F, "Math", "Physics", "Art"),
                Student.getFromNameGpaListOfCourses("Jim", 2.2F, "History", "Biology"),
                Student.getFromNameGpaListOfCourses("Sheila", 3.8F, "Math", "Astronomy", "Art", "Fencing"),
                Student.getFromNameGpaListOfCourses("Jessica", 3.2F, "Math", "Physics", "Art"),
                Student.getFromNameGpaListOfCourses("Jason", 3.5F, "History", "Biology"),
                Student.getFromNameGpaListOfCourses("Jerry", 2.9F, "Math", "Astronomy", "Art", "Fencing")
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
        List<Student> goodStudents = Student.getSubsetOfStudentsByPredicate(students, Student.isSmart(2.9F));
        System.out.println("goodStudents: " + goodStudents);
        List<Student> flunkies = Student.getBadStudents(students);
        System.out.println("flunkies = " + flunkies);
        List<Student> badStudents = Student.getSubsetOfStudentsByPredicate(students, Student.isDumb(3.0F));
        System.out.println("badStudents = " + badStudents);
        List<Student> enthusiasticStudents = Student.getSubsetOfStudentsByPredicate(students, new Predicate<Student>() {
            @Override
            public boolean test(Student student) {
                return student.getCourses().size() > 3;
            }
        });
        System.out.println("enthusiasticStudents = " + enthusiasticStudents);
        List<Student> unEnthusiasticStudents =
                Student.getSubsetOfStudentsByPredicate(students, student -> student.getCourses().size() <=3);
        System.out.println("unEnthusiasticStudents = " + unEnthusiasticStudents);
        System.out.println("unEnthusiasticStudents (not2): " + Student.getSubsetByPredicate(students, not2(student -> student.getCourses().size() > 3)));
        System.out.println("students with high gpa: " + Student.getSubsetOfStudentsLambda(students, Student.isSmart(3.0F)));
        System.out.println("students A-M: " + Student.getSubsetOfStudentsLambda(students, Student.startsWithFirstPartOfAlphabet('M')));
        System.out.println("math students: " + Student.getSubsetOfStudentsLambda(students, Student.takesCourse("Math")));

        System.out.println("non-math students (not): " + Student.getSubsetOfStudentsLambda(students, not(Student.takesCourse("Math"))));

        System.out.println("non-math students: " + Student.getSubsetOfStudentsLambda(students, Student.takesCourse("Math").negate()));

        List<String> rivers = asList("Rhone", "Amazon", "Rhine", "Mississippi");
        System.out.println("r rivers: " + Student.getSubsetByPredicate(rivers, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.toUpperCase().charAt(0) == 'R';
            }
        }));

        System.out.println("rivers that don't start with 'rh': "
                + Student.getSubsetByPredicate(rivers, river -> !river.toUpperCase().startsWith("RH")));
        System.out.println("***************************************");
        System.out.println("Courses the smart kids are taking:");
        students.stream()
                .filter(Student.isSmart(3.2F))
                .flatMap(student -> student.getCourses().stream())
                .distinct()
                .sorted()
                .forEach(c -> System.out.println("> " + c));

        System.out.println("***************************************");
        System.out.println("Students with a GPA > 3 whose name starts with J:");
        students.stream()
                .filter(Student.isSmart(3.0F))
                .filter(student -> student.getName().startsWith("J"))
                .sorted()
                .forEach(student -> System.out.println(student));

    }

    // This performs the same function as the "negate" method on Predicate interface
    // <E> stands for Element, but it really could be anything...<E> is the convention
    // <T> stands for Type...and should be used if the underlying 'thing' is java.lang.class
    public static <E> Predicate<E> not(Predicate<E> t) {
        return new Predicate<E>() {
            @Override
            public boolean test(E e) {
                return !t.test(e);
            }
        };
    }

    // Same function as above (and the negate method in general)
    public static <E> Predicate<E> not2(Predicate<E> t) {
        return e -> !t.test(e);
    }

}
