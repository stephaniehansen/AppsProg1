public class TwoBedroomApartment extends Apartment {
    public TwoBedroomApartment(int number){
        this.number = number;
        bedrooms.add(new Bedroom(number, 1, 1500));
        bedrooms.add(new Bedroom(number, 2, 1500));
    } 
}
