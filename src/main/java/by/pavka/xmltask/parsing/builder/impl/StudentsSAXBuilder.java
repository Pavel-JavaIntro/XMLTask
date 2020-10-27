package by.pavka.xmltask.parsing.builder.impl;

import by.pavka.xmltask.bean.Student;
import by.pavka.xmltask.parsing.builder.AbstractStudentsBuilder;
import by.pavka.xmltask.parsing.handler.StudentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

public class StudentsSAXBuilder extends AbstractStudentsBuilder {
  private Set<Student> students;
  private StudentHandler handler;
  private XMLReader reader;
  public StudentsSAXBuilder() {
    handler = new StudentHandler();
    try {
      reader = XMLReaderFactory.createXMLReader();
      reader.setContentHandler(handler);
    } catch (SAXException e) {
      System.err.print("ошибка SAX парсера: " + e);
    }
  }
  public Set<Student> getStudents() {
    return students;
  }
  public void buildSetStudents(String fileName) {
    try {
      reader.parse(fileName);
    } catch (SAXException e) {
      System.err.print("ошибка SAX парсера: " + e);
    } catch (IOException e) {
      System.err.print("ошибка I/О потока: " + e);
    }
    students = handler.getStudents();
  }
}
