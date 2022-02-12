/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import GUI.Waiter_Dashboard;
import GUI.Waiter_noreservations;
import java.util.List;
import restaurant_system.Reservation;

import restaurant_system.Restaurant;

public class Waiter extends Person {

    @Override
    public void drawWindow(Restaurant restaurant, Restaurant savedrestaurant, String username) {
        try {
            List<Reservation> reservationlist = savedrestaurant.getReservations().getReservations();
            Waiter_Dashboard wtr = new Waiter_Dashboard(reservationlist);
            wtr.setVisible(true);
        } catch (NullPointerException reservationlist) {

           
            Waiter_noreservations wnr = new Waiter_noreservations();

            wnr.setVisible(true);

        }

    }
}
