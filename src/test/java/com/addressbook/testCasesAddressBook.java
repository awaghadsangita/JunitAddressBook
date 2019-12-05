package com.addressbook;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class testCasesAddressBook {
    @Test
    public void givenPersonInformation_InProperFormat_ShouldReturnOne() throws Exception {
        AddressbookImplementation obj = new AddressbookImplementation();
        obj.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/MH.json");
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
}