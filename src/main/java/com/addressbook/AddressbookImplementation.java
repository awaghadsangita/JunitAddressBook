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
    ObjectMapper objectmapper = new ObjectMapper();

    public Object addPerson(String fname, String lname, String address, String city, String state, String zip, String ph) throws Exception {
        try {
            System.out.println(Pattern.compile("^[A-Z]{1}[a-zA-Z]{1,20}$").matcher(fname).matches());
            if (!Pattern.compile("^[A-Z]{1}[a-zA-Z]{1,20}$").matcher(fname).matches()) {
                throw new Exception("firstname must start with Uppercase and contain minimum two letters");
            }

            if (!Pattern.compile("^[A-Z]{1}[a-zA-Z]{1,20}$").matcher(lname).matches()) {
                throw new Exception("lastname must start with Uppercase and contain minimum two letters");
            }
            if (!Pattern.compile("^[a-zA-Z0-9,: -]{2,50}$").matcher(address).matches()) {
                throw new Exception("address must be minimum two letters long");
            }
            if (!Pattern.compile("^[a-zA-Z]{2,50}$").matcher(city).matches()) {
                throw new Exception("city must be minimum two letters long and Should contain letters only");
            }
            if (!Pattern.compile("^[a-zA-Z]{2,50}$").matcher(state).matches()) {
                throw new Exception("state must be minimum two letters long and Should contain letters only");
            }
            if (!Pattern.compile("^[0-9]{6}$").matcher(zip).matches()) {
                throw new Exception("zip must be 6 digit long");
            }
            if (!Pattern.compile("^[0-9]{10}$").matcher(ph).matches()) {
                throw new Exception("mobile number must be 10 digit long");
            }
            Person personObj = new Person();
            personObj.setPersonId(personList.get(personList.size() - 1).getPersonId() + 1);
            personObj.setFirstName(fname);
            personObj.setLastName(lname);
            Address addressObj = new Address();
            addressObj.setAddress(address);
            addressObj.setCity(city);
            addressObj.setState(state);
            addressObj.setZip(zip);
            personObj.setAddress(addressObj);
            personObj.setMobile(ph);
            this.personList.add(personObj);

            writeToJsonFile(this.personList);
            return 1;
        } catch (Exception e) {
            return e;
        }
    }


    public void writeToJsonFile(List<Person> personList) throws IOException {
        objectmapper.writeValue(new File(this.fileName), personList);
    }

    public void readAddressBook(String filename) throws IOException {
        this.fileName = filename;
        this.personList = objectmapper.readValue(new File(this.fileName), new TypeReference<List<Person>>() {
        });
    }

    public int findPersonRecord(int personId) {
        int listIndex=-1;
        for(int i=0;i<this.personList.size();i++){
            if(this.personList.get(i).getPersonId()==personId){
                listIndex=i;
                break;
            }
        }
        return listIndex;
    }

    public Object editMobileNumber(int listIndex,String newMobileNumber){
        try{
            if(listIndex==-1)
            {
                throw new Exception("person addressbook id does not exit");
            }
            if (!Pattern.compile("^[0-9]{10}$").matcher(newMobileNumber).matches()) {
                throw new Exception("mobile number must be 10 digit long");
            }
            this.personList.get(listIndex).setMobile(newMobileNumber);
            writeToJsonFile(this.personList);
            return 1;
        }catch(Exception e){
            return e;
        }
    }

    public Object editAddress(int listIndex, String address, String city, String state, String zip) {
        try{
            if(listIndex==-1)
            {
                throw new Exception("person addressbook id does not exit");
            }
            if (!Pattern.compile("^[a-zA-Z0-9,: -]{2,50}$").matcher(address).matches()) {
                throw new Exception("address must be minimum two letters long");
            }
            if (!Pattern.compile("^[a-zA-Z]{2,50}$").matcher(city).matches()) {
                throw new Exception("city must be minimum two letters long and Should contain letters only");
            }
            if (!Pattern.compile("^[a-zA-Z]{2,50}$").matcher(state).matches()) {
                throw new Exception("state must be minimum two letters long and Should contain letters only");
            }
            if (!Pattern.compile("^[0-9]{6}$").matcher(zip).matches()) {
                throw new Exception("zip must be 6 digit long");
            }
            Address addressObj = new Address();
            addressObj.setAddress(address);
            addressObj.setCity(city);
            addressObj.setState(state);
            addressObj.setZip(zip);
            this.personList.get(listIndex).setAddress(addressObj);
            writeToJsonFile(this.personList);
            return 1;
        }catch(Exception e){
            return e;
        }
    }
}
