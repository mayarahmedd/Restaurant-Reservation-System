/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import GUI.Manager_Dashboard;
import restaurant_system.Reservations;
import restaurant_system.Restaurant;

public class Manager extends Person {

    private Reservations reservations = new Reservations();

    @Override
    public void drawWindow(Restaurant restaurant, Restaurant savedrestaurant, String username) {

        try {
            reservations = savedrestaurant.getReservations();
            Manager_Dashboard mng = new Manager_Dashboard(restaurant, savedrestaurant);
            mng.setVisible(true);
        } catch (NullPointerException reservations) {
            Manager_Dashboard mng = new Manager_Dashboard(restaurant);

            mng.setVisible(true);
        }

    }

}
