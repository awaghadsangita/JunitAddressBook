package com.addressbook;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AddressbookImplementation {
    private List<Person> personList;
    private String fileName;
    ObjectMapper objectmapper = new ObjectMapper();
    public int addPerson(String fname, String lname, String address, String city, String state, String zip, String ph) throws IOException {
            Person personObj = new Person();
            personObj.setPersonId(personList.get(personList.size()-1).getPersonId()+1);
            personObj.setFirstName(fname);
            personObj.setLastName(lname);
            Address addressObj=new Address();
            addressObj.setAddress(address);
            addressObj.setCity(city);
            addressObj.setState(state);
            addressObj.setZip(zip);
            personObj.setAddress(addressObj);
            personObj.setMobile(ph);
            personList.add(personObj);

            writeToJsonFile(personList);
            return 1;
        }

        public void writeToJsonFile(List<Person> personList) throws IOException {
            objectmapper.writeValue(new File(this.fileName),personList);
        }
        public void readAddressBook(String filename) throws IOException {
            this.fileName=filename;
            this.personList = objectmapper.readValue(new File(this.fileName), new TypeReference<List<Person>>(){
            });
        }

}
