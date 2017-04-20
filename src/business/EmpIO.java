/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Keith
 */
public class empIO {
    
    public static Map<Long, Employee> getEmps(String path) {
        
        Map<Long, Employee> empList = new HashMap<>();
        
        try{
            BufferedReader in = new BufferedReader(new FileReader(path));
            
            in.readLine(); // Read one line (line 1) to ignore headings
            String empstr = in.readLine();
            while(empstr != null) {
                String[] edata = empstr.split(","); // Contains all employee data
                Employee e = new Employee();
                Long empno = Long.parseLong(edata[0]);
                e.setEmpno(empno);
                if(!edata[1].isEmpty()) {
                    e.setFirstnm(edata[1]);              
                }
                                if(!edata[2].isEmpty()) {
                    e.setLastnm(edata[2]);              
                }
                if(!edata[3].isEmpty()) {
                    e.setMiddlenm(edata[3]);              
                }          
                                if(!edata[4].isEmpty()) {
                    e.setSuffix(edata[4]);              
                }
                if(!edata[5].isEmpty()) {
                    e.setAddr1(edata[5]);              
                }  
if(!edata[6].isEmpty()) {
                    e.setAddr2(edata[6]);              
                }     
if(!edata[7].isEmpty()) {
                    e.setCity(edata[7]);              
                }  
if(!edata[8].isEmpty()) {
                    e.setState(edata[8]);              
                }  
if(!edata[9].isEmpty()) {
                    e.setZip(edata[9]);              
                }  
if(!edata[10].isEmpty()) {
                    e.setPhone(Long.parseLong(edata[10]));              
                }  
if(!edata[11].isEmpty()) {
                    e.setGender(edata[11]);              
                }  
if(!edata[12].isEmpty()) {
                    e.setStatus(edata[12]);              
                }  
if(!edata[13].isEmpty()) {
                    e.setHiredt(edata[13]);              
                }  
if(!edata[14].isEmpty()) {
                    e.setTerminateddt(edata[14]);              
                }  
if(!edata[15].isEmpty()) {
                    e.setPaycd(Integer.parseInt(edata[15]));              
                }  
            empList.put(empno, e);
            empstr = in.readLine();
            }
            in.close();
        } catch (IOException e) {
            // no action, just return the HashMap as it is, could send it up the chain to the calling method
        }
        return empList;
    }
}
