package com.addressbook;

import java.io.File;
import java.io.FileWriter;


public class AddresbookManagement {
    public Object createAddressbook(String addressbookName) {
        try{
            String filename="/home/admin1/IdeaProjects/AddressBook/src/main/resources/"+addressbookName+".json";
            File file =new File(filename);
            if(file.createNewFile()){
                FileWriter fw=new FileWriter(filename);
                String object="[{"+'"'+"personId"+'"'+":0}]";
                fw.write(object);
                fw.close();
                return 1;
            }else{
                throw new Exception("given name addressbook already exist");
            }
        }catch(Exception e){
            return e;
        }
    }

    public Object openAddressbook(String addressbookName) {
        try {
            String filename = "/home/admin1/IdeaProjects/AddressBook/src/main/resources/" + addressbookName + ".json";
            File folder = new File("/home/admin1/IdeaProjects/AddressBook/src/main/resources/");
            File[] listOfFiles = folder.listFiles();

            boolean isFound = false;
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    if (listOfFiles[i].compareTo(new File(filename))==0) {
                        isFound = true;
                    }
                }
            }
            if (isFound) {
                AddressbookImplementation obj = new AddressbookImplementation();
                obj.readAddressBook(filename);
                return 1;
            } else {
                throw new Exception("given name addressbook does not exist");
            }

        } catch (Exception e) {
            return e;
        }
    }
}
