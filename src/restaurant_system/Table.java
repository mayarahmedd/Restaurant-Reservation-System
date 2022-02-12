/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant_system;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "table")
@XmlAccessorType(XmlAccessType.FIELD)

public class Table {

    @XmlElement(name = "number")
    private int tablenumber;
    @XmlElement(name = "number_of_seats")
    private int numberOfseats;
    @XmlElement(name = "smoking")
    private Boolean tabletype;

    public int getTablenumber() {
        return tablenumber;
    }

    public void setTablenumber(int tablenumber) {
        this.tablenumber = tablenumber;
    }

    public int getNumberOfseats() {
        return numberOfseats;
    }

    public void setNumberOfseats(int numberOfseats) {
        this.numberOfseats = numberOfseats;
    }

    public Boolean getTabletype() {
        return tabletype;
    }

    public void setTabletype(Boolean tabletype) {
        this.tabletype = tabletype;
    }

}
