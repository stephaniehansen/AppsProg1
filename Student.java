import java.text.*;
import java.io.*;

public class Student implements Comparable<Student>, Serializable {
    private String name;
    private double owing;
    
    public Student(String name){
        this.name = name;
    }
    
    public String gender(){
        if (name.charAt(name.length() - 1) == 'o'){
            return "male";
        } else if(name.charAt(name.length() - 1) == 'a'){
            return "female";
        }
        return "unspecified";
    }
    
    public String name(){
        return name;
    }
    
    public double addAmount(double amount){
        return owing += amount;
    }
    
    public String print(){
        return "  " + name + " owes $" + formatted(owing);
    }
    
    private String formatted(double owing){ 
         DecimalFormat form = new DecimalFormat("###,##0.00"); 
         return form.format(owing); 
    } 

    public int compareTo(Student student){
        return this.name.compareTo(student.name);
    }
}
