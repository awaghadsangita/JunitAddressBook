package com.addressbook;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

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
}
