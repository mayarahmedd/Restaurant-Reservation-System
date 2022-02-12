/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import GUI.Cook_Dashboard;
import GUI.Cook_noorders;
import restaurant_system.Orders;
import restaurant_system.Restaurant;

public class Cook extends Person {

    private Orders orders = new Orders();

    @Override
    public void drawWindow(Restaurant restaurant, Restaurant savedrestaurant, String username) {
        try {

            orders = savedrestaurant.getOrders();
            Cook_Dashboard ck = new Cook_Dashboard(orders);
            ck.setVisible(true);

        } catch (NullPointerException orders) {
            Cook_noorders cno = new Cook_noorders();
            cno.setVisible(true);

        }
    }
}
