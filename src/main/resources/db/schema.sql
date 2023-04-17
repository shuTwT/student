DROP TABLE IF EXISTS stu_major;
DROP TABLE IF EXISTS stu_clazz;
Drop TABLE IF EXISTS teacher;
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS score;
DROP TABLE IF EXISTS sys_role;
DROP TABLE IF EXISTS sys_user;
DROP TABLE IF EXISTS sys_router;

CREATE TABLE stu_major
(
    major_id       bigint      NOT NULL COMMENT '专业id',
    major_name     varchar(20) NULL     DEFAULT NULL COMMENT '专业名',
    major_disabled bool        NOT NULL DEFAULT 0 COMMENT '禁用',
    last_update    TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '更新时间',
    PRIMARY KEY (major_id)
);
CREATE TABLE stu_clazz
(
    clazz_id       BIGINT      NOT NULL COMMENT '班级id',
    clazz_name     VARCHAR(20) NULL     default NULL COMMENT '班级名',
    major_id       BIGINT      NULL     DEFAULT NULL COMMENT '专业id',
    assistant_id   BIGINT      NULL     DEFAULT NULL COMMENT '辅导员id',
    clazz_disabled bool        NOT NULL DEFAULT 0 COMMENT '禁用',
    last_update    TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '更新时间',
    PRIMARY KEY (clazz_id)
);
CREATE TABLE teacher
(
    teacher_id       bigint      NOT NULL COMMENT '教师id',
    teacher_name     varchar(20) NULL     DEFAULT NULL COMMENT '教师名',
    teacher_disabled bool        NOT NULL DEFAULT 0 COMMENT '禁用',
    last_update      TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '更新时间',
    PRIMARY KEY (teacher_id)
);
CREATE TABLE course
(
    course_id       bigint      NOT NULL COMMENT '课程id',
    course_name     varchar(20) NULL     DEFAULT NULL COMMENT '课程名',
    course_tid      bigint      NOT NULL COMMENT '授课教师id',
    course_hour     int         NOT NULL COMMENT '课程学时',
    course_score    varchar(10) NOT NULL COMMENT '课程学分',
    course_disabled bool        NOT NULL DEFAULT 0 COMMENT '禁用',
    last_update     TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '更新时间',
    PRIMARY KEY (course_id)
);
CREATE TABLE student
(
    stu_id       BIGINT      NOT NULL COMMENT '主键ID',
    stu_name     VARCHAR(30) NULL     DEFAULT NULL COMMENT '姓名',
    stu_age      INT         NULL     DEFAULT NULL COMMENT '年龄',
    stu_sex      varchar(10) NULL     DEFAULT NULL COMMENT '性别',
    stu_date     date        NULL     DEFAULT NULL COMMENT '入学时间',
    stu_major_id bigint      NULL     DEFAULT NULL COMMENT '专业ID',
    stu_clazz_id BIGINT      NULL     DEFAULT NULL COMMENT '班级ID',
    stu_disabled bool        NOT NULL DEFAULT 0 COMMENT '禁用',
    last_update  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '更新时间',
    PRIMARY KEY (stu_id)
);
CREATE TABLE score
(
    score_id    BIGINT      NOT NULL COMMENT '成绩id',
    score_cid   BIGINT      NOT NULL DEFAULT NULL COMMENT '课程id',
    score_sid   BIGINT      NOT NULL DEFAULT NULL COMMENT '学生id',
    score_num   VARCHAR(10) NULL     DEFAULT NULL COMMENT '成绩',
    last_update TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '更新时间',
    PRIMARY KEY (score_id)
);
CREATE TABLE sys_role
(
    role_id   BIGINT      NOT NULL COMMENT '角色id',
    role_name varchar(10) NOT NULL COMMENT '角色名'
);
CREATE TABLE sys_user
(
    user_id       BIGINT      NOT NULL COMMENT '用户id',
    user_name     VARCHAR(20) NOT NULL COMMENT '用户名',
    user_password varchar(20) NOT NULL COMMENT '密码',
    role_id       BIGINT      NOT NULL COMMENT '角色id',
    PRIMARY KEY (user_id)
);
CREATE TABLE sys_router
(
    router_id        BIGINT       NOT NULL AUTO_INCREMENT,
    parent_id        BIGINT                DEFAULT NULL COMMENT '父级路由 ID',
    router_name      varchar(255) NOT NULL COMMENT '路由名称',
    router_path      varchar(255) NOT NULL COMMENT '路由路径',
    router_component varchar(255)          DEFAULT NULL COMMENT '路由组件',
    router_icon      varchar(255)          DEFAULT NULL COMMENT '路由图标',
    min_role         BIGINT       NOT NULL DEFAULT 1 COMMENT '最小权限',
    PRIMARY KEY (router_id)
);