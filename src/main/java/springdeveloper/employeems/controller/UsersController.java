package springdeveloper.employeems.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import springdeveloper.employeems.dto.ChangeOwnPasswordRequest;
import springdeveloper.employeems.dto.UserDTO;
import springdeveloper.employeems.model.Employee;
import springdeveloper.employeems.service.EmployeeService;
import springdeveloper.employeems.service.UsersService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;
    private final EmployeeService employeeService;

    public UsersController(UsersService usersService, EmployeeService employeeService) {
        this.usersService = usersService;
        this.employeeService = employeeService;
    }

    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping("/changeOwnPassword")
    public ResponseEntity<?> changeOwnPassword(@RequestBody ChangeOwnPasswordRequest request) {
        try {
            String message = usersService.changeOwnPassword(request);
            return ResponseEntity.ok(message);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/userDashboard")
    public ResponseEntity<String> userDashboard() {
        return ResponseEntity.ok("Welcome, User! You can view this content.");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/allEmployee")
    public ResponseEntity<?> getAllEmployees() {
        return getResponseEntity(employeeService);
    }

    static ResponseEntity<?> getResponseEntity(EmployeeService employeeService) {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        Map<String, Object> response = new HashMap<>();
        response.put("count", allEmployees.size());
        response.put("employees", allEmployees); // لیست کارمندان را هم اضافه کن
        return ResponseEntity.ok(response);
    }

    static ResponseEntity<?> getResponseEntity(UsersService usersService) {
        List<UserDTO> userDTOs = usersService.getAllUsers();
        Map<String, Object> map = new HashMap<>();
        map.put("count", userDTOs.size());
        map.put("Users", userDTOs);
        return ResponseEntity.ok(map);
    }
}