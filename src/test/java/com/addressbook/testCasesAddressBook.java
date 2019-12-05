package com.addressbook;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class testCasesAddressBook {
    @Test
    public void givenPersonInformation_InProperFormat_ShouldReturnOne() throws IOException {
        AddressbookImplementation obj = new AddressbookImplementation();
        obj.readAddressBook("/home/admin1/IdeaProjects/AddressBook/src/main/resources/MH.json");
        int result=obj.addPerson("sangita","awaghad","Madhuban Colony,Old Jalna","Jalna","MH","431203","9422329006");
        Assert.assertEquals(1,result);
    }
}