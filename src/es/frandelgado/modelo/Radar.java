package es.frandelgado.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by thinway on 7/5/17.
 */
public class Radar {

    final int MILISECONDS_IN_SECOND = 1000;
    final int METERS_IN_KM = 1000;
    final int SECONDS_IN_HOUR = 60 * 60;

    private ArrayList<Flight> flights;

    public Radar() {
        flights = new ArrayList<>();
    }


    public void addflight(Flight flight) {
        if( flight != null){
            flights.add(flight);
        }
    }

    public void flightList() {

        updateFlightsPositions();

        showFlightList();
    }

    private void showFlightList() {
        for (Flight flight :
                flights) {
            System.out.println(flight);
        }
    }

    private void updateFlightsPositions() {
        Date newDate;
        long timeElapsed;
        double speedMetersPerSecond;
        double distanceTraveled;

        Iterator<Flight> itFlights = flights.iterator();

//        for (Flight flight : flights) {
        while( itFlights.hasNext() ){
            Flight flight = itFlights.next();

            newDate = new Date();
            timeElapsed = (newDate.getTime() - flight.getControlDateTime().getTime()) / MILISECONDS_IN_SECOND;
            flight.setControlDateTime(newDate);
            speedMetersPerSecond = flight.getSpeed() * METERS_IN_KM / SECONDS_IN_HOUR;
            distanceTraveled = speedMetersPerSecond * timeElapsed / METERS_IN_KM;
            // New position
            flight.setDistanceToUs(flight.getDistanceToUs() - distanceTraveled);

            if( flight.getDistanceToUs() == 0.0 ){
                itFlights.remove();
            }
        }

        Collections.sort( flights ); // Ordenación siguiendo la interfaz Comparable (método: compareTo)
    }

    public void flightListByAirline() {
        // Ordenación siguiendo la interfaz Comparator (método: compare)
        Collections.sort( flights, new Flight() );

        showFlightList();
    }

    public void flightListBySpeed() {
        Collections.sort( flights, Flight.compareBySpeed );

        showFlightList();
    }
}
