
    create table authorities_test (
        authorityid_pk int4 not null,
        authority varchar(255),
        primary key (authorityid_pk)
    );

    create table cell (
        cell_id int8 not null,
        plan_id_fk int8 not null,
        runway_fk int8 not null,
        stage_fk int8 not null,
        primary key (cell_id)
    );

    create table checkpoint (
        checkpoint_id int8 not null,
        checkpoint_type_id_fk varchar(255) not null,
        value varchar(255) not null,
        cell_id_fk int8,
        checkpoint_index int4,
        primary key (checkpoint_id)
    );

    create table department (
        dept_no int8 not null,
        department_name varchar(255),
        office varchar(255),
        phone varchar(255),
        current_plan_id_fk int8,
        primary key (dept_no)
    );

    create table persistent_logins (
        persistent_id int8 not null,
        last_used timestamp,
        series varchar(255),
        token varchar(255),
        username varchar(255),
        primary key (persistent_id)
    );

    create table plan (
        plan_id int8 not null,
        plan_name varchar(255),
        published_date timestamp,
        associated_dept_id_fk int8,
        primary key (plan_id)
    );

    create table plan_history (
        id int8 not null,
        from_date timestamp,
        to_date timestamp,
        plan_id_fk int8 not null,
        student_id_fk int8 not null,
        primary key (id)
    );

    create table plan_runway (
        planId int8 not null,
        runwayId int8 not null,
        runway_index int4 not null,
        primary key (planId, runway_index)
    );

    create table plan_stage (
        planId int8 not null,
        stageId int8 not null,
        stage_index int4 not null,
        primary key (planId, stage_index)
    );

    create table privilege (
        privilege_id int4 not null,
        decription varchar(255) not null,
        primary key (privilege_id)
    );

    create table role (
        role_id int4 not null,
        role_name varchar(255),
        primary key (role_id)
    );

    create table roles_privileges (
        privilegeId int4 not null,
        roleId int4 not null,
        primary key (roleId, privilegeId)
    );

    create table runway (
        runway_id int8 not null,
        decription varchar(255),
        primary key (runway_id)
    );

    create table stage (
        stage_id int8 not null,
        description varchar(255),
        primary key (stage_id)
    );

    create table student_checkpoint (
        userId int8 not null,
        checkpointId int8 not null,
        primary key (userId, checkpointId)
    );

    create table user_roles (
        userId int8 not null,
        roleId int4 not null,
        primary key (roleId, userId)
    );

    create table users (
        user_id int8 not null,
        address varchar(255),
        cin varchar(255) not null,
        emailid varchar(255),
        enabled boolean,
        f_name varchar(255) not null,
        l_name varchar(255) not null,
        password varchar(255),
        user_name varchar(255) not null,
        department_id_fk int8 not null,
        official_plan_id_fk int8,
        primary key (user_id)
    );

    create table users_test (
        id int4 not null,
        enabled boolean not null,
        password varchar(255),
        username varchar(255),
        authorityid_fk int4,
        primary key (id)
    );

    alter table cell 
        add constraint UK_8u96bkg1usj8bnf3vpkmi8lt3 unique (runway_fk, stage_fk, plan_id_fk);

    alter table users 
        add constraint UK_ka6m8ghsr7vna1ti6lftwww8o unique (cin);

    alter table users 
        add constraint UK_k8d0f2n7n88w1a16yhua64onx unique (user_name);

    alter table users_test 
        add constraint UK_hn95a8jef61y9iwf7rh45ae6n unique (authorityid_fk);

    alter table cell 
        add constraint FK_7mfxrj5s5ioa5l4nh75xe7ktr 
        foreign key (plan_id_fk) 
        references plan;

    alter table cell 
        add constraint FK_kd96ryid6y6rj6gxn3ympfpyl 
        foreign key (runway_fk) 
        references runway;

    alter table cell 
        add constraint FK_2m6b6yt2rfbadocnqhuspea3s 
        foreign key (stage_fk) 
        references stage;

    alter table checkpoint 
        add constraint FK_r7ivispl65393grilsbpbtsci 
        foreign key (cell_id_fk) 
        references cell;

    alter table department 
        add constraint FK_c175ko5bvxtf7vfbn2gwgofmk 
        foreign key (current_plan_id_fk) 
        references plan;

    alter table plan 
        add constraint FK_cv9rugv2v0yair8dtup91l158 
        foreign key (associated_dept_id_fk) 
        references department;

    alter table plan_history 
        add constraint FK_g4bxsolh1nvgursahsol30bq8 
        foreign key (plan_id_fk) 
        references plan;

    alter table plan_history 
        add constraint FK_l45nfu52heg6btya20k8wit3t 
        foreign key (student_id_fk) 
        references users;

    alter table plan_runway 
        add constraint FK_rd2n5mrpu4dyp3jnk1n7j4ekg 
        foreign key (runwayId) 
        references runway;

    alter table plan_runway 
        add constraint FK_p2ny0xpn3mh2g7g41smbkv02x 
        foreign key (planId) 
        references plan;

    alter table plan_stage 
        add constraint FK_1l3b25d3mbhv851n8xp6ebexs 
        foreign key (stageId) 
        references stage;

    alter table plan_stage 
        add constraint FK_jb2ewda1kdwq6shdorpu7rq9d 
        foreign key (planId) 
        references plan;

    alter table roles_privileges 
        add constraint FK_8v41cqmc9gf5p3cmf5sjd9qqk 
        foreign key (roleId) 
        references role;

    alter table roles_privileges 
        add constraint FK_71bl89vbdxv104ovwet7a8l0m 
        foreign key (privilegeId) 
        references privilege;

    alter table student_checkpoint 
        add constraint FK_ctl6b28luyo7ojpr2j8itjd7w 
        foreign key (checkpointId) 
        references checkpoint;

    alter table student_checkpoint 
        add constraint FK_39o8ja5qg9lpwkjovbvqryylw 
        foreign key (userId) 
        references users;

    alter table user_roles 
        add constraint FK_gtvhfoxqgeb6ghsg59yjakenx 
        foreign key (roleId) 
        references role;

    alter table user_roles 
        add constraint FK_lbvvqmh536gyici9px18cunf6 
        foreign key (userId) 
        references users;

    alter table users 
        add constraint FK_gtnfk30beleksg1p96yp0x40x 
        foreign key (department_id_fk) 
        references department;

    alter table users 
        add constraint FK_bu1n5kvk309ncjkb5rxi36smq 
        foreign key (official_plan_id_fk) 
        references plan;

    alter table users_test 
        add constraint FK_hn95a8jef61y9iwf7rh45ae6n 
        foreign key (authorityid_fk) 
        references authorities_test;


