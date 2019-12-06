package com.addressbook;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class testCasesAddressBook {
    @Test
    public void givenPersonInformation_InProperFormat_ShouldReturnOne() {
        try {
            AddresbookManagement managementObj = new AddresbookManagement();
            AddressbookImplementation obj = managementObj.openAddressbook("newUP");
            obj.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/newUP.json");
            int result = (Integer) obj.addPerson("Gita", "Awaghad", "Madhuban Colony,Old Jalna", "Jalna", "MH", "431203", "9422329006");
            Assert.assertEquals(1, result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AddressBookCustomException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenPersonInformation_WithFirstNameDontFollowRules_ShouldThrowException() {
        try {
            AddresbookManagement managementObj = new AddresbookManagement();
            AddressbookImplementation obj = managementObj.openAddressbook("newUP");
            obj.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/newUP.json");
            int result = obj.addPerson("s", "Awaghad", "Madhuban Colony,Old Jalna", "Jalna", "MH", "431203", "9422329006");
        } catch (AddressBookCustomException e) {
            Assert.assertEquals(AddressBookCustomException.ExceptionType.INVALID_NAME, e.type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenPersonInformation_WithCityDontFollowRules_ShouldThrowException() throws IOException {

        try {
            AddresbookManagement managementObj = new AddresbookManagement();
            AddressbookImplementation obj = managementObj.openAddressbook("newUP");
            obj.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/newUP.json");
            int result = obj.addPerson("Sangita", "Awaghad", "Madhuban Colony,Old Jalna", "Jalna11", "MH", "431203", "9422329006");
        } catch (AddressBookCustomException e) {
            Assert.assertEquals(AddressBookCustomException.ExceptionType.INVALID_CITY, e.type);
        }
    }

    @Test
    public void givenPersonInformation_WithStateDontFollowRules_ShouldThrowException() throws IOException {
        try {
            AddresbookManagement managementObj = new AddresbookManagement();
            AddressbookImplementation obj = managementObj.openAddressbook("newUP");
            obj.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/newUP.json");

            int result = obj.addPerson("Sangita", "Awaghad", "Madhuban Colony,Old Jalna", "Jalna", "MH11", "431203", "9422329006");
        } catch (AddressBookCustomException e) {
            Assert.assertEquals(AddressBookCustomException.ExceptionType.INVALID_STATE, e.type);
        }
    }

    @Test
    public void givenPersonInformation_WithZipDontFollowRules_ShouldThrowException() throws IOException {
        try {
            AddresbookManagement managementObj = new AddresbookManagement();
            AddressbookImplementation obj = managementObj.openAddressbook("newUP");
            obj.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/newUP.json");
            int result = obj.addPerson("Sangita", "Awaghad", "Madhuban Colony,Old Jalna", "Jalna", "MH", "43120311", "9422329006");
        } catch (AddressBookCustomException e) {
            Assert.assertEquals(AddressBookCustomException.ExceptionType.INVLAID_ZIP, e.type);
        }
    }

    @Test
    public void givenPersonInformation_WithMobileNumberDontFollowRules_ShouldThrowException() throws IOException {
        try {
            AddresbookManagement managementObj = new AddresbookManagement();
            AddressbookImplementation obj = managementObj.openAddressbook("newUP");
            obj.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/newUP.json");
            int result = obj.addPerson("Sangita", "Awaghad", "Madhuban Colony,Old Jalna", "Jalna", "MH", "431203", "942232900611");
        } catch (AddressBookCustomException e) {
            Assert.assertEquals(AddressBookCustomException.ExceptionType.INVALID_MOBILENUMBER, e.type);
        }
    }

    @Test
    public void givenPersonId_EditPersonMobileNumberSuccessfully_ShouldReturnOne() {
        try {
            AddresbookManagement managementObj = new AddresbookManagement();
            AddressbookImplementation obj = managementObj.openAddressbook("newUP");

            int result = obj.editMobileNumber("9422329006", "9011907937");
            Assert.assertEquals(1, result);
        } catch (AddressBookCustomException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenPersonId_EditPersonMobileNumber_ButPersonIdDoesNotExist_ShouldReturnOne() {
        try {
            AddresbookManagement managementObj = new AddresbookManagement();
            AddressbookImplementation obj = managementObj.openAddressbook("newUP");

            int result = obj.editMobileNumber("1234567890", "9011907937");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AddressBookCustomException e) {
            Assert.assertEquals(AddressBookCustomException.ExceptionType.PERSON_NOT_FOUND, e.type);
        }

    }

    @Test
    public void givenPersonID_EditPersonAddressSuccessfully_ShouldReturnOne() throws IOException {
        try {
            AddresbookManagement managementObj = new AddresbookManagement();
            AddressbookImplementation obj = managementObj.openAddressbook("newUP");

            int result = obj.editAddress("9422329006", "Vikas nagar,Kranti chowk", "Aurangabad", "MH", "431205");
            Assert.assertEquals(1, result);
        } catch (AddressBookCustomException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenPersonId_EditPersonAddress_ButPersonIdDoesNotExist_ShouldReturnOne() throws IOException {
        try {
            AddresbookManagement managementObj = new AddresbookManagement();
            AddressbookImplementation obj = managementObj.openAddressbook("newUP");

            int result = obj.editAddress("1234567890", "Vikas nagar,Kranti chowk", "Aurangabad", "MH", "431205");
        } catch (AddressBookCustomException e) {
            Assert.assertEquals(AddressBookCustomException.ExceptionType.PERSON_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenPersonID_DeletePersonFromAddressbookSuccessfully_ShouldReturnOne() throws IOException {
        try {
            AddresbookManagement managementObj = new AddresbookManagement();
            Object implementationObj = managementObj.openAddressbook("newUP");
            AddressbookImplementation obj = (AddressbookImplementation) implementationObj;

            int result = obj.deletePersonFromAddressbook("9422329006");
            Assert.assertEquals(1, result);
        } catch (AddressBookCustomException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenPersonId_DeletePersonFromAddressbook_ButPersonIdDoesNotExist_ShouldReturnOne() throws IOException {
        try {
            AddresbookManagement managementObj = new AddresbookManagement();
            AddressbookImplementation obj = managementObj.openAddressbook("newUP");

            int result = obj.deletePersonFromAddressbook("1234567890");
        } catch (AddressBookCustomException e) {
            Assert.assertEquals(AddressBookCustomException.ExceptionType.PERSON_NOT_FOUND, e.type);
        }
    }

    @Test
    public void sortAddressbook_ByLastname_ShouldReturnExpectedValue() throws IOException {
        try {
            AddresbookManagement managementObj = new AddresbookManagement();
            AddressbookImplementation obj = managementObj.openAddressbook("MH");

            List<Person> personList = obj.sortByLastname();
            Assert.assertEquals("Awaghad", personList.get(0).getLastName());
            obj.sortByPersonId();
        } catch (AddressBookCustomException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sortAddressbook_ByZip_ShouldReturnExpectedValue() {
        try {
            AddresbookManagement managementObj = new AddresbookManagement();
            AddressbookImplementation obj = managementObj.openAddressbook("newUP");

            List<Person> personList = obj.sortByZip();
            Assert.assertEquals(431203, personList.get(0).getAddress().getZip());
            obj.sortByPersonId();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AddressBookCustomException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createdAddressBookSuccssfully_ShouldReturnOne() {
        try {
            AddresbookManagement obj = new AddresbookManagement();
            int result = obj.createAddressbook("UP");
            Assert.assertEquals(1, result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AddressBookCustomException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createdAddressBook_ifAlreadyExist_ShouldThrowException() {
        try {
            AddresbookManagement obj = new AddresbookManagement();
            int result = obj.createAddressbook("UP");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AddressBookCustomException e) {
            Assert.assertEquals(AddressBookCustomException.ExceptionType.ADDRESSBOOK_ALREADY_EXIST, e.type);
        }
    }

    @Test
    public void openAddressBookSuccessfully_ShouldReturnAddressbookImplementationClassObject() {

        try {
            AddresbookManagement mgntObj = new AddresbookManagement();
            AddressbookImplementation obj = mgntObj.openAddressbook("NewUP");
            Assert.assertTrue(obj instanceof AddressbookImplementation);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AddressBookCustomException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void openAddressBook_AddressbookNameDoesNotExist_ShouldReturnOne() {
        try {
            AddresbookManagement obj = new AddresbookManagement();
            AddressbookImplementation result = obj.openAddressbook("AP");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AddressBookCustomException e) {
            Assert.assertEquals(AddressBookCustomException.ExceptionType.ADDRESSBOOK_NOT_EXIST, e.type);
        }
    }

    @Test
    public void closeAddressBook_ifSuccessfully_ShouldReturnOne() {
        try {
            AddresbookManagement obj = new AddresbookManagement();
            AddressbookImplementation implObj = obj.openAddressbook("NewUP");
            int result = obj.closeAddressbook(implObj);
            Assert.assertEquals(1, result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AddressBookCustomException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void closeAddressBook_NoAddressbookIsOpen_ShouldThrowException() {
        try {
            AddresbookManagement obj = new AddresbookManagement();
            AddressbookImplementation personObj = obj.openAddressbook("AP");
            Object result = obj.closeAddressbook(personObj);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (AddressBookCustomException e) {
            Assert.assertEquals(AddressBookCustomException.ExceptionType.ADDRESS_NOT_OPEN, e.type);
        }
    }

    @Test
    public void saveAddressBook_ifSavedSuccessfully_ShouldReturnOne() {
        try {
            AddresbookManagement obj = new AddresbookManagement();
            AddressbookImplementation personObj = obj.openAddressbook("MH");
            int result = obj.saveAddresBook(personObj);
            Assert.assertEquals(1, result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AddressBookCustomException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveAddressBook_dataNotSaved_ShouldThrowException() {
        try {
            AddresbookManagement obj = new AddresbookManagement();
            AddressbookImplementation personObj = obj.openAddressbook("AP");
            Object result = obj.saveAddresBook(personObj);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AddressBookCustomException e) {
            Assert.assertEquals(AddressBookCustomException.ExceptionType.ADDRESSBOOK_NOT_EXIST, e.type);
        }
    }

    @Test
    public void saveAsAddressBook_ifSaveAsSuccessfully_ShouldReturnOne() {
        try {
            AddresbookManagement obj = new AddresbookManagement();
            AddressbookImplementation personObj = obj.openAddressbook("newUP");
            int result = obj.saveAsAddresBook(personObj, "NNEWUP");
            Assert.assertEquals(1, result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AddressBookCustomException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveAsAddressBook_ifUnableToSaveAs_ShouldThrowException() {
        try {
            AddresbookManagement obj = new AddresbookManagement();
            AddressbookImplementation personObj = obj.openAddressbook("NewUP");
            Object result = obj.saveAsAddresBook(personObj, "newAP");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AddressBookCustomException e) {
            Assert.assertEquals(AddressBookCustomException.ExceptionType.UNABLE_TO_SAVEAS, e.type);
        }
    }

}