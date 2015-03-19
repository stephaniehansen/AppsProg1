import java.util.*;
import java.io.*;

public class Tower implements Serializable{
    public static void main(String[] args){
        new Tower();
    }
    
    private LinkedList<Apartment> apartments = new LinkedList<Apartment>();
    private final int floors = 9;
    private String fileName = "tower.dat";
    
    public Tower(){
        File file = new File(fileName);
        if (file.exists() && !file.isDirectory()) {
            load();
        } else {
            setup();
            start();
        }
    }
    
    private void start(){
        char choice;
        while((choice = readChoice()) !='x') {
            execute(choice);
        }
    }
    
    private void load(){
        try {
            FileInputStream inFile = new FileInputStream(fileName);
            ObjectInputStream inStream = new ObjectInputStream(inFile);
            Tower tower = (Tower) inStream.readObject();
            inStream.close();
            tower.start();
        } catch (Exception e) {
            Tower tower = new Tower();
        }
    }
    
    private char readChoice(){   
        System.out.print("Choice: ");
        String choice = In.nextLine();
        return choice.charAt(0);
    }
    
    private String readName(){
        System.out.print("Name: ");
        return In.nextLine();
    }
    
    private void setup(){
      for(int i = 1; i <= floors; i++) {
          apartments.add(new OneBedroomApartment(i*100 + 1));
          apartments.add(new ThreeBedroomApartment(i*100 + 2));
          apartments.add(new TwoBedroomApartment(i*100 + 3));
      }
    }
    
    private void menu(){
        System.out.println("The choices are");
        System.out.println("  a: allocate a room");
        System.out.println("  m: move to another room");
        System.out.println("  s: see who is in a room");
        System.out.println("  t: add time (a month)");
        System.out.println("  c: show student charges");
        System.out.println("  f: store to file");
        System.out.println("  x: exit the system");
        System.out.println("  ?: show this display");
    }

    private void execute(char choice){
        switch (choice) {
            case 'a': allocate(); break;
            case 'm': move(); break; 
            case 's': see(); break;
            case 't': time(); break;
            case 'c': charges(); break;
            case 'f': file(); break;
            case 'x': exit(); break;
            case '?': menu(); break;
            default: menu(); break;
        }
    }

    private void allocate(){
        String name;
        while(!(name = readName()).equals("x")) {
            Student student = new Student(name);
            allocateRoom(student);
        }
    }
    
    private void allocateRoom(Student student){
        for (Apartment apartment : apartments){
                Bedroom bedroom = apartment.vacancy(student.gender());
                if (bedroom != null) {
                    bedroom.allocate(student);
                    System.out.println("  Room: " + bedroom.number());
                    break;
                }
        }
    }
    
    private void move(){
        Student student = findStudent(readName());
        Bedroom oldBedroom = findBedroomAllocatedFor(student);
        allocateRoom(student);
        oldBedroom.remove();
    }
    
    private Bedroom findBedroomAllocatedFor(Student student) {
        for (Apartment apartment : apartments){ 
            Bedroom bedroom = apartment.findBedroomAllocatedFor(student);
            if (bedroom != null) {
                return bedroom;
            }
        }
        return null;
    }
    
    private Student findStudent(String name){
        for (Apartment apartment : apartments){ 
            Student student = apartment.findStudent(name);
            if (student != null) {
                return student;
            }
        }
        return null;
    }
    
    private void see(){
        System.out.print("Room: ");
        int number = In.nextInt();        
        for (Apartment apartment : apartments){
            if(apartment.number() == number){
                System.out.print(apartment);
            }
        }
    }
    
    private void time(){
        for(Apartment apartment : apartments){
            apartment.charge();
        }
    }
    
    private LinkedList<Student> findAllStudents() {
        LinkedList<Student> students = new LinkedList<Student>();
        for(Apartment apartment : apartments){
            students.addAll(apartment.findAllStudents());
        }
        return students;
    }
    
    private void charges(){
        LinkedList<Student> students = findAllStudents();
        Collections.sort(students);
        for (Student student: students) {
            System.out.print(student.print() + "\n");
        }
    }

    private void file(){
        try {
            FileOutputStream outFile = new FileOutputStream(fileName);
            ObjectOutputStream outStream = new ObjectOutputStream(outFile);
            outStream.writeObject(this);
            outStream.close();
            System.out.println("  Data stored");
            exit();
        } catch(Exception e) {
            System.out.println("Store failed");
        }
    }
    
    private void exit(){
        System.exit(0);
    }
}