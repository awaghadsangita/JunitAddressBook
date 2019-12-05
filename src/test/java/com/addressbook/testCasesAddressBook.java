package com.addressbook;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class testCasesAddressBook {
    @Test
    public void givenPersonInformation_InProperFormat_ShouldReturnOne() throws Exception {
        AddressbookImplementation obj = new AddressbookImplementation();
        obj.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/UP.json");
        int result = (Integer) obj.addPerson("Sangita", "Awaghad", "Madhuban Colony,Old Jalna", "Jalna", "MH", "431203", "9422329006");
        Assert.assertEquals(1, result);
    }
    @Test
    public void givenPersonInformation_WithFirstNameDontFollowRules_ShouldThrowException() throws Exception {
        AddressbookImplementation obj = new AddressbookImplementation();
        Object result = null;
        obj.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/MH.json");
        result = obj.addPerson("s", "Awaghad", "Madhuban Colony,Old Jalna", "Jalna", "MH", "431203", "9422329006");
        Assert.assertEquals("java.lang.Exception: firstname must start with Uppercase and contain minimum two letters", result.toString());
    }
    @Test
    public void givenPersonInformation_WithCityDontFollowRules_ShouldThrowException() throws Exception {
        AddressbookImplementation obj = new AddressbookImplementation();
        obj.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/MH.json");
        Object result = obj.addPerson("Sangita", "Awaghad", "Madhuban Colony,Old Jalna", "Jalna11", "MH", "431203", "9422329006");
        Assert.assertEquals("java.lang.Exception: city must be minimum two letters long and Should contain letters only", result.toString());
    }
    @Test
    public void givenPersonInformation_WithStateDontFollowRules_ShouldThrowException() throws Exception {
        AddressbookImplementation obj = new AddressbookImplementation();
        obj.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/MH.json");
        Object result = obj.addPerson("Sangita", "Awaghad", "Madhuban Colony,Old Jalna", "Jalna", "MH11", "431203", "9422329006");
        Assert.assertEquals("java.lang.Exception: state must be minimum two letters long and Should contain letters only", result.toString());
    }
    @Test
    public void givenPersonInformation_WithZipDontFollowRules_ShouldThrowException() throws Exception {
        AddressbookImplementation obj = new AddressbookImplementation();
        obj.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/MH.json");
        Object result = obj.addPerson("Sangita", "Awaghad", "Madhuban Colony,Old Jalna", "Jalna", "MH", "43120311", "9422329006");
        Assert.assertEquals("java.lang.Exception: zip must be 6 digit long", result.toString());
    }
    @Test
    public void givenPersonInformation_WithMobileNumberDontFollowRules_ShouldThrowException() throws Exception {
        AddressbookImplementation obj = new AddressbookImplementation();
        obj.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/MH.json");
        Object result = obj.addPerson("Sangita", "Awaghad", "Madhuban Colony,Old Jalna", "Jalna", "MH", "431203", "942232900611");
        Assert.assertEquals("java.lang.Exception: mobile number must be 10 digit long", result.toString());
    }

    @Test
    public void givenPersonId_FindPersonRecordSuccessfully_ShouldReturnPersonListIndex() throws IOException {
        AddressbookImplementation obj = new AddressbookImplementation();
        obj.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/MH.json");
        int result=obj.findPersonRecord(1);
        Assert.assertNotEquals(-1,result);
    }
    @Test
    public void givenPersonId_NoTFindPersonRecordSuccessfully_ShouldReturnMinusOne() throws IOException {
        AddressbookImplementation obj = new AddressbookImplementation();
        obj.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/MH.json");
        int result=obj.findPersonRecord(1000);
        Assert.assertEquals(-1,result);
    }

    @Test
    public void givenPersonId_EditPersonMobileNumberSuccessfully_ShouldReturnOne() throws Exception {
        AddressbookImplementation obj = new AddressbookImplementation();
        obj.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/MH.json");
        int listIndex=obj.findPersonRecord(1);
        int result = (Integer)obj.editMobileNumber(listIndex, "9011907937");
        Assert.assertEquals(1,result);
    }
    @Test
    public void givenPersonId_EditPersonMobileNumber_ButPersonIdDoesNotExist_ShouldReturnOne() throws Exception {
        AddressbookImplementation obj = new AddressbookImplementation();
        obj.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/MH.json");
        int listIndex=obj.findPersonRecord(10000);
        Object result = obj.editMobileNumber(listIndex, "9011907937");
        Assert.assertEquals("java.lang.Exception: person addressbook id does not exit", result.toString());
    }

    @Test
    public void givenPersonID_EditPersonAddressSuccessfully_ShouldReturnOne() throws IOException {
        AddressbookImplementation obj = new AddressbookImplementation();
        obj.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/MH.json");

        int result = (Integer)obj.editAddress(1, "Vikas nagar,Kranti chowk","Aurangabad","MH","431205");
        Assert.assertEquals(1,result);
    }
    @Test
    public void givenPersonId_EditPersonAddress_ButPersonIdDoesNotExist_ShouldReturnOne() throws Exception {
        AddressbookImplementation obj = new AddressbookImplementation();
        obj.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/MH.json");
        Object result = obj.editMobileNumber(10000, "9011907937");
        Assert.assertEquals("java.lang.Exception: person addressbook id does not exit", result.toString());
    }

    @Test
    public void givenPersonID_DeletePersonFromAddressbookSuccessfully_ShouldReturnOne() throws IOException {
        AddressbookImplementation obj = new AddressbookImplementation();
        obj.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/MH.json");
        int result = (Integer)obj.deletePersonFromAddressbook(1);
        Assert.assertEquals(1,result);
    }
    @Test
    public void givenPersonId_DeletePersonFromAddressbook_ButPersonIdDoesNotExist_ShouldReturnOne() throws Exception {
        AddressbookImplementation obj = new AddressbookImplementation();
        obj.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/MH.json");
        Object result = obj.deletePersonFromAddressbook(1000);
        Assert.assertEquals("java.lang.Exception: person addressbook id does not exit", result.toString());
    }

    @Test
    public void sortAddressbook_ByLastname_ShouldReturnExpectedValue() throws Exception {
        AddressbookImplementation obj = new AddressbookImplementation();
        obj.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/MH.json");
        List<Person> personList = obj.sortByLastname();
        Assert.assertEquals("Giri",personList.get(0).getLastName());
        obj.sortByPersonId();
    }

    @Test
    public void sortAddressbook_ByZip_ShouldReturnExpectedValue() throws Exception {
        AddressbookImplementation obj = new AddressbookImplementation();
        obj.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/MH.json");
        List<Person> personList = obj.sortByZip();
        Assert.assertEquals(431203,personList.get(0).getAddress().getZip());
        obj.sortByPersonId();
    }

    @Test
    public void createdAddressBookSuccssfully_ShouldReturnOne() {
        AddresbookManagement obj = new AddresbookManagement();
        int result=(Integer)obj.createAddressbook("UP");
        Assert.assertEquals(1,result);
    }
    @Test
    public void createdAddressBook_ifAlreadyExist_ShouldThrowException() {
        AddresbookManagement obj = new AddresbookManagement();
        Object result=obj.createAddressbook("UP");
        Assert.assertEquals("java.lang.Exception: given name addressbook already exist", result.toString());
    }

    @Test
    public void openAddressBookSuccessfully_ShouldReturnOne() {
        AddresbookManagement obj = new AddresbookManagement();
        int result=(Integer)obj.openAddressbook("UP");
        Assert.assertEquals(1,result);
    }
    @Test
    public void openAddressBook_AddressbookNameDoesNotExist_ShouldReturnOne() {
        AddresbookManagement obj = new AddresbookManagement();
        Object result=obj.openAddressbook("AP");
        Assert.assertEquals("java.lang.Exception: given name addressbook does not exist", result.toString());
    }
}