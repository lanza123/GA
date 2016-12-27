package tsp;

import java.util.ArrayList;
import java.util.Collections;

public class Tour{
    private ArrayList tour = new ArrayList<City>();

    private double fitness = 0;
    private int distance = 0;

    public Tour(){
        for (int i = 0; i < TSP_GA.destinationCities.size(); i++) {
            tour.add(null);
        }
    }
    
    public Tour(ArrayList tour){
        this.tour = tour;
    }

    public void generateIndividual() {
        for (int cityIndex = 0; cityIndex < TSP_GA.destinationCities.size(); cityIndex++) {
          setCity(cityIndex, TSP_GA.destinationCities.get(cityIndex));
        }
        Collections.shuffle(tour);
    }

    public City getCity(int tourPosition) {
        return (City)tour.get(tourPosition);
    }

    public void setCity(int tourPosition, City city) {
        tour.set(tourPosition, city);
        fitness = 0;
        distance = 0;
    }

    public double getFitness() {
        if (fitness == 0) {
            fitness = 1/(double)getDistance();
        }
        return fitness;
    }

    public int getDistance(){
        if (distance == 0) {
            int tourDistance = 0;
            for (int cityIndex=0; cityIndex < tourSize(); cityIndex++) {
                City fromCity = getCity(cityIndex);
                City destinationCity;
                if(cityIndex+1 < tourSize()){
                    destinationCity = getCity(cityIndex+1);
                }
                else{
                    destinationCity = getCity(0);
                }
                tourDistance += fromCity.distanceTo(destinationCity);
            }
            distance = tourDistance;
        }
        return distance;
    }

    public int tourSize() {
        return tour.size();
    }

    public boolean containsCity(City city){
        return tour.contains(city);
    }

}