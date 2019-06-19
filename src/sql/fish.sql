INSERT INTO department (name, description, pid, create_time, enabled, sort) VALUES ('总部', '总部', 0, '2019-05-10 18:16:00', '', 0);
INSERT INTO department (name, description, pid, create_time, enabled, sort) VALUES ('北京分部', '北京分部', 1, '2019-05-10 18:16:00', '', 1);
INSERT INTO department (name, description, pid, create_time, enabled, sort) VALUES ('上海分部', '上海分部', 1, '2019-05-10 18:16:00', '', 2);

INSERT INTO job (name, description, create_time, enabled, sort, department_id) VALUES ('董事长', null, '2019-05-10 18:16:00', '', 0, 2);
INSERT INTO job (name, description, create_time, enabled, sort, department_id) VALUES ('研发工程师', null, '2019-05-10 18:16:00', '', 1, 3);

INSERT INTO user (username, gender, age, password, salt, email, phone, department_id, job_id, real_name, avatar, create_time, enabled, last_password_reset_time) VALUES ('admin', 1, 22, '5068ba9ac1af1ccba0e5922d0f89b780', '2B94by', 'avalon@nekolr.com', '13121470000', 2, 1, 'saber', null, '2019-05-10 18:16:00', '', null);
INSERT INTO user (username, gender, age, password, salt, email, phone, department_id, job_id, real_name, avatar, create_time, enabled, last_password_reset_time) VALUES ('test', 0, 25, 'ab1bf2261fdda57728c8aa027776faf0', 'WOhHni', 'test@nekolr.com', '13121470001', 3, 2, '测试', null, '2019-05-10 18:16:00', '', null);

INSERT INTO role (create_time, description, name, sort) VALUES ('2019-05-10 18:16:00', '管理员', 'ADMIN', 0);
INSERT INTO role (create_time, description, name, sort) VALUES ('2019-05-10 18:16:00', '普通员工', 'EMPLOYEE', 1);

INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (2, 2);

INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '用户管理权限', 'USER_ALL', 0, 0);
INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '角色管理权限', 'ROLE_ALL', 0, 1);
INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '菜单管理权限', 'MENU_ALL', 0, 2);
INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '部门管理权限', 'DEPARTMENT_ALL', 0, 3);
INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '职务管理权限', 'JOB_ALL', 0, 4);
INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '权限管理权限', 'PERMISSION_ALL', 0, 5);

INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '用户查看权限', 'USER_SELECT', 1, 6);
INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '用户创建权限', 'USER_POST', 1, 7);
INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '用户更新权限', 'USER_PUT', 1, 8);
INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '用户删除权限', 'USER_DELETE', 1, 9);

INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '角色查看权限', 'ROLE_SELECT', 2, 10);
INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '角色创建权限', 'ROLE_POST', 2, 11);
INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '角色更新权限', 'ROLE_PUT', 2, 12);
INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '角色删除权限', 'ROLE_DELETE', 2, 13);

INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '菜单查看权限', 'MENU_SELECT', 3, 14);
INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '菜单创建权限', 'MENU_POST', 3, 15);
INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '菜单更新权限', 'MENU_PUT', 3, 16);
INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '菜单删除权限', 'MENU_DELETE', 3, 17);

INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '部门查看权限', 'DEPARTMENT_SELECT', 4, 18);
INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '部门创建权限', 'DEPARTMENT_POST', 4, 19);
INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '部门更新权限', 'DEPARTMENT_PUT', 4, 20);
INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '部门删除权限', 'DEPARTMENT_DELETE', 4, 21);

INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '职务查看权限', 'JOB_SELECT', 5, 22);
INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '职务创建权限', 'JOB_POST', 5, 23);
INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '职务更新权限', 'JOB_PUT', 5, 24);
INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '职务删除权限', 'JOB_DELETE', 5, 25);

INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '权限查看权限', 'PERMISSION_SELECT', 6, 26);
INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '权限创建权限', 'PERMISSION_POST', 6, 27);
INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '权限更新权限', 'PERMISSION_PUT', 6, 28);
INSERT INTO permission(create_time, description, name, pid, sort) VALUES ('2019-05-10 18:16:00', '权限删除权限', 'PERMISSION_DELETE', 6, 29);

INSERT INTO role_permission(role_id, permission_id) VALUES (1, 1);
INSERT INTO role_permission(role_id, permission_id) VALUES (1, 2);
INSERT INTO role_permission(role_id, permission_id) VALUES (1, 3);
INSERT INTO role_permission(role_id, permission_id) VALUES (1, 4);
INSERT INTO role_permission(role_id, permission_id) VALUES (1, 5);
INSERT INTO role_permission(role_id, permission_id) VALUES (1, 6);

INSERT INTO role_permission(role_id, permission_id) VALUES (2, 7);
INSERT INTO role_permission(role_id, permission_id) VALUES (2, 11);
INSERT INTO role_permission(role_id, permission_id) VALUES (2, 15);
INSERT INTO role_permission(role_id, permission_id) VALUES (2, 19);
INSERT INTO role_permission(role_id, permission_id) VALUES (2, 23);
INSERT INTO role_permission(role_id, permission_id) VALUES (2, 27);

INSERT INTO menu (create_time, icon, name, outside, path, pid, sort) VALUES ('2019-05-10 18:16:00', 'icon-setting', '系统管理', false, null, 0, 0);
INSERT INTO menu (create_time, icon, name, outside, path, pid, sort) VALUES ('2019-05-10 18:16:00', 'icon-user', '用户管理', false, 'system/user', 1, 1);
INSERT INTO menu (create_time, icon, name, outside, path, pid, sort) VALUES ('2019-05-10 18:16:00', 'icon-team', '角色管理', false, 'system/role', 1, 2);
INSERT INTO menu (create_time, icon, name, outside, path, pid, sort) VALUES ('2019-05-10 18:16:00', 'icon-safetycertificate', '权限管理', false, 'system/permission', 1, 3);
INSERT INTO menu (create_time, icon, name, outside, path, pid, sort) VALUES ('2019-05-10 18:16:00', 'icon-unorderedlist', '菜单管理', false, 'system/menu', 1, 4);
INSERT INTO menu (create_time, icon, name, outside, path, pid, sort) VALUES ('2019-05-10 18:16:00', 'icon-apartment', '部门管理', false, 'system/department', 1, 5);
INSERT INTO menu (create_time, icon, name, outside, path, pid, sort) VALUES ('2019-05-10 18:16:00', 'icon-job', '职务管理', false, 'system/job', 1, 6);

INSERT INTO role_menu(role_id, menu_id) VALUES (1, 1);
INSERT INTO role_menu(role_id, menu_id) VALUES (1, 2);
INSERT INTO role_menu(role_id, menu_id) VALUES (1, 3);
INSERT INTO role_menu(role_id, menu_id) VALUES (1, 4);
INSERT INTO role_menu(role_id, menu_id) VALUES (1, 5);
INSERT INTO role_menu(role_id, menu_id) VALUES (1, 6);
INSERT INTO role_menu(role_id, menu_id) VALUES (1, 7);

INSERT INTO role_menu(role_id, menu_id) VALUES (2, 1);
INSERT INTO role_menu(role_id, menu_id) VALUES (2, 2);
INSERT INTO role_menu(role_id, menu_id) VALUES (2, 3);
INSERT INTO role_menu(role_id, menu_id) VALUES (2, 4);
INSERT INTO role_menu(role_id, menu_id) VALUES (2, 5);
INSERT INTO role_menu(role_id, menu_id) VALUES (2, 6);
INSERT INTO role_menu(role_id, menu_id) VALUES (2, 7);