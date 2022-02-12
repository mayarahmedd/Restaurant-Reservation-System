/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import restaurant_system.User;

public class PersonFactory {

    public static Person convertFromUser(User user) {
        if (user.getRole().equalsIgnoreCase("manager")) {
            return new Manager();
        } else if (user.getRole().equalsIgnoreCase("waiter")) {
            return new Waiter();
        } else if (user.getRole().equalsIgnoreCase("client")) {

            return new Customer();

        } else if (user.getRole().equalsIgnoreCase("cooker")) {
            return new Cook();

        }
        throw new RuntimeException("Unsupported role");
    }

}
