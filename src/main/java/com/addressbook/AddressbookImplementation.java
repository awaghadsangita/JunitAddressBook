package com.addressbook;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class AddressbookImplementation {
    private List<Person> personList;
    private String fileName;

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    ObjectMapper objectmapper = new ObjectMapper();

    public int addPerson(String fname, String lname, String address, String city, String state, String zip, String ph) throws AddressBookCustomException, IOException {
            if (!Pattern.compile("^[A-Z]{1}[a-zA-Z]{1,20}$").matcher(fname).matches()) {
                throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.INVALID_NAME,"firstname must start with Uppercase and contain minimum two letters");
            }
            if (!Pattern.compile("^[A-Z]{1}[a-zA-Z]{1,20}$").matcher(lname).matches()) {
                throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.INVALID_NAME,"lastname must start with Uppercase and contain minimum two letters");
            }
            if (!Pattern.compile("^[a-zA-Z0-9,: -]{2,50}$").matcher(address).matches()) {
                throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.INVALID_ADDRESS,"address must be minimum two letters long");
            }
            if (!Pattern.compile("^[a-zA-Z]{2,50}$").matcher(city).matches()) {
                throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.INVALID_CITY,"city must be minimum two letters long and Should contain letters only");
            }
            if (!Pattern.compile("^[a-zA-Z]{2,50}$").matcher(state).matches()) {
                throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.INVALID_STATE,"state must be minimum two letters long and Should contain letters only");
            }
            if (!Pattern.compile("^[0-9]{6}$").matcher(zip).matches()) {
                throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.INVLAID_ZIP,"zip must be 6 digit long");
            }
            if (!Pattern.compile("^[0-9]{10}$").matcher(ph).matches()) {
                throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.INVALID_MOBILENUMBER,"mobile number must be 10 digit long");
            }
            Person personObj = new Person();

            personObj.setPersonId(personList.get(personList.size() - 1).getPersonId() + 1);
            personObj.setFirstName(fname);
            personObj.setLastName(lname);
            Address addressObj = new Address();
            addressObj.setAddress(address);
            addressObj.setCity(city);
            addressObj.setState(state);
            addressObj.setZip(Integer.valueOf(zip));
            personObj.setAddress(addressObj);
            personObj.setMobile(ph);
            if(personList.get(0).getPersonId()==0){
                personList.remove(0);
            }
            this.personList.add(personObj);

            AddresbookManagement managementObj = new AddresbookManagement();
            managementObj.saveAddresBook(this);

            return 1;
    }

    public void writeToJsonFile(List<Person> personList, File file) throws IOException {
        objectmapper.writeValue(file, personList);
    }

    public void readAddressBook(String filename) throws IOException {
        this.fileName = filename;
        this.personList = objectmapper.readValue(new File(this.fileName), new TypeReference<List<Person>>() {
        });
    }

    public int findPersonRecord(int personId) {
        int listIndex = -1;
        for (int i = 0; i < this.personList.size(); i++) {
            if (this.personList.get(i).getPersonId() == personId) {
                listIndex = i;
                break;
            }
        }
        return listIndex;
    }

    public int editMobileNumber(int personid, String newMobileNumber) throws AddressBookCustomException, IOException {
        int listIndex=this.findPersonRecord(personid);
            if (listIndex == -1) {
                throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.PERSON_NOT_FOUND,"can not find person");
            }
            if (!Pattern.compile("^[0-9]{10}$").matcher(newMobileNumber).matches()) {
                throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.INVALID_MOBILENUMBER,"mobile number must be 10 digit long");
            }
            this.personList.get(listIndex).setMobile(newMobileNumber);

            AddresbookManagement managementObj = new AddresbookManagement();
            managementObj.saveAddresBook(this);

            return 1;
    }

    public int editAddress(int personId, String address, String city, String state, String zip) throws AddressBookCustomException, IOException {
            int listIndex = this.findPersonRecord(personId);
            if (listIndex == -1) {
                throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.PERSON_NOT_FOUND,"can not find person");
            }
            if (!Pattern.compile("^[a-zA-Z0-9,: -]{2,50}$").matcher(address).matches()) {
                throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.INVALID_ADDRESS,"address must be minimum two letters long");
            }
            if (!Pattern.compile("^[a-zA-Z]{2,50}$").matcher(city).matches()) {
                throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.INVALID_CITY,"city must be minimum two letters long and Should contain letters only");
            }
            if (!Pattern.compile("^[a-zA-Z]{2,50}$").matcher(state).matches()) {
                throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.INVALID_STATE,"state must be minimum two letters long and Should contain letters only");
            }
            if (!Pattern.compile("^[0-9]{6}$").matcher(zip).matches()) {
                throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.INVLAID_ZIP,"zip must be 6 digit long");
            }
            Address addressObj = new Address();
            addressObj.setAddress(address);
            addressObj.setCity(city);
            addressObj.setState(state);
            addressObj.setZip(Integer.valueOf(zip));
            this.personList.get(listIndex).setAddress(addressObj);

            AddresbookManagement managementObj = new AddresbookManagement();
            managementObj.saveAddresBook(this);

            return 1;
    }

    public int deletePersonFromAddressbook(int personId) throws AddressBookCustomException, IOException {
            int listIndex = this.findPersonRecord(personId);
            if (listIndex == -1) {
                throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.PERSON_NOT_FOUND,"can not find person");
            }
            this.personList.remove(listIndex);

            AddresbookManagement managementObj = new AddresbookManagement();
            managementObj.saveAddresBook(this);

            return 1;

    }

    public List<Person> sortByLastname() throws IOException {
        for (int i = 0; i < this.personList.size() - 1; i++) {
            for (int j = 0; j < this.personList.size() - i - 1; j++) {
                if (this.personList.get(j).getLastName().compareTo(this.personList.get(j + 1).getLastName()) > 0) {
                    Person tempObj = this.personList.get(j);
                    this.personList.set(j, this.personList.get(j + 1));
                    this.personList.set(j + 1, tempObj);
                }
            }
        }
        writeToJsonFile(this.personList, new File(this.fileName));
        return this.personList;
    }

    public List<Person> sortByZip() throws IOException {
        for (int i = 0; i < this.personList.size() - 1; i++) {
            for (int j = 0; j < this.personList.size() - i - 1; j++) {
                if (this.personList.get(j).getAddress().getZip() > this.personList.get(j + 1).getAddress().getZip()) {
                    Person tempObj = this.personList.get(j);
                    this.personList.set(j, this.personList.get(j + 1));
                    this.personList.set(j + 1, tempObj);
                }
            }
        }
        writeToJsonFile(this.personList, new File(this.fileName));
        return this.personList;
    }

    public void sortByPersonId() throws IOException {
        for (int i = 0; i < this.personList.size() - 1; i++) {
            for (int j = 0; j < this.personList.size() - i - 1; j++) {
                if (this.personList.get(j).getPersonId() > this.personList.get(j + 1).getPersonId()) {
                    Person tempObj = this.personList.get(j);
                    this.personList.set(j, this.personList.get(j + 1));
                    this.personList.set(j + 1, tempObj);
                }
            }
        }
        writeToJsonFile(this.personList, new File(this.fileName));
    }
}
