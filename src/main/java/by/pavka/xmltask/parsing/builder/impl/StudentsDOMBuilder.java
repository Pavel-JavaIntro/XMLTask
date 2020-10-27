package by.pavka.xmltask.parsing.builder.impl;

import by.pavka.xmltask.bean.Student;
import by.pavka.xmltask.parsing.builder.AbstractStudentsBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class StudentsDOMBuilder extends AbstractStudentsBuilder {
  private Set<Student> students;
  private DocumentBuilder docBuilder;
  public StudentsDOMBuilder() {
    this.students = new HashSet<Student>();
    // создание DOM-анализатора
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try {
      docBuilder = factory.newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      System.err.println("Ошибка конфигурации парсера: " + e);
    }
  }
  public Set<Student> getStudents() {
    return students;
  }

  @Override
  public void buildSetStudents(String fileName) {
    Document doc = null;
    try {
      // parsing XML-документа и создание древовидной структуры
      doc = docBuilder.parse(fileName);
      Element root = doc.getDocumentElement();
      // получение списка дочерних элементов <student>
      NodeList studentsList = root.getElementsByTagName("student");
      for (int i = 0; i < studentsList.getLength(); i++) {
        Element studentElement = (Element) studentsList.item(i);
        Student student = buildStudent(studentElement);
        students.add(student);
      }
    } catch (IOException e) {
      System.err.println("File error or I/O error: " + e);
    } catch (SAXException e) {
      System.err.println("Parsing failure: " + e);
    }
  }
  private Student buildStudent(Element studentElement) {
    Student student = new Student();
    // заполнение объекта student
    student.setFaculty(studentElement.getAttribute("faculty")); // проверка на null
    student.setName(getElementTextContent(studentElement, "name"));
    Integer tel = Integer.parseInt(getElementTextContent(studentElement,
            "telephone"));
    student.setTelephone(tel);
    Student.Address address = student.getAddress();
    // заполнение объекта address
    Element adressElement = (Element) studentElement.getElementsByTagName("address").item(0);
    address.setCountry(getElementTextContent(adressElement, "country"));
    address.setCity(getElementTextContent(adressElement, "city"));
    address.setStreet(getElementTextContent(adressElement, "street"));
    student.setLogin(studentElement.getAttribute("login"));
    return student;
  }
  // получение текстового содержимого тега
  private static String getElementTextContent(Element element, String elementName) {
    NodeList nList = element.getElementsByTagName(elementName);
    Node node = nList.item(0);
    String text = node.getTextContent();
    return text;
  }
}
