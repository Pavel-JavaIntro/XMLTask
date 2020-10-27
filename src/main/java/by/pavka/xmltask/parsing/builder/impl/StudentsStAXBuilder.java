package by.pavka.xmltask.parsing.builder.impl;

import by.pavka.xmltask.bean.Student;
import by.pavka.xmltask.bean.StudentEnum;
import by.pavka.xmltask.parsing.builder.AbstractStudentsBuilder;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class StudentsStAXBuilder extends AbstractStudentsBuilder {
  private Set<Student> students = new HashSet<>();
  private XMLInputFactory inputFactory;

  public StudentsStAXBuilder() {
    inputFactory = XMLInputFactory.newInstance();
  }

  public Set<Student> getStudents() {
    return students;
  }

  @Override
  public void buildSetStudents(String fileName) {
    FileInputStream inputStream = null;
    XMLStreamReader reader = null;
    String name;
    try {
      inputStream = new FileInputStream(new File(fileName));
      reader = inputFactory.createXMLStreamReader(inputStream);
      // StAX parsing
      while (reader.hasNext()) {
        int type = reader.next();
        if (type == XMLStreamConstants.START_ELEMENT) {
          name = reader.getLocalName();
          if (StudentEnum.valueOf(name.toUpperCase()) == StudentEnum.STUDENT) {
            Student st = buildStudent(reader);
            students.add(st);
          }
        }
      }
    } catch (XMLStreamException ex) {
      System.err.println("StAX parsing error! " + ex.getMessage());
    } catch (FileNotFoundException ex) {
      System.err.println("File " + fileName + " not found! " + ex);
    } finally {
      try {
        if (inputStream != null) {
          inputStream.close();
        }
      } catch (IOException e) {
        System.err.println("Impossible close file " + fileName + " : " + e);
      }
    }
  }

  private Student buildStudent(XMLStreamReader reader) throws XMLStreamException {
    Student st = new Student();
    st.setLogin(reader.getAttributeValue(null, StudentEnum.LOGIN.getValue()));
    st.setFaculty(reader.getAttributeValue(null,
            StudentEnum.FACULTY.getValue()));
    String name;
    while (reader.hasNext()) {
      int type = reader.next();
      switch (type) {
        case XMLStreamConstants.START_ELEMENT:
          name = reader.getLocalName();
          switch (StudentEnum.valueOf(name.toUpperCase())) {
            case NAME:
              st.setName(getXMLText(reader));
              break;
            case TELEPHONE:
              name = getXMLText(reader);
              st.setTelephone(Integer.parseInt(name));
              break;
            case ADDRESS:
              st.setAddress(getXMLAddress(reader));
              break;
          }
          break;
        case XMLStreamConstants.END_ELEMENT:
          name = reader.getLocalName();
          if (StudentEnum.valueOf(name.toUpperCase()) ==
                  StudentEnum.STUDENT) {
            return st;
          }
          break;
      }

    }
    throw new XMLStreamException("Unknown element in tag Student");
  }

  private Student.Address getXMLAddress(XMLStreamReader reader)
          throws XMLStreamException {
    Student.Address address = new Student.Address();
    int type;
    String name;
    while (reader.hasNext()) {
      type = reader.next();
      switch (type) {
        case XMLStreamConstants.START_ELEMENT:
          name = reader.getLocalName();
          switch (StudentEnum.valueOf(name.toUpperCase())) {
            case COUNTRY:
              address.setCountry(getXMLText(reader));
              break;
            case CITY:
              address.setCity(getXMLText(reader));
              break;
            case STREET:
              address.setStreet(getXMLText(reader));
              break;
          }
          break;
        case XMLStreamConstants.END_ELEMENT:
          name = reader.getLocalName();
          if (StudentEnum.valueOf(name.toUpperCase()) ==
                  StudentEnum.ADDRESS) {
            return address;
          }
          break;
      }
    }
    throw new XMLStreamException("Unknown element in tag Address");
  }

  private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
    String text = null;
    if (reader.hasNext()) {
      reader.next();
      text = reader.getText();
    }
    return text;
  }
}
