/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.franciscocalaca.agenda;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 *
 * @author Francisco
 */
public class IdManager {
    
    private static final File FILE = new File("idgen.properties");
    
    public static int getProximoId(String arquivo){
        try{
            String key = "last" + arquivo;
            if(FILE.exists()){
                FileInputStream fis = new FileInputStream(FILE);
                Properties props = new Properties();
                props.load(fis);
                String idStr = props.getProperty(key);
                int id = 1;
                if(idStr != null){
                    id = Integer.valueOf(idStr);
                }
                fis.close();
                
                props.setProperty(key, String.valueOf(id + 1));
                FileOutputStream fos = new FileOutputStream(FILE);
                props.store(fos, "");
                fos.close();
                return id;
            }else{
                FileOutputStream fos = new FileOutputStream(FILE);
                Properties props = new Properties();
                props.setProperty(key, "1");
                props.store(fos, "");
                fos.close();
                return 1;
            }
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    
}
