/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author Keith
 */
public class EmpIO {

    public static Map<Long, Employee> getEmps(String path) {

        Map<Long, Employee> empList = new HashMap<>();

        try {
            BufferedReader in = new BufferedReader(new FileReader(path));

            in.readLine(); // Read one line (line 1) to ignore headings
            String empstr = in.readLine();
            while (empstr != null) {
                String[] edata = empstr.split(","); // Contains all employee data
                Employee e = new Employee();
                Long empno = Long.parseLong(edata[0]);
                e.setEmpNo(empno);
                if (!edata[1].isEmpty()) {
                    e.setFirstNm(edata[1]);
                }
                if (!edata[2].isEmpty()) {
                    e.setLastNm(edata[2]);
                }
                if (!edata[3].isEmpty()) {
                    e.setMiddleNm(edata[3]);
                }
                if (!edata[4].isEmpty()) {
                    e.setSuffix(edata[4]);
                }
                if (!edata[5].isEmpty()) {
                    e.setAddr1(edata[5]);
                }
                if (!edata[6].isEmpty()) {
                    e.setAddr2(edata[6]);
                }
                if (!edata[7].isEmpty()) {
                    e.setCity(edata[7]);
                }
                if (!edata[8].isEmpty()) {
                    e.setState(edata[8]);
                }
                if (!edata[9].isEmpty()) {
                    e.setZip(edata[9]);
                }
                if (!edata[10].isEmpty()) {
                    e.setPhone(Long.parseLong(edata[10]));
                }
                if (!edata[11].isEmpty()) {
                    e.setGender(edata[11]);
                }
                if (!edata[12].isEmpty()) {
                    e.setStatus(edata[12]);
                }
                if (!edata[13].isEmpty()) {
                    e.setHireDt(edata[13]);
                }
                if (!edata[14].isEmpty()) {
                    e.setTerminateDt(edata[14]);
                }
                if (!edata[15].isEmpty()) {
                    e.setPayCd(Integer.parseInt(edata[15]));
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

    public static String setEmps(String absolutePath, Map<Long, Employee> emps) {

        String msg = "";
        int empsout = 0;

        // employee object manages its own data lock
        // Empno, Firstname, Lastname, MiddleInit, Suffix, Address1, Address2, City, State, Zip, Phone, Gender, Status, HireDate, TerminateDt, PayCd
        try {
            PrintWriter out = new PrintWriter(new FileWriter(absolutePath));
            Iterator<Map.Entry<Long, Employee>> it = emps.entrySet().iterator();
            out.println("Empno, Firstname, Lastname, MiddleInit, Suffix, Address1, Address2, City, State, Zip, Phone, Gender, Status, HireDate, TerminateDt, PayCd");
            while (it.hasNext()) {
                Map.Entry<Long, Employee> entry = it.next();
                out.println(entry.getValue().toString());
                empsout++;
            }
            out.close();
            msg = "Employees saved to CSV = " + empsout;
            // must output header line
        } catch (Exception e) {
            msg = "Save Error: " + e.getMessage();
        }
        return msg;
    }
    
    
    public static String setEmpsXML(String path, Map<Long, Employee> emps) {
        int countEmpsXML = 0;
        String msg = "";
        int v1 = 0;
        long v2 = 0;
        String val = "";
        
        try{
            Iterator<Map.Entry<Long, Employee>> it = emps.entrySet().iterator();
            
            XMLOutputFactory outFactory = XMLOutputFactory.newFactory();
            FileWriter filewriter = new FileWriter(path);
            XMLStreamWriter writer = outFactory.createXMLStreamWriter(filewriter);
            writer.writeStartDocument("1.0");
            writer.writeStartElement("Employees");
            
            while(it.hasNext()) {
                Map.Entry<Long, Employee> empentry = it.next();
                Employee emp = empentry.getValue();
                Class empclass = emp.getClass();
                
                writer.writeStartElement("Employee");
                writer.writeAttribute("Empno", String.valueOf(emp.getEmpNo()));
                Method[] methods = empclass.getDeclaredMethods();
                for(Method m : methods) {
                    if(m.getName().startsWith("get")) {
                        switch(m.getName()) {
                            case "getEmpNo" :
                                // no action - empno is an attribute
                                break;
                            case "getPhone":
                                try {
                                    v2 = (Long) (m.invoke(emp));
                                    val = String.valueOf(v2);
                                } catch (Exception e) {
                                    val = "";
                                }
                                break;
                            case "getPayCd":
                                try{
                                    v1 = (int) (m.invoke(emp));
                                    val = String.valueOf(v1);
                                } catch(Exception e) {
                                    val = "";
                                }
                                break;
                            default:
                                try {
                                    val = (String) m.invoke(emp);
                                } catch (Exception e) {
                                    val = "";
                                }
                                break;
                        } // end switch
                        // skip employee number as it was included already
                        if(!m.getName().equals("getEmpNo")) {
                            writer.writeStartElement(m.getName().substring(3));
                            writer.writeCharacters(val);
                            writer.writeEndElement();
                        } // end writeElement
                    } // end if for get method
                } // end for loop
                writer.writeEndElement(); // end Employee
                countEmpsXML++;

            } // end while loop
            writer.writeEndElement(); // end of Employees
            writer.flush();
            writer.close();
            msg = countEmpsXML + " employees written.";
        } catch(Exception e) {
            msg = "XML save error: " + e.getMessage();
        }
        return msg;
    }
    
    public static Map<Long, Employee> getEmpsXML (String path) {
        
        Map<Long, Employee> emps = new HashMap<>();
        Employee emp = null;
        Class<?> empclass;
        Method m;
        long empno = 0;
        String elementName;       
        
        try {
            FileReader flr = new FileReader(path);
            XMLInputFactory inFactory = XMLInputFactory.newFactory();
            XMLStreamReader reader = inFactory.createXMLStreamReader(flr);
            
            while(reader.hasNext()) {
                int eventType = reader.getEventType();
                switch (eventType) {
                    case XMLStreamConstants.START_ELEMENT:
                        elementName=reader.getLocalName();
                        if(!elementName.equals("Employees")) {
                            if(elementName.equals("Employee")) {
                                // the beginning of a new employee
                                emp = new Employee();
                                empno = Long.parseLong(reader.getAttributeValue(0));
                                emp.setEmpNo(empno);
                            } else {
                                empclass = emp.getClass();
                                try {
                                    String setMethod = "set" + elementName;
                                    switch(setMethod) {
                                        case "setPhone":
                                            m = empclass.getMethod(setMethod, Long.class);
                                            m.invoke(emp, Long.parseLong(reader.getElementText()));
                                            break;
                                        case "setPayCd":
                                            m = empclass.getMethod(setMethod, Integer.class);
                                            m.invoke(emp, Integer.parseInt(reader.getElementText()));
                                            break; 
                                        default:
                                            m = empclass.getMethod(setMethod, String.class);
                                            m.invoke(emp, reader.getElementText());
                                            break;
                                    } // end of switch                           
                                } catch (Exception e) {
                                    // do nothing, no update to emp                                    
                                } // end catch
                            } // end else for non-Employees plural
                        } // end if for !=Employees
                        break; // end of start element case
                    case XMLStreamConstants.END_ELEMENT:
                        elementName = reader.getLocalName();
                        if(elementName.equals("Employee")) {
                            // finished an employee
                            if(emp != null) {
                                emps.put(emp.getEmpNo(), emp);
                            }
                        }
                        break;
                } // end outer switch for event type
                reader.next();
            } // end while
            reader.close();
        } catch (Exception e) {
            emps = null;
        }
        return emps;
    }
}