INSERT INTO role (role_id, role_name) VALUES (1, 'ADMIN');
INSERT INTO role (role_id, role_name) VALUES (2, 'ADVISOR');
INSERT INTO role (role_id, role_name) VALUES (3, 'STUDENT');
INSERT INTO privilege (privilege_id, decription) VALUES (1, 'ADD_RUNWAY');
INSERT INTO privilege (privilege_id, decription) VALUES (2, 'REMOVE_RUNWAY');
INSERT INTO privilege (privilege_id, decription) VALUES (3, 'EDIT_RUNWAY');
INSERT INTO privilege (privilege_id, decription) VALUES (4, 'ADD_STAGE');
INSERT INTO privilege (privilege_id, decription) VALUES (5, 'REMOVE_STAGE');
INSERT INTO privilege (privilege_id, decription) VALUES (6, 'EDIT_STAGE');
INSERT INTO privilege (privilege_id, decription) VALUES (7, 'ADD_CHECKPOINT');
INSERT INTO privilege (privilege_id, decription) VALUES (8, 'EDIT_CHECKPOINT');
INSERT INTO privilege (privilege_id, decription) VALUES (9, 'REMOVE_CHECKPOINT');
INSERT INTO privilege (privilege_id, decription) VALUES (10, 'SHOW_CHECKBOX');
INSERT INTO privilege (privilege_id, decription) VALUES (11, 'VIEW_ALL');
INSERT INTO roles_privileges (roleid, privilegeid) VALUES (1, 1);
INSERT INTO roles_privileges (roleid, privilegeid) VALUES (1, 2);
INSERT INTO roles_privileges (roleid, privilegeid) VALUES (1, 3);
INSERT INTO roles_privileges (roleid, privilegeid) VALUES (1, 4);
INSERT INTO roles_privileges (roleid, privilegeid) VALUES (1, 5);
INSERT INTO roles_privileges (roleid, privilegeid) VALUES (1, 6);
INSERT INTO roles_privileges (roleid, privilegeid) VALUES (1, 7);
INSERT INTO roles_privileges (roleid, privilegeid) VALUES (1, 8);
INSERT INTO roles_privileges (roleid, privilegeid) VALUES (1, 9);
INSERT INTO roles_privileges (roleid, privilegeid) VALUES (1, 11);
INSERT INTO roles_privileges (roleid, privilegeid) VALUES (2, 11);
INSERT INTO roles_privileges (roleid, privilegeid) VALUES (3, 10);
INSERT INTO roles_privileges (roleid, privilegeid) VALUES (3, 11);

