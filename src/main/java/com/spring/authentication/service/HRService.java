package com.spring.authentication.service;

import com.spring.authentication.dao.Employee;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;

public class HRService {

    public static List<Employee> getAllEmployees(Request request, Response response) throws Exception {
        List<Employee> employees = new ArrayList<Employee>();
        employees.add( new Employee("E-001", "Chintan Shah", "https://external.com/E-001", "Account-001") );
        employees.add( new Employee("E-002", "Chuck Summer", "https://external.com/E-002", "Account-001") );
        employees.add( new Employee("E-003", "Chris Walker", "https://external.com/E-003", "Account-001") );
        employees.add( new Employee("E-004", "Dan Topper", "https://external.com/E-004", "Account-002") );
        employees.add( new Employee("E-005", "Drew Berry", "https://external.com/E-005", "Account-002") );
        employees.add( new Employee("E-006", "Don Aiken", "https://external.com/E-006", "Account-002") );
        return employees;
    }

    public static List<Employee> getEmployeesByColumn(Request request, Response response) throws Exception {
        List<Employee> allEmployees = getAllEmployees(request, response);
        List<Employee> filteredEmployees = new ArrayList<Employee>();
        String columnName = request.params(":columnName");
        String columnValue = request.params(":columnValue");
        System.out.println("HRService.getEmployeesByColumn columnName " + columnName + " columnValue " + columnValue );
        if( columnName != null ) {
            for(Employee employee : allEmployees ) {
                switch(columnName) {
                    case "ExternalId" : if( employee.getExternalId().equalsIgnoreCase( columnValue) ) { filteredEmployees.add( employee ); break; }
                    case "Name" : if( employee.getName().contains( columnValue) ) { filteredEmployees.add( employee ); break; }
                    case "EmployeeAccountKey" : if( employee.getEmployeeAccountKey().equalsIgnoreCase( columnValue) ) { filteredEmployees.add( employee ); break; }
                }
            }
        }
        return filteredEmployees;
    }

}
