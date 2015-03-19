public class ThreeBedroomApartment extends Apartment {
    public ThreeBedroomApartment(int number){
        this.number = number;
        bedrooms.add(new Bedroom(number, 1, 1000));
        bedrooms.add(new Bedroom(number, 2, 1000));
        bedrooms.add(new Bedroom(number, 3, 1000));
    }     
}
