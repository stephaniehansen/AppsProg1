import java.util.*;
import java.io.*;

public class Bedroom implements Serializable{
    private Student student;
    private int bedNumber;
    private int roomNumber;
    private double cost;
    
    public Bedroom(int roomNumber, int bedNumber, double cost){
        this.bedNumber = bedNumber;
        this.roomNumber = roomNumber;
        this.cost = cost;
    }
    
    public boolean isVacant(){
        return student == null;
    }
    
    public void allocate(Student student){
        this.student = student;
    }
   
    public String number(){
        return roomNumber + "." + bedNumber;
    }
    
    public Student student(){
        return student;
    }
    
    public boolean hasStudent(Student student) {
        return this.student == student;
    }

    public void remove(){
        this.student = null;
    }
    
    public String toString() {
        return "  " + this.bedNumber + ": " + student.name();
    }
    
    public double cost(){
        return cost;
    }
    
}