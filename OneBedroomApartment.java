public class OneBedroomApartment extends Apartment {
    public OneBedroomApartment(int number){
        this.number = number;
        bedrooms.add(new Bedroom(number, 1, 2000));
    } 
}