INSERT INTO department (dept_no, department_name, office, phone) VALUES (1, 'Computer Science', 'Engineering & Technology', '623-857-7896');
INSERT INTO department (dept_no, department_name, office, phone) VALUES (2, 'Electrical Engineering', 'Engineering & Technology', '623-857-2536');
INSERT INTO plan (plan_id,plan_name,published_date,associated_dept_id_fk) VALUES (1,'CS First GEFP','1994-11-29',1);
INSERT INTO plan (plan_id,plan_name,published_date,associated_dept_id_fk) VALUES (2,'ECST First GEFP','1995-04-09',2);
UPDATE  department SET current_plan_id_fk =1 WHERE dept_no = 1 ;
UPDATE  department  SET current_plan_id_fk =2 WHERE dept_no = 2;

INSERT INTO runway (runway_id, decription) VALUES (1, 'Academic Performance');
INSERT INTO runway (runway_id, decription) VALUES (2, 'Career Preparation');
INSERT INTO runway (runway_id, decription) VALUES (3, 'Leadership & Community Engagement');
INSERT INTO stage (stage_id, description) VALUES (6, 'Pre-college (pre-flight checklist)');
INSERT INTO stage (stage_id, description) VALUES (8, 'Sophomore (climbing altitude)');
INSERT INTO stage (stage_id, description) VALUES (9, 'Junior (cruising altitude)');
INSERT INTO stage (stage_id, description) VALUES (10, 'Senior (descent )');
INSERT INTO stage (stage_id, description) VALUES (7, 'Freshman (take-off)');
INSERT INTO stage (stage_id, description) VALUES (11, 'Graduation (Landing)');
INSERT INTO plan_stage (planid, stageid, stage_index) VALUES (1, 6, 0);
INSERT INTO plan_stage (planid, stageid, stage_index) VALUES (1, 7, 1);
INSERT INTO plan_stage (planid, stageid, stage_index) VALUES (2, 9, 0);
INSERT INTO plan_stage (planid, stageid, stage_index) VALUES (2, 10, 1);
INSERT INTO plan_runway (planid, runwayid, runway_index) VALUES (1, 1, 0);
INSERT INTO plan_runway (planid, runwayid, runway_index) VALUES (1, 2, 1);
INSERT INTO plan_runway (planid, runwayid, runway_index) VALUES (1, 3, 2);
INSERT INTO plan_runway (planid, runwayid, runway_index) VALUES (2, 1, 0);
INSERT INTO plan_runway (planid, runwayid, runway_index) VALUES (2, 2 ,1 );
INSERT INTO plan_runway (planid, runwayid, runway_index) VALUES (2, 3, 2);
INSERT INTO cell (cell_id, plan_id_fk, runway_fk, stage_fk) VALUES ('1', 1, 1, 6);
INSERT INTO cell (cell_id, plan_id_fk, runway_fk, stage_fk) VALUES ('2', 1, 2, 6);
INSERT INTO cell (cell_id, plan_id_fk, runway_fk, stage_fk) VALUES ('3', 1, 3, 6);
INSERT INTO cell (cell_id, plan_id_fk, runway_fk, stage_fk) VALUES ('4', 1, 1, 7);
INSERT INTO cell (cell_id, plan_id_fk, runway_fk, stage_fk) VALUES ('5', 1, 2, 7);
INSERT INTO cell (cell_id, plan_id_fk, runway_fk, stage_fk) VALUES ('6', 1, 3, 7);
INSERT INTO cell (cell_id, plan_id_fk, runway_fk, stage_fk) VALUES ('7', 2, 1, 9);
INSERT INTO cell (cell_id, plan_id_fk, runway_fk, stage_fk) VALUES ('8', 2, 2, 9);
INSERT INTO cell (cell_id, plan_id_fk, runway_fk, stage_fk) VALUES ('9', 2, 3, 9);
INSERT INTO cell (cell_id, plan_id_fk, runway_fk, stage_fk) VALUES ('10', 2, 1, 10);
INSERT INTO cell (cell_id, plan_id_fk, runway_fk, stage_fk) VALUES ('11', 2, 2, 10);
INSERT INTO cell (cell_id, plan_id_fk, runway_fk, stage_fk) VALUES ('12', 2, 3, 10);
INSERT INTO checkpoint (checkpoint_id, value, cell_id_fk, checkpoint_type_id_fk, checkpoint_index) VALUES (1, 'Algebra (before Yr 1)', '1', 1,0);
INSERT INTO checkpoint (checkpoint_id, value, cell_id_fk, checkpoint_type_id_fk, checkpoint_index) VALUES (2, 'Pre-calculus (before (Yr 1)', '1', 1,1);
INSERT INTO checkpoint (checkpoint_id, value, cell_id_fk, checkpoint_type_id_fk, checkpoint_index) VALUES (3, 'Attend STEP', '1', 1,2);
INSERT INTO checkpoint (checkpoint_id, value, cell_id_fk, checkpoint_type_id_fk, checkpoint_index) VALUES (4, 'Apply for financial aid and scholarships', '2', 1,0);
INSERT INTO checkpoint (checkpoint_id, value, cell_id_fk, checkpoint_type_id_fk, checkpoint_index) VALUES (5, 'Make a list of questions to ask during orientation and ask them! ', '3', 1,0);
INSERT INTO checkpoint (checkpoint_id, value, cell_id_fk, checkpoint_type_id_fk, checkpoint_index) VALUES (6, 'Math 206 (Calc I)', '4', 1,0);
INSERT INTO checkpoint (checkpoint_id, value, cell_id_fk, checkpoint_type_id_fk, checkpoint_index) VALUES (7, 'Take a personality assessment', '5', 1,0);
INSERT INTO checkpoint (checkpoint_id, value, cell_id_fk, checkpoint_type_id_fk, checkpoint_index) VALUES (8, 'Find out about ECST student organizations at ECST week ', '6', 1,0);
INSERT INTO checkpoint (checkpoint_id, value, cell_id_fk, checkpoint_type_id_fk, checkpoint_index) VALUES (9, 'Math 209 (Calc IV)', '7', 2,0);
INSERT INTO checkpoint (checkpoint_id, value, cell_id_fk, checkpoint_type_id_fk, checkpoint_index) VALUES (10, 'Visit career center and find out what services they offer', '8', 2,0);
INSERT INTO checkpoint (checkpoint_id, value, cell_id_fk, checkpoint_type_id_fk, checkpoint_index) VALUES (11, 'Participate in ECST student council activities', '9', 2,0);
INSERT INTO checkpoint (checkpoint_id, value, cell_id_fk, checkpoint_type_id_fk, checkpoint_index) VALUES (12, 'Complete 200-level engineering courses.', '10', 2,0);
INSERT INTO checkpoint (checkpoint_id, value, cell_id_fk, checkpoint_type_id_fk, checkpoint_index) VALUES (13, 'Re-assess how well Engineering matches your interests, goals, and natural talents', '11', 2,0);
INSERT INTO checkpoint (checkpoint_id, value, cell_id_fk, checkpoint_type_id_fk, checkpoint_index) VALUES (14, 'Assume 1-3 leadership roles', '12', 2,0);
INSERT INTO users (user_id, address, cin, emailid, f_name, l_name, password, user_name,  department_id_fk, official_plan_id_fk,enabled) VALUES (1, 'la', '856982258', 'cysun@calstatela.edu', 'chengyu', 'sun', 'e2fc714c4727ee9395f324cd2e7f331f', 'cysun', 1,1,true);
INSERT INTO users (user_id, address, cin, emailid, f_name, l_name, password, user_name,  department_id_fk, official_plan_id_fk,enabled) VALUES (2, 'la', '856998544', 'tfox@calstatela.edu', 't', 'fox', 'e2fc714c4727ee9395f324cd2e7f331f', 'tfox', 1,1,true);
INSERT INTO users (user_id, address, cin, emailid, f_name, l_name, password, user_name,  department_id_fk, official_plan_id_fk,enabled) VALUES (3, 'la', '856998545', 'jdoe1@calstatela.edu', 'j', 'doe1', 'e2fc714c4727ee9395f324cd2e7f331f', 'jdoe1', 1,1,true);
INSERT INTO users (user_id, address, cin, emailid, f_name, l_name, password, user_name,  department_id_fk, official_plan_id_fk,enabled) VALUES (4, 'la', '856998546', 'jdoe2@calstatela.edu', 'j', 'doe2', 'e2fc714c4727ee9395f324cd2e7f331f', 'jdoe2', 2,2,true);
INSERT INTO users (user_id, address, cin, emailid, f_name, l_name, password, user_name,  department_id_fk, official_plan_id_fk,enabled) VALUES (5, 'la', '34345553', 'misha@calstatela.edu', 'm', 'chandan', 'e2fc714c4727ee9395f324cd2e7f331f', 'misha', 1,1,true);
INSERT INTO users (user_id, address, cin, emailid, f_name, l_name, password, user_name,  department_id_fk, official_plan_id_fk,enabled) VALUES (6, 'la', '34345554553', 'harry@calstatela.edu', 'h', 'harry', 'e2fc714c4727ee9395f324cd2e7f331f', 'harry', 1,1,true);

