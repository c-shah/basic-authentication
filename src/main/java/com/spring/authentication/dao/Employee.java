package com.spring.authentication.dao;

public class Employee {
    String ExternalId;
    String Name;
    String DisplayUrl;
    String EmployeeAccountKey;

    public Employee(String ExternalId, String Name, String DisplayUrl, String EmployeeAccountKey) {
        this.ExternalId = ExternalId;
        this.Name = Name;
        this.DisplayUrl = DisplayUrl;
        this.EmployeeAccountKey = EmployeeAccountKey;
    }

    public String getExternalId() {
        return ExternalId;
    }

    public void setExternalId(String externalId) {
        ExternalId = externalId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDisplayUrl() {
        return DisplayUrl;
    }

    public void setDisplayUrl(String displayUrl) {
        DisplayUrl = displayUrl;
    }

    public String getEmployeeAccountKey() {
        return EmployeeAccountKey;
    }

    public void setEmployeeAccountKey(String employeeAccountKey) {
        EmployeeAccountKey = employeeAccountKey;
    }

}
