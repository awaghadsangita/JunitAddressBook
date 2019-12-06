package com.addressbook;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class AddresbookManagement {
    public int createAddressbook(String addressbookName) throws IOException, AddressBookCustomException {

        String filename = "/home/admin1/IdeaProjects/AddressBook/src/main/resources/" + addressbookName + ".json";
        File file = new File(filename);
        if (file.createNewFile()) {
            FileWriter fw = new FileWriter(filename);
            String object = "[{" + '"' + "personId" + '"' + ":0}]";
            fw.write(object);
            fw.close();
            return 1;
        } else {
            throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.ADDRESSBOOK_ALREADY_EXIST, "given name addressbook already exist");
        }

    }

    public AddressbookImplementation openAddressbook(String addressbookName) throws IOException, AddressBookCustomException {
        String filename = "/home/admin1/IdeaProjects/AddressBook/src/main/resources/" + addressbookName + ".json";
        File folder = new File("/home/admin1/IdeaProjects/AddressBook/src/main/resources/");
        File[] listOfFiles = folder.listFiles();

        boolean isFound = false;
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                if (listOfFiles[i].compareTo(new File(filename)) == 0) {
                    isFound = true;
                }
            }
        }
        if (isFound) {
            AddressbookImplementation obj = new AddressbookImplementation();
            obj.readAddressBook(filename);
            return obj;
        } else {
            throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.ADDRESSBOOK_NOT_EXIST, "given name addressbook does not exist");
        }

    }

    public int closeAddressbook(AddressbookImplementation obj) throws AddressBookCustomException {
        if (obj instanceof AddressbookImplementation) {
            return 1;
        } else {
            throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.ADDRESSBOOK_NOT_EXIST, "no addressbook is open for close");
        }

    }

    public int saveAddresBook(AddressbookImplementation personObj) throws AddressBookCustomException, IOException {
        if (personObj instanceof AddressbookImplementation) {
            personObj.writeToJsonFile(personObj.getPersonList(), new File(personObj.getFileName()));
            return 1;
        } else {
            throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.DADA_NOT_SAVED, "data not saved");
        }

    }

    public int saveAsAddresBook(AddressbookImplementation personObj, String newFileName) throws IOException, AddressBookCustomException {
        if (personObj instanceof AddressbookImplementation) {
            String filename = "/home/admin1/IdeaProjects/AddressBook/src/main/resources/" + newFileName + ".json";
            File file = new File(filename);
            if (file.createNewFile()) {
                 personObj.writeToJsonFile(personObj.getPersonList(), file);
            }else {
                throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.UNABLE_TO_SAVEAS, "unable to saveAs");
            }
        }
        return 1;
    }

}
