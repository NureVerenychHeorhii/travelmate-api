package ua.nure.verenych.travelmate_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.nure.verenych.travelmate_api.service.AdminService;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Блокування користувача: POST /api/admin/users/1/ban
    @PostMapping("/users/{id}/ban")
    public void banUser(@PathVariable Long id) {
        adminService.banUser(id);
    }

    // Зміна ролі: PUT /api/admin/users/1/role?role=MANAGER
    @PutMapping("/users/{id}/role")
    public void changeRole(@PathVariable Long id, @RequestParam String role) {
        adminService.changeUserRole(id, role);
    }

    // Отримання статистики: GET /api/admin/stats
    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        return adminService.getSystemStatistics();
    }
}