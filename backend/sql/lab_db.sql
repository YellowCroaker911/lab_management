drop table if exists maintain;
drop table if exists borrow;
drop table if exists course;
drop table if exists lab;
drop table if exists session;
drop table if exists semester;
drop table if exists user;

-- 用户表
create table user
(
	id           	bigint auto_increment comment '用户id' primary key,
	username     	varchar(255) not null unique comment '账号，约定管理员以a开头，学生以s开头，教师以t开头，实验员以l开头',
	password     	varchar(255) not null comment '密码',
	role         	int not null comment '用户角色，0-管理员，1-学生，2-教师，3-实验员',
	name         	varchar(255) comment '姓名',
	major 			varchar(255) comment '专业',
	clazz			varchar(255) comment '班级',
	title 			varchar(255) comment '职称',
	create_time  	datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_time  	datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
	is_delete    	int default 0 not null comment '是否删除'
) comment '用户';

-- 学期表
create table semester
(
	id           	bigint auto_increment comment '学年id' primary key,
	semester		varchar(255) not null unique comment '学期，格式为year1-year2-num（year1-year2为学年，num为1或2）',
	week			varchar(255) not null comment '周',
	create_time  	datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_time  	datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
	is_delete    	int default 0 not null comment '是否删除'
) comment '学年';

-- 节次表
create table session
(
	id           	bigint auto_increment comment '节次id' primary key,
	session 		varchar(255) not null unique comment '节次，格式为num1-num2（表示节次为num1-num2节）',
	create_time  	datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_time  	datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
	is_delete    	int default 0 not null comment '是否删除'
) comment '节次';

-- 实验室表
create table lab
(
	id           	bigint auto_increment comment '实验室id' primary key,
	lab_admin_id	bigint not null comment '实验员id',
	type 			int not null comment '实验室类别，0-软件，1-硬件，2-网络',
	name 		    varchar(255) comment '实验室名称',
	number			varchar(255) unique comment '实验室编号，约定用3位数字表示',
	equipment_num	int default 0 not null comment '设备数量',
	create_time  	datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_time  	datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
	is_delete    	int default 0 not null comment '是否删除',
	FOREIGN KEY		(lab_admin_id) REFERENCES user(id) on delete cascade
) comment '实验室';

-- 课程表
create table course
(
	id           	bigint auto_increment comment '课程id' primary key,
	teacher_id	 	bigint not null comment '教师id',
	lab_id	 	   	bigint default 1 not null comment '实验室id，默认只是占位，已通过后（status=1）分配',
	type 			int not null comment '需求实验室类别，0-软件，1-硬件，2-网络',
	name			varchar(255) not null comment '课程名称',
	semester		varchar(255) not null comment '学期，格式为year1-year2-num（year1-year2为学年，num为1或2）',
	starting_week 	varchar(255) not null comment '起始周',
	ending_week 	varchar(255) not null comment '结束周',
	session 		varchar(255) not null comment '节次，格式为num1-num2（表示节次为num1-num2节）',
	student_num		int not null comment '学生人数',
	major 			varchar(255) comment '学生专业',
	clazz			varchar(255) comment '学生班级',
	status    		int default 0 not null comment '排课状态，0-未排课，1-已排课',
	create_time  	datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_time  	datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
	is_delete    	int default 0 not null comment '是否删除',
	FOREIGN KEY		(teacher_id) REFERENCES user(id) on delete cascade,
	FOREIGN KEY		(lab_id) REFERENCES lab(id) on delete cascade,
	FOREIGN KEY		(semester) REFERENCES semester(semester) on delete cascade,
	FOREIGN KEY		(session) REFERENCES session(session) on delete cascade
) comment '课程';

-- 借用表
create table borrow
(
	id           	bigint auto_increment comment '借用记录id' primary key,
	student_id	 	bigint not null comment '学生id',
	lab_id	 	   	bigint default 1 not null comment '实验室id，默认只是占位，已通过后（status=1）分配',
	reason 			text comment '借用原因',
	semester		varchar(255) not null comment '学期，格式为year1-year2-num（year1-year2为学年，num为1或2）',
	week 			varchar(255) not null comment '周，1-20',
	session 		varchar(255) not null comment '节次，格式为num1-num2（表示节次为num1-num2节）',
	status    		int default 0 not null comment '审核状态，0-未审核，1-通过，2-驳回，3-使用完毕',
	create_time  	datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_time  	datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
	is_delete    	int default 0 not null comment '是否删除',
	FOREIGN KEY		(student_id) REFERENCES user(id) on delete cascade,
	FOREIGN KEY		(lab_id) REFERENCES lab(id) on delete cascade,
	FOREIGN KEY		(semester) REFERENCES semester(semester) on delete cascade,
	FOREIGN KEY		(session) REFERENCES session(session) on delete cascade
) comment '借用';

-- 维修表
create table maintain
(
	id           	bigint auto_increment comment '维修记录id' primary key,
	teacher_id	 	bigint not null comment '教师id',
	lab_id	 	   	bigint default 1 not null comment '实验室id',
	fault_description 			text comment '故障描述',
	maintenance_description 	text comment '维修说明',
	status    		int default 0 not null comment '维修状态，0-未维修，1-维修中，2-已维修',
	create_time  	datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_time  	datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
	is_delete    	int default 0 not null comment '是否删除',
	FOREIGN KEY		(teacher_id) REFERENCES user(id) on delete cascade,
	FOREIGN KEY		(lab_id) REFERENCES lab(id) on delete cascade
) comment '维修';