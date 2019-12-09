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
            AddressbookImplementation addressbookImplementation = managementObj.openAddressbook("newUP");
            addressbookImplementation.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/newUP.json");
            int result = (Integer) addressbookImplementation.addPerson("Gita", "Awaghad", "Madhuban Colony,Old Jalna", "Jalna", "MH", "431203", "9422329006");
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
            AddressbookImplementation addressbookImplementation = managementObj.openAddressbook("newUP");
            addressbookImplementation.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/newUP.json");
            int result = addressbookImplementation.addPerson("s", "Awaghad", "Madhuban Colony,Old Jalna", "Jalna", "MH", "431203", "9422329006");
        } catch (AddressBookCustomException e) {
            Assert.assertEquals(AddressBookCustomException.ExceptionType.INVALID_NAME, e.type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenPersonInformation_WithCityDontFollowRules_ShouldThrowException() {

        try {
            AddresbookManagement managementObj = new AddresbookManagement();
            AddressbookImplementation addressbookImplementation = managementObj.openAddressbook("newUP");
            addressbookImplementation.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/newUP.json");
            int result = addressbookImplementation.addPerson("Sangita", "Awaghad", "Madhuban Colony,Old Jalna", "Jalna11", "MH", "431203", "9422329006");
        } catch (AddressBookCustomException e) {
            Assert.assertEquals(AddressBookCustomException.ExceptionType.INVALID_CITY, e.type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenPersonInformation_WithStateDontFollowRules_ShouldThrowException()  {
        try {
            AddresbookManagement managementObj = new AddresbookManagement();
            AddressbookImplementation addressbookImplementation = managementObj.openAddressbook("newUP");
            addressbookImplementation.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/newUP.json");

            int result = addressbookImplementation.addPerson("Sangita", "Awaghad", "Madhuban Colony,Old Jalna", "Jalna", "MH11", "431203", "9422329006");
        } catch (AddressBookCustomException e) {
            Assert.assertEquals(AddressBookCustomException.ExceptionType.INVALID_STATE, e.type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenPersonInformation_WithZipDontFollowRules_ShouldThrowException() {
        try {
            AddresbookManagement managementObj = new AddresbookManagement();
            AddressbookImplementation addressbookImplementation = managementObj.openAddressbook("newUP");

            addressbookImplementation.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/newUP.json");
            int result = addressbookImplementation.addPerson("Sangita", "Awaghad", "Madhuban Colony,Old Jalna", "Jalna", "MH", "43120311", "9422329006");
        } catch (AddressBookCustomException e) {
            Assert.assertEquals(AddressBookCustomException.ExceptionType.INVLAID_ZIP, e.type);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenPersonInformation_WithMobileNumberDontFollowRules_ShouldThrowException() {
        try {
            AddresbookManagement managementObj = new AddresbookManagement();
            AddressbookImplementation  addressbookImplementation = managementObj.openAddressbook("newUP");
            addressbookImplementation.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/newUP.json");
            int result = addressbookImplementation.addPerson("Sangita", "Awaghad", "Madhuban Colony,Old Jalna", "Jalna", "MH", "431203", "942232900611");
        } catch (AddressBookCustomException e) {
            Assert.assertEquals(AddressBookCustomException.ExceptionType.INVALID_MOBILENUMBER, e.type);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenPersonId_EditPersonMobileNumberSuccessfully_ShouldReturnOne() {
        try {
            AddresbookManagement managementObj = new AddresbookManagement();
            AddressbookImplementation addressbookImplementation = managementObj.openAddressbook("newUP");

            int result = addressbookImplementation.editMobileNumber("9422329006", "9011907937");
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
            AddressbookImplementation addressbookImplementation = managementObj.openAddressbook("newUP");

            int result = addressbookImplementation.editMobileNumber("1234567890", "9011907937");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AddressBookCustomException e) {
            Assert.assertEquals(AddressBookCustomException.ExceptionType.PERSON_NOT_FOUND, e.type);
        }

    }

    @Test
    public void givenPersonID_EditPersonAddressSuccessfully_ShouldReturnOne() {
        try {
            AddresbookManagement managementObj = new AddresbookManagement();
            AddressbookImplementation addressbookImplementation = managementObj.openAddressbook("newUP");
            int result = addressbookImplementation.editAddress("9422329006", "Vikas nagar,Kranti chowk", "Aurangabad", "MH", "431205");
            Assert.assertEquals(1, result);
        } catch (AddressBookCustomException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenPersonId_EditPersonAddress_ButPersonIdDoesNotExist_ShouldReturnOne(){
        try {
            AddresbookManagement managementObj = new AddresbookManagement();
            AddressbookImplementation addressbookImplementation = managementObj.openAddressbook("newUP");
            int result = addressbookImplementation.editAddress("1234567890", "Vikas nagar,Kranti chowk", "Aurangabad", "MH", "431205");
        } catch (AddressBookCustomException e) {
            Assert.assertEquals(AddressBookCustomException.ExceptionType.PERSON_NOT_FOUND, e.type);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenPersonID_DeletePersonFromAddressbookSuccessfully_ShouldReturnOne() {
        try {
            AddresbookManagement managementObj = new AddresbookManagement();
            AddressbookImplementation implementationObj = implementationObj = managementObj.openAddressbook("newUP");
            AddressbookImplementation addressbookImplementation = (AddressbookImplementation) implementationObj;
            int result = addressbookImplementation.deletePersonFromAddressbook("9422329006");
            Assert.assertEquals(1, result);
        } catch (AddressBookCustomException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenPersonId_DeletePersonFromAddressbook_ButPersonIdDoesNotExist_ShouldReturnOne() {
        try {
            AddresbookManagement managementObj = new AddresbookManagement();
            AddressbookImplementation addressbookImplementation = managementObj.openAddressbook("newUP");
            int result = addressbookImplementation.deletePersonFromAddressbook("1234567890");
        } catch (AddressBookCustomException e) {
            Assert.assertEquals(AddressBookCustomException.ExceptionType.PERSON_NOT_FOUND, e.type);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sortAddressbook_ByLastname_ShouldReturnExpectedValue(){
        try {
            AddresbookManagement managementObj = new AddresbookManagement();
            AddressbookImplementation addressbookImplementation = managementObj.openAddressbook("MH");
            List<Person> personList = addressbookImplementation.sortByLastname();
            Assert.assertEquals("Awaghad", personList.get(0).getLastName());
            addressbookImplementation.sortByPersonId();
        } catch (AddressBookCustomException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sortAddressbook_ByZip_ShouldReturnExpectedValue() {
        try {
            AddresbookManagement managementObj = new AddresbookManagement();
            AddressbookImplementation addressbookImplementation = managementObj.openAddressbook("newUP");

            List<Person> personList = addressbookImplementation.sortByZip();
            Assert.assertEquals(431203, personList.get(0).getAddress().getZip());
            addressbookImplementation.sortByPersonId();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AddressBookCustomException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createdAddressBookSuccssfully_ShouldReturnOne() {
        try {
            AddresbookManagement addresbookManagement = new AddresbookManagement();
            int result = addresbookManagement.createAddressbook("UP");
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
            AddresbookManagement addresbookManagement = new AddresbookManagement();
            int result = addresbookManagement.createAddressbook("UP");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AddressBookCustomException e) {
            Assert.assertEquals(AddressBookCustomException.ExceptionType.ADDRESSBOOK_ALREADY_EXIST, e.type);
        }
    }

    @Test
    public void openAddressBookSuccessfully_ShouldReturnAddressbookImplementationClassObject() {

        try {
            AddresbookManagement addresbookManagement = new AddresbookManagement();
            AddressbookImplementation obj = addresbookManagement.openAddressbook("NewUP");
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
            AddresbookManagement addresbookManagement = new AddresbookManagement();
            AddressbookImplementation result = addresbookManagement.openAddressbook("AP");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AddressBookCustomException e) {
            Assert.assertEquals(AddressBookCustomException.ExceptionType.ADDRESSBOOK_NOT_EXIST, e.type);
        }
    }

    @Test
    public void closeAddressBook_ifSuccessfully_ShouldReturnOne() {
        try {
            AddresbookManagement addresbookManagement = new AddresbookManagement();
            AddressbookImplementation addressbookImplementation = addresbookManagement.openAddressbook("NewUP");
            int result = addresbookManagement.closeAddressbook(addressbookImplementation);
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
            AddresbookManagement addresbookManagement = new AddresbookManagement();
            AddressbookImplementation addressbookImplementation = addresbookManagement.openAddressbook("AP");
            Object result = addresbookManagement.closeAddressbook(addressbookImplementation);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (AddressBookCustomException e) {
            Assert.assertEquals(AddressBookCustomException.ExceptionType.ADDRESS_NOT_OPEN, e.type);
        }
    }

    @Test
    public void saveAddressBook_ifSavedSuccessfully_ShouldReturnOne() {
        try {
            AddresbookManagement addresbookManagement = new AddresbookManagement();
            AddressbookImplementation addressbookImplementation = addresbookManagement.openAddressbook("MH");
            int result = addresbookManagement.saveAddresBook(addressbookImplementation);
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
            AddresbookManagement addresbookManagement = new AddresbookManagement();
            AddressbookImplementation addressbookImplementation = addresbookManagement.openAddressbook("AP");
            Object result = addresbookManagement.saveAddresBook(addressbookImplementation);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AddressBookCustomException e) {
            Assert.assertEquals(AddressBookCustomException.ExceptionType.ADDRESSBOOK_NOT_EXIST, e.type);
        }
    }

    @Test
    public void saveAsAddressBook_ifSaveAsSuccessfully_ShouldReturnOne() {
        try {
            AddresbookManagement addresbookManagement = new AddresbookManagement();
            AddressbookImplementation addressbookImplementation = addresbookManagement.openAddressbook("newUP");
            int result = addresbookManagement.saveAsAddresBook(addressbookImplementation, "NNEWUP");
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
            AddresbookManagement addresbookManagement = new AddresbookManagement();
            AddressbookImplementation addressbookImplementation = addresbookManagement.openAddressbook("NewUP");
            Object result = addresbookManagement.saveAsAddresBook(addressbookImplementation, "newAP");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AddressBookCustomException e) {
            Assert.assertEquals(AddressBookCustomException.ExceptionType.UNABLE_TO_SAVEAS, e.type);
        }
    }

}