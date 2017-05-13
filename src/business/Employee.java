/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import javax.swing.JTextField;

/**
 *
 * @author Keith
 */
public class Employee {

    private long empno, phone;
    private String lastnm, firstnm, middlenm, suffix;
    private String addr1, addr2, city, state, zip, gender;
    private String status, hiredt, terminateddt;
    private int paycd;

    public Employee() {
        this.empno = 0;
        phone = 0;
        lastnm = "";
        firstnm = "";
        middlenm = "";
        suffix = "";
        addr1 = "";
        addr2 = "";
        city = "";
        state = "";
        zip = "";
        gender = "";
        status = "";
        hiredt = "";
        terminateddt = "";
        paycd = 0;

    }

    /**
     * @return the empno
     */
    public long getEmpNo() {
        return empno;
    }

    /**
     * @param empno the empno to set
     */
    public void setEmpNo(Long empno) {
        this.empno = empno;
    }

    /**
     * @return the phone
     */
    public long getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(Long phone) {
        this.phone = phone;
    }

    /**
     * @return the lastnm
     */
    public String getLastNm() {
        return lastnm;
    }

    /**
     * @param lastnm the lastnm to set
     */
    public void setLastNm(String lastnm) {
        this.lastnm = lastnm;
    }

    /**
     * @return the firstnm
     */
    public String getFirstNm() {
        return firstnm;
    }

    /**
     * @param firstnm the firstnm to set
     */
    public void setFirstNm(String firstnm) {
        this.firstnm = firstnm;
    }

    /**
     * @return the middlenm
     */
    public String getMiddleNm() {
        return middlenm;
    }

    /**
     * @param middlenm the middlenm to set
     */
    public void setMiddleNm(String middlenm) {
        this.middlenm = middlenm;
    }

    /**
     * @return the suffix
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * @param suffix the suffix to set
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /**
     * @return the addr1
     */
    public String getAddr1() {
        return addr1;
    }

    /**
     * @param addr1 the addr1 to set
     */
    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    /**
     * @return the addr2
     */
    public String getAddr2() {
        return addr2;
    }

    /**
     * @param addr2 the addr2 to set
     */
    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the hiredt
     */
    public String getHireDt() {
        return hiredt;
    }

    /**
     * @param hiredt the hiredt to set
     */
    public void setHireDt(String hiredt) {
        this.hiredt = hiredt;
    }

    /**
     * @return the terminateddt
     */
    public String getTerminateDt() {
        return terminateddt;
    }

    /**
     * @param terminateddt the terminateddt to set
     */
    public void setTerminateDt(String terminateddt) {
        this.terminateddt = terminateddt;
    }

    /**
     * @return the paycd
     */
    public int getPayCd() {
        return paycd;
    }

    /**
     * @param paycd the paycd to set
     */
    public void setPayCd(Integer paycd) {
        this.paycd = paycd;
    }


    @Override
    public String toString() {

        int intValue = 0;
        long longValue = 0;
        String empString = "";
        Class empclass = this.getClass();
        Method[] methods = empclass.getDeclaredMethods();

        for (Method m : methods) {
            if (m.getName().startsWith("get")) {
                switch(m.getName()) {
                            case "getEmpNo" :
                                try {
                                    longValue = (Long) (m.invoke(this));
                                    empString += String.valueOf(longValue) + ", ";
                                } catch (Exception e) {
                                    
                                }                                    
                                break;
                            case "getPhone":
                                try {
                                    longValue = (Long) (m.invoke(this));
                                    empString += String.valueOf(longValue) + ", ";
                                } catch (Exception e) {
                                    
                                }
                                break;
                            case "getPayCd":
                                try{
                                    intValue = (int) (m.invoke(this));
                                    empString += String.valueOf(intValue) + ", ";
                                } catch(Exception e) {
                                    
                                }
                                break;
                            default:
                                try {
                                    String tempString = (String) m.invoke(this);
                                    empString += tempString + ", ";
                                } catch (Exception e) {
                                    
                                }
                                break;
                        } // end switch
            }
        }
        return empString;
    }
}
