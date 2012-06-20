/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gruner.ical.icalconverter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ladmin
 */
public class FormatedDate extends Date{

    Date innerDate;

    public void setTime(long time) {
        innerDate.setTime(time);
    }

    public int hashCode() {
        return innerDate.hashCode();
    }

    public long getTime() {
        return innerDate.getTime();
    }

    public boolean equals(Object obj) {
        return innerDate.equals(obj);
    }

    public int compareTo(Date anotherDate) {
        return innerDate.compareTo(anotherDate);
    }

    public Object clone() {
        return innerDate.clone();
    }

    public boolean before(Date when) {
        return innerDate.before(when);
    }

    public boolean after(Date when) {
        return innerDate.after(when);
    }
    
    @Override
    public String toString() {
        //DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return innerDate.toString();
    }

    public FormatedDate(Date date)
    {
        this.innerDate = date;
    }

    public FormatedDate(String date) throws Exception
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        
        innerDate = sdf.parse(date);
        
    }
    
    public String toLocaleString() {
        return innerDate.toLocaleString();
    }

    public String toGMTString() {
        return innerDate.toGMTString();
    }

    public void setYear(int year) {
        innerDate.setYear(year);
    }

    public void setSeconds(int seconds) {
        innerDate.setSeconds(seconds);
    }

    public void setMonth(int month) {
        innerDate.setMonth(month);
    }

    public void setMinutes(int minutes) {
        innerDate.setMinutes(minutes);
    }

    public void setHours(int hours) {
        innerDate.setHours(hours);
    }

    public void setDate(int date) {
        innerDate.setDate(date);
    }

    public static long parse(String s) {
        return Date.parse(s);
    }

    public int getYear() {
        return innerDate.getYear();
    }

    public int getTimezoneOffset() {
        return innerDate.getTimezoneOffset();
    }

    public int getSeconds() {
        return innerDate.getSeconds();
    }

    public int getMonth() {
        return innerDate.getMonth();
    }

    public int getMinutes() {
        return innerDate.getMinutes();
    }

    public int getHours() {
        return innerDate.getHours();
    }

    public int getDay() {
        return innerDate.getDay();
    }

    public int getDate() {
        return innerDate.getDate();
    }

    public static long UTC(int year, int month, int date, int hrs, int min, int sec) {
        return Date.UTC(year, month, date, hrs, min, sec);
    }
    
    
    
    
}
    
