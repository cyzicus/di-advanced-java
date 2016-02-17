import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Student implements Comparable<Student> {

    private String name;
    private float gpa;
    private List<String> courses;

    public String getName() {
        return name;
    }

    public float getGpa() {
        return gpa;
    }

    public List<String> getCourses() {
        return courses;
    }

    private Student() {

    }

    public static Student getFromNameGpaListOfCourses(final String name, final float gpa, final String... courses) {
        Student self = new Student();
        self.name = name;
        self.gpa = gpa;
        self.courses = asList(courses);
        return self;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", gpa=" + gpa +
                ", courses=" + courses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (Float.compare(student.gpa, gpa) != 0) return false;
        if (name != null ? !name.equals(student.name) : student.name != null) return false;
        return courses != null ? courses.equals(student.courses) : student.courses == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (gpa != +0.0f ? Float.floatToIntBits(gpa) : 0);
        result = 31 * result + (courses != null ? courses.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(final Student o) {
        return new Float(gpa).compareTo(o.getGpa());
    }

    private static Comparator<Student> nameComparator = new StudentNameComparator();

    public static Comparator<Student> getNameComparator() {
        return nameComparator;
    }

    private static class StudentNameComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    public static Comparator<Student> getEnthusiasmComparator() {
        return new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.courses.size() - o2.courses.size();
            }
        };
    }

    public static Comparator<Student> getReversedEnthusiasmComparatorAsLambda() {
        return (o1, o2) -> o2.courses.size() - o1.courses.size();
    }

//    public static List<Student> getSubsetOfStudentsByPredicate(List<Student> allStudents) {
//        List<Student> goodStudents = new ArrayList<>();
//        allStudents.forEach(student -> {
//            if (student.getGpa() >= 3.0F) {
//                goodStudents.add(student);
//            }});
//        return Collections.unmodifiableList(goodStudents);
//    }

    public static List<Student> getBadStudents(final List<Student> allStudents) {
        return Collections.unmodifiableList(allStudents.stream()
                    .filter(item -> item.getGpa() < 3.0F)
                    .collect(Collectors.toList()));
    }

    public static List<Student> getSubsetOfStudentsByPredicate(final List<Student> allStudents, final Predicate<Student> criterion) {
        List<Student> goodStudents = new ArrayList<>();
        allStudents.forEach(student -> {
            if (criterion.test(student)) {
                goodStudents.add(student);
            }});
        return Collections.unmodifiableList(goodStudents);
    }

    public static <E> List<E> getSubsetByPredicate(final List<E> inputList, final Predicate<E> criterion) {
        List<E> returnedList = new ArrayList<>();
        for (E s : inputList) {
            if(criterion.test(s)) {
                returnedList.add(s);
            }
        }
        return Collections.unmodifiableList(returnedList);
    }

    public static List<Student> getSubsetOfStudentsLambda(final List<Student> students, final Predicate<Student> criterion) {
        return students.stream()
                .filter(student -> criterion.test(student))
                .collect(Collectors.toList());
    }

//    public static Predicate<Student> isSmart(float threshold) = new Predicate<Student>() {
//        @Override
//        public boolean test(Student student) {
//            return student.getGpa() >= 3.0F;
//        }
//    }

//    public static Predicate<Student> isSmart(final float threshold) {
//        return new Predicate<Student>() {
//            @Override
//            public boolean test(Student student) {
//                return student.getGpa() >= threshold;
//            }
//        };
//    }

    public static Predicate<Student> isSmart(final float threshold) {
        return student -> student.getGpa() >= threshold;
    }

    public static Predicate<Student> isDumb(float threshold) { // effectively final
        return student -> student.getGpa() <= threshold;
    }

//    public static Predicate<Student> isDumb = student -> student.getGpa() < 3.0F;

//    public static Predicate<Student> startsWithFirstHalfOfAlphabet = new Predicate<Student>() {
//        @Override
//        public boolean test(Student student) {
//            return student.name.toUpperCase().charAt(0) <= 'M';
//        }
//    };

//    public static Predicate<Student> startsWithFirstPartOfAlphabet(char threshold) {
//        return new Predicate<Student>() {
//            @Override
//            public boolean test(Student student) {
//                return student.name.toUpperCase().charAt(0) <= threshold;
//            }
//        };
//    }

    public static Predicate<Student> startsWithFirstPartOfAlphabet(char threshold) {
        return student -> student.name.toUpperCase().charAt(0) <= threshold;
    }

    public static Predicate<Student> takesCourse(String course) {
        return student -> student.courses.contains(course);
    }

}
