package es.frandelgado.modelo;

import java.io.*;
import java.util.*;

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

// PARA ABARCAR TODAS LAS FUNCIONALIDADES QUE SON REFERENTE A VUELOS, PODRIAMOS USAR EL PATRON FACADE
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

        Iterator<Flight> itFlight = flights.iterator();

//        for (Flight flight : flights) {
        while( itFlight.hasNext() ){
            Flight flight = itFlight.next();

            newDate = new Date();
            timeElapsed = (newDate.getTime() - flight.getControlDateTime().getTime()) / MILISECONDS_IN_SECOND;
            flight.setControlDateTime(newDate);
            speedMetersPerSecond = flight.getSpeed() * METERS_IN_KM / SECONDS_IN_HOUR;
            distanceTraveled = speedMetersPerSecond * timeElapsed / METERS_IN_KM;
            // New position
            flight.setDistanceToUs(flight.getDistanceToUs() - distanceTraveled);

            if( flight.getDistanceToUs() == 0.0 ){
                itFlight.remove();
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

    public void saveFlights() {
        try {
            ObjectOutputStream fichero = new ObjectOutputStream( new FileOutputStream("info/flights.dat"));
            fichero.writeObject( flights );
            fichero.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFlights() {
        try {
            ObjectInputStream fichero = new ObjectInputStream(new FileInputStream("info/flights.dat"));
            flights = (ArrayList<Flight>)fichero.readObject();
            fichero.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
