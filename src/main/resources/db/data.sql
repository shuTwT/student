DELETE
FROM stu_major;
DELETE
FROM stu_clazz;
DELETE
FROM teacher;
DELETE
FROM course;
DELETE
FROM student;
DELETE
FROM score;
DELETE
FROM sys_role;
DELETE
FROM sys_user;
DELETE
FROM sys_router;

INSERT INTO stu_major (major_id, major_name)
VALUES (1, '软件技术'),
       (2, '大数据技术'),
       (3, '人工智能'),
       (4, '信息安全'),
       (5, '测试专业5'),
       (6, '测试专业6'),
       (7, '测试专业7'),
       (8, '测试专业8'),
       (9, '测试专业9'),
       (10, '测试专业10'),
       (11, '测试专业11'),
       (12, '测试专业12'),
       (13, '测试专业13'),
       (14, '测试专业14'),
       (15, '测试专业15');
INSERT INTO stu_clazz (clazz_id, clazz_name, major_id, assistant_id)
VALUES (1, '软件211', 1, 1),
       (2, '软件212', 1, 1),
       (3, '软件213', 1, 2),
       (4, '大数据211', 2, 2),
       (5, '大数据212', 2, 2),
       (6, '人工211', 3, 3),
       (7, '人工211', 3, 3);

INSERT INTO teacher (teacher_id, teacher_name)
VALUES (1, '测试教师1'),
       (2, '测试教师2'),
       (3, '测试教师3'),
       (4, '测试教师4'),
       (5, '测试教师5'),
       (6, '测试教师6'),
       (7, '测试教师7'),
       (8, '测试教师8'),
       (9, '测试教师9'),
       (10, '测试教师10'),
       (11, '测试教师11'),
       (12, '测试教师12'),
       (13, '测试教师13');

INSERT INTO course (course_id, course_name, course_tid, course_hour, course_score)
VALUES (1, '人工智能入门', 1, 2, '0.2'),
       (2, '前端开发', 1, 2, '0.2'),
       (3, '混合式APP开发', 2, 2, '0.2'),
       (4, 'javaWeb', 2, 2, '0.2'),
       (5, '软件测试', 3, 2, '0.2');

INSERT INTO student (stu_id, stu_name, stu_sex, stu_date, stu_major_id, stu_clazz_id)
VALUES (1, '测试学生1', '男', '2021-01-01', 1, 1),
       (2, '测试学生2', '男', '2021-01-01', 2, 1),
       (3, '测试学生3', '男', '2021-01-01', 3, 2),
       (4, '测试学生4', '女', '2021-01-01', 4, 2),
       (5, '测试学生5', '男', '2021-01-01', 1, 3),
       (6, '测试学生6', '男', '2021-01-01', 2, 3),
       (7, '测试学生7', '女', '2021-01-01', 3, 4),
       (8, '测试学生8', '男', '2021-01-01', 4, 5),
       (9, '测试学生9', '男', '2021-01-01', 1, 1),
       (10, '测试学生10', '女', '2021-01-01', 2, 2),
       (11, '测试学生11', '男', '2021-01-01', 3, 2),
       (12, '测试学生12', '男', '2021-01-01', 4, 3),
       (13, '测试学生13', '女', '2021-01-01', 1, 4),
       (14, '测试学生14', '男', '2021-01-01', 2, 5),
       (15, '测试学生15', '男', '2021-01-01', 3, 5),
       (16, '测试学生16', '女', '2021-01-01', 4, 5),
       (17, '测试学生17', '男', '2021-01-01', 5, 5),
       (18, '测试学生18', '男', '2021-01-01', 1, 5),
       (19, '测试学生19', '女', '2021-01-01', 2, 5),
       (20, '测试学生20', '女', '2021-01-01', 3, 5);

INSERT INTO score(score_id, score_cid, score_sid, score_num)
VALUES (1, 1, 1, '90'),
       (2, 1, 2, '91'),
       (3, 2, 3, '92'),
       (4, 2, 5, '93'),
       (5, 3, 6, '94'),
       (6, 3, 7, '95'),
       (7, 4, 8, '96'),
       (8, 4, 9, '97'),
       (9, 4, 10, '98'),
       (10, 5, 11, '99'),
       (11, 5, 12, '89'),
       (12, 5, 13, '88');

INSERT INTO sys_role(role_id, role_name)
VALUES (1, '普通用户'),
       (2, '管理员'),
       (3, '超管');

INSERT INTO sys_user(user_id, user_name, user_password, role_id)
VALUES (1, '张三', '123456', 1),
       (2, '李四', '123123', 2),
       (3, 'admin', 'admin123', 3);

INSERT INTO sys_router(router_id, parent_id, router_name, router_path, min_role)
VALUES (1, null, '成绩管理', '/admin/', 1),
       (10, 1, '专业管理', '/admin/major_list', 2),
       (11, 1, '教师管理', '/admin/teacher_list', 2),
       (12, 1, '班级管理', '/admin/clazz_list', 2),
       (13, 1, '课程管理', '/admin/course_list', 2),
       (14, 1, '学生管理', '/admin/student_list', 2),
       (15, 1, '成绩管理', '/admin/score_list', 1),
       (2, null, '系统管理', '/admin/sys/', 2),
       (20, 2, '用户列表', '/admin/sys/user', 3),
       (21, 2, '角色管理', '/admin/sys/role', 3),
       (22, 2, '权限分类', '/admin/sys/category', 3),
       (23, 2, '系统接口', '/doc.html', 3),
       (24, 2, '系统日志', '/admin/sys/log', 2);
