/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import GUI.Customer_Dashboard;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import restaurant_system.Dish;
import restaurant_system.Dishes;
import restaurant_system.Order;
import restaurant_system.Orders;
import restaurant_system.Reservation;
import restaurant_system.Reservations;
import restaurant_system.Restaurant;
import restaurant_system.Table;
import restaurant_system.Tables;

public class Customer extends Person {

    private Dishes dishes = new Dishes();

    private Tables tables = new Tables();

    private Reservations reservations = new Reservations();
    private Restaurant restaurant;
    private Restaurant savedrestaurant;

    private float taxpercentage;
    private int tablenumber;
    private Dishes dishespurchased = new Dishes();
    private List<Dish> dishespurchasedlist = new ArrayList<>();

    private float price = 0;
   
    private int flag = 0;
    
private int count =0;
    public int getTablenumber() {
        return tablenumber;
    }

    public void setTablenumber(int tablenumber) {
        this.tablenumber = tablenumber;
    }

    public Customer(Restaurant restaurant, Restaurant savedrestaurant) {
        this.restaurant = restaurant;
        this.savedrestaurant = savedrestaurant;
    }

    public Customer() {
    }

    public float getPrice() {
        return price;
    }

    @Override
    public void drawWindow(Restaurant restaurant, Restaurant savedrestaurant, String username) {

        this.restaurant = restaurant;
        this.savedrestaurant = savedrestaurant;
       
        

        try {
            reservations = savedrestaurant.getReservations();
            Customer_Dashboard cst = new Customer_Dashboard(restaurant, savedrestaurant,username);

            cst.setVisible(true);
        } catch (NullPointerException reservations) {

            Customer_Dashboard cst = new Customer_Dashboard(restaurant, savedrestaurant,username);

            cst.setVisible(true);

        }

    }

    public int Order(String dishname) {
        Dish dsh = null;
        tables = restaurant.getTables();
        dishes = restaurant.getDishes();
        dishespurchased.setDishes(dishespurchasedlist);

        List<Dish> disheslist = dishes.getDishes();
        for (int i = 0; i < disheslist.size(); i++) {
            if (disheslist.get(i).getName().equalsIgnoreCase(dishname)) {
                dsh = disheslist.get(i);
                break;
            }
        }

        if (dsh != null) {

            if (dsh.getType().equalsIgnoreCase("appetizer")) {

                taxpercentage = (float) 0.1;
            } else if (dsh.getType().equalsIgnoreCase("main_course")) {

                taxpercentage = (float) 0.15;
            } else if (dsh.getType().equalsIgnoreCase("desert")) {
                taxpercentage = (float) 0.2;

            }

            dishespurchasedlist.add(dsh);

            price = price + (dsh.getPrice() + (dsh.getPrice() * taxpercentage));

            flag = 1;
             count++;
            return flag;
           
        } else {
flag = 0;
            return flag;
        }

    }

    public int Reserve(String tabletype, String numberofseats) {
        tables = restaurant.getTables();
        List<Table> tablesList = tables.getTables();

        Table tbs = null;
        int flag = 0;

        Boolean x = false;
        if (tabletype != null) {
            if (tabletype.equalsIgnoreCase("smoking")) {
                x = true;

            } else {
                x = false;
            }
        }

        for (int i = 0; i < tablesList.size(); i++) {
            if (tablesList.get(i).getNumberOfseats() == Integer.parseInt(numberofseats) && tablesList.get(i).getTabletype() == x) {//Integer.parseInt(jTextField1.getText()) && tables.get(i).getTabletype() == x ) {
                tbs = tablesList.get(i);
                break;
            }
        }

        try {
            List<Reservation> reservations = savedrestaurant.getReservations().getReservations();
            if (tbs != null) {

                for (int i = 0; i < reservations.size(); i++) {
                    if (tbs.getTablenumber() == reservations.get(i).getTablenumber()) {

                        flag = 1;
                        break;
                    }

                }
                if (flag == 1) {
                    return flag;

                } else {
                    this.tablenumber = tbs.getTablenumber();
                    return flag;

                }
            } else {
                flag = 1;
                return flag;

            }
        } catch (NullPointerException reservations) {
            if (tbs != null) {
                this.tablenumber = tbs.getTablenumber();
                return flag;

            } else {
                flag = 1;
                return flag;

            }

        }

    }

    public int saveOrderAndReserve(String customername) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Restaurant.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        Restaurant save = new Restaurant();
        Orders orders = new Orders();
        Order order = new Order();
        order.setTablenumber(tablenumber);
        order.setPrice(price);
        order.setDishes(dishespurchased);
        Reservations reservations = new Reservations();
        Reservation reservation = new Reservation();
        reservation.setCustomer_name(customername);
        reservation.setTablenumber(tablenumber);

        try {
            if (count>0) {

                List<Order> OL = savedrestaurant.getOrders().getOrders();
                OL.add(order);
                orders.setOrders(OL);
                save.setOrders(orders);

                List<Reservation> r = savedrestaurant.getReservations().getReservations();
                r.add(reservation);
                reservations.setReservations(r);
                save.setReservations(reservations);

                marshaller.marshal(save, new File("restaurantorders.xml"));

                return count;
            } else if (count == 0) {
                return count;
            }

        } catch (Exception savedrestaurant) {

            if (count>0) {

                List<Order> OL = new ArrayList<>();
                OL.add(order);
                orders.setOrders(OL);
                save.setOrders(orders);

                List<Reservation> r = new ArrayList<>();
                r.add(reservation);
                reservations.setReservations(r);
                save.setReservations(reservations);

                marshaller.marshal(save, new File("restaurantorders.xml"));

                return count;
            } else if (count == 0) {

                return count;
            }
        }
        return -1;

    }
}
