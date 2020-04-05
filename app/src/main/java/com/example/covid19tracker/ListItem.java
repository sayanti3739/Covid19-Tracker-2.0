package com.example.covid19tracker;

import java.io.Serializable;

public class ListItem implements Serializable {

    String sname, cnf, dis, deat;

    public ListItem() {
    }

    public String getSname() {
        return sname;
    }

    public String getCnf() {
        return cnf;
    }

    public String getDis() {
        return dis;
    }

    public String getDeat() {
        return deat;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setCnf(String cnf) {
        this.cnf = cnf;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public void setDeat(String deat) {
        this.deat = deat;
    }
}
