package by.pavka.xmltask;

import by.pavka.xmltask.parsing.builder.AbstractStudentsBuilder;
import by.pavka.xmltask.parsing.builder.StudentBuilderFactory;

public class Main {

    private final static String DOM = "dom";
    private final static String SAX = "sax";
    private final static String STAX = "stax";
    private final static String FILE = "target/classes/students.xml";

    public static void main(String[] args) {
        StudentBuilderFactory sFactory = new StudentBuilderFactory();


        AbstractStudentsBuilder domBuilder = sFactory.createStudentBuilder(DOM);
        domBuilder.buildSetStudents(FILE);
        System.out.println(domBuilder.getStudents());

        AbstractStudentsBuilder saxBuilder = sFactory.createStudentBuilder(SAX);
        saxBuilder.buildSetStudents(FILE);
        System.out.println(saxBuilder.getStudents());

        AbstractStudentsBuilder staxBuilder = sFactory.createStudentBuilder(STAX);
        staxBuilder.buildSetStudents(FILE);
        System.out.println(staxBuilder.getStudents());

    }
}
