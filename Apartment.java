import java.util.*;
import java.io.*;

public class Apartment implements Serializable{
    protected int number;
    protected LinkedList<Bedroom> bedrooms = new LinkedList<Bedroom>();
    
    public Bedroom vacancy(String gender){
        for(Bedroom bedroom: bedrooms) {
            if (bedroom.isVacant() && conditions(gender)) {
                return bedroom;
            }
        }
        return null;
    }
    
    public String gender(){
        for(Bedroom bedroom : bedrooms){
            if (!bedroom.isVacant()) {
                return bedroom.student().gender();
            }
        }
        return null;
    }
    
    private boolean emptyApartment(){
        for(Bedroom bedroom : bedrooms){
            if (!bedroom.isVacant()) {
                return false;
            }
        }
        return true;    
    }
    
    private boolean conditions(String gender){
        if (gender.equals("unspecified")) {
            return emptyApartment();
        } else {
            return gender() == null || (!gender().equals("unspecified") 
                && gender.equals(gender()));
        }
    }
    
    public String toString(){
        String str = "";
        for(Bedroom bedroom: bedrooms) {
            if (!bedroom.isVacant()) {
                str += bedroom.toString() + "\n";
            }
        }
        return str;
    }
    
    public Student findStudent(String name){
        for(Bedroom bedroom: bedrooms) {
            if (!bedroom.isVacant()) {
                Student student = bedroom.student();
                if(name.equals(student.name())){
                    return student;
                }
            }
       }
       return null;
    }
    
    public Bedroom findBedroomAllocatedFor(Student student){
        for(Bedroom bedroom: bedrooms) {
            if (!bedroom.isVacant()) {
                if (bedroom.hasStudent(student)) {
                    return bedroom;
                }
            }
       }
       return null;    
    }  
    
    public int number(){
        return number;
    }
    
    public void charge(){
        for(Bedroom bedroom : bedrooms){
            if (!bedroom.isVacant()) {
                Student student = bedroom.student();
                double charge = 0;
                double rent = bedroom.cost();
                if(student.gender().equals("unspecified")){
                     charge += rent * bedrooms.size();
                } else {
                    charge += rent;
                }
                charge += 1000.11;
                charge *= 1.1;
                student.addAmount(charge);
            }
        }
    }
    
    public LinkedList<Student> findAllStudents(){
        LinkedList<Student> students = new LinkedList<Student>();
        for(Bedroom bedroom : bedrooms){
            if (!bedroom.isVacant()) {
                students.add(bedroom.student());
            }
        }
        return students;
    } 
}