package com.example.dudco.highjinro.Datas;

/**
 * Created by dudco on 2017. 2. 15..
 */

public class SchoolData {
    private String code, name, tag, pathways, employment, shcool_class, location_x, location_y, coeducation;

    public SchoolData(String code, String name, String tag, String pathways, String employment, String shcool_class, String location_x, String location_y, String coeducation) {
        this.code = code;
        this.name = name;
        this.tag = tag;
        this.pathways = pathways;
        this.employment = employment;
        this.shcool_class = shcool_class;
        this.location_x = location_x;
        this.location_y = location_y;
        this.coeducation = coeducation;
    }

    public String getCoeducation() {
        return coeducation;
    }

    public void setCoeducation(String coeducation) {
        this.coeducation = coeducation;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPathways() {
        return pathways;
    }

    public void setPathways(String pathways) {
        this.pathways = pathways;
    }

    public String getEmployment() {
        return employment;
    }

    public void setEmployment(String employment) {
        this.employment = employment;
    }

    public String getShcool_class() {
        return shcool_class;
    }

    public void setShcool_class(String shcool_class) {
        this.shcool_class = shcool_class;
    }

    public String getLocation_x() {
        return location_x;
    }

    public void setLocation_x(String location_x) {
        this.location_x = location_x;
    }

    public String getLocation_y() {
        return location_y;
    }

    public void setLocation_y(String location_y) {
        this.location_y = location_y;
    }
}
