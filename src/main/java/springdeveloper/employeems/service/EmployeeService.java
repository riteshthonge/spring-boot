package springdeveloper.employeems.service;

import org.springframework.stereotype.Service;
import springdeveloper.employeems.model.Employee;
import springdeveloper.employeems.repository.EmployeeRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UsersService usersService;

    public EmployeeService(EmployeeRepository employeeRepository, UsersService usersService) {
        this.employeeRepository = employeeRepository;
        this.usersService = usersService;
    }
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    public Employee saveEmployee(Employee employee) {
        // تنظیم تاریخ استخدام به تاریخ امروز
        employee.setHireDate(LocalDateTime.now());
        return employeeRepository.save(employee);
    }

    public boolean updateEmployee(Employee employee) {
        if (employeeRepository.existsById(employee.getId())) {
            employeeRepository.save(employee); // اگر ID موجود باشد، به‌روزرسانی می‌شود
            return true;
        }
        return false;
    }
    // متد حذف کارمند بر اساس شناسه
    public boolean deleteEmployeeById(Integer id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
