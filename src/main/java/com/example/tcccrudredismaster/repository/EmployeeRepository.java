package com.example.tcccrudredismaster.repository;

import com.example.tcccrudredismaster.entity.Employee;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    private HashOperations hashOperations;
    private RedisTemplate redisTemplate;
    private ListOperations listOperations;
    private final static String  EMPLOYEE_KEY = "EMPLOYEE";

    public EmployeeRepository(RedisTemplate redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
        this.listOperations = redisTemplate.opsForList();
        this.redisTemplate = redisTemplate;
    }

    public void saveEmployee(Employee e) {
        hashOperations.put(EMPLOYEE_KEY, e.getId(), e);
    }

    public List<Employee> getAllEmployees() {
        return hashOperations.values(EMPLOYEE_KEY);
    }

    public Employee findEmployeeById(int id) {
        return (Employee)  hashOperations.get(EMPLOYEE_KEY, id);
    }

    public void updateEmployee(Employee employee) {
        saveEmployee(employee);
    }

    public void deleteEmployee(int id) {
        hashOperations.delete(EMPLOYEE_KEY, id);
    }
}