INSERT INTO user_roles(userid, roleid) VALUES (1, 1);
INSERT INTO user_roles(userid, roleid) VALUES (2, 2);
INSERT INTO user_roles(userid, roleid) VALUES (3, 3);
INSERT INTO user_roles(userid, roleid) VALUES (4, 3);
INSERT INTO user_roles(userid, roleid) VALUES (5, 3);
INSERT INTO user_roles(userid, roleid) VALUES (5, 1);
INSERT INTO user_roles(userid, roleid) VALUES (5, 2);
INSERT INTO user_roles(userid, roleid) VALUES (6, 2);
INSERT INTO user_roles(userid, roleid) VALUES (6, 3);


INSERT INTO student_checkpoint (userid, checkpointid) VALUES (3, 1);
INSERT INTO student_checkpoint (userid, checkpointid) VALUES (4, 9);
INSERT INTO student_checkpoint (userid, checkpointid) VALUES (4, 10);
INSERT INTO student_checkpoint (userid, checkpointid) VALUES (4, 11);
INSERT INTO student_checkpoint (userid, checkpointid) VALUES (4, 12);
INSERT INTO student_checkpoint (userid, checkpointid) VALUES (4, 13);
INSERT INTO student_checkpoint (userid, checkpointid) VALUES (4, 14);


	INSERT INTO authorities_test (authorityid_pk, authority) VALUES (1, 'ROLE_ADMIN');
	INSERT INTO authorities_test (authorityid_pk, authority) VALUES (2, 'ROLE_USER');
	
	INSERT INTO users_test (id, enabled, password, username,authorityid_fk) VALUES (1, true, '1234', 'admin',1);
	INSERT INTO users_test (id, enabled, password, username,authorityid_fk) VALUES (2, true, 'abcd', 'cysun',2);

    create sequence hibernate_sequence minvalue 100;
