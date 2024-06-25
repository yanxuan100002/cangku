package com.rainng.coursesystem.manager.admin;

import com.rainng.coursesystem.dao.AdminDAO;
import com.rainng.coursesystem.manager.BaseManager;
import com.rainng.coursesystem.model.entity.AdminEntity;
import org.springframework.stereotype.Component;

import java.util.List;
//这个AdminManager类是一个管理后台管理员的类，它封装了对管理员数据（可能是从数据库）的增删改查操作。
@Component
public class AdminManager extends BaseManager {
    private final AdminDAO adminDAO;

    public AdminManager(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    public AdminEntity get(Integer id) {
        return adminDAO.get(id);
    }

    public int create(AdminEntity entity) {
        return adminDAO.insert(entity);
    }

    public int update(AdminEntity entity) {
        return adminDAO.update(entity);
    }

    public int delete(Integer id) {
        return adminDAO.delete(id);
    }

    public List<AdminEntity> list() {
        return adminDAO.list();
    }
}
