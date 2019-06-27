package cn.edu.gdpt.libraryhome.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Userinfo {
    @DatabaseField
    private String name;
    @DatabaseField
    private String psw;

    public Userinfo(String name, String psw) {
        this.name = name;
        this.psw = psw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {

        this.psw = psw;
    }

    public Userinfo() {

    }
}
