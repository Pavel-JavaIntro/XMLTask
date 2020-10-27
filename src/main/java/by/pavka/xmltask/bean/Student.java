package by.pavka.xmltask.bean;

import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable {
  private String login;
  private String name;
  private String faculty;
  private int telephone;
  private Address address = new Address();

  public Student() {
  }

  public Student(String login, String name, String faculty, int telephone, Address address) {
    this.login = login;
    this.name = name;
    this.faculty = faculty;
    this.telephone = telephone;
    this.address = address;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFaculty() {
    return faculty;
  }

  public void setFaculty(String faculty) {
    this.faculty = faculty;
  }

  public int getTelephone() {
    return telephone;
  }

  public void setTelephone(int telephone) {
    this.telephone = telephone;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Student student = (Student) o;
    return telephone == student.telephone &&
            Objects.equals(login, student.login) &&
            Objects.equals(name, student.name) &&
            Objects.equals(faculty, student.faculty) &&
            Objects.equals(address, student.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(login, name, faculty, telephone, address);
  }

  @Override
  public String toString() {
    return String.format("Student %s having login %s, faculty %s, phone %d, address %s", name,
            login, faculty, telephone, address);
  }

  public static class Address implements Serializable {
    private String country;
    private String city;
    private String street;

    public Address() {
    }

    public Address(String country, String city, String street) {
      this.country = country;
      this.city = city;
      this.street = street;
    }

    public String getCountry() {
      return country;
    }

    public void setCountry(String country) {
      this.country = country;
    }

    public String getCity() {
      return city;
    }

    public void setCity(String city) {
      this.city = city;
    }

    public String getStreet() {
      return street;
    }

    public void setStreet(String street) {
      this.street = street;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Address address = (Address) o;
      return Objects.equals(country, address.country) &&
              Objects.equals(city, address.city) &&
              Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
      return Objects.hash(country, city, street);
    }

    @Override
    public String toString() {
      return String.format("Address: country = %s, city = %s, street = %s", country, city, street);
    }
  }

}
