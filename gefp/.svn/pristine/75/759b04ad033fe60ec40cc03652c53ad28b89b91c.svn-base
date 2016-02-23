
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
        runwayId int8 not null
    );

    create table plan_stage (
        planId int8 not null,
        stageId int8 not null
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
        address varchar(255) not null,
        cin varchar(255) not null,
        emailid varchar(255) not null,
        enabled boolean,
        f_name varchar(255) not null,
        l_name varchar(255) not null,
        password varchar(255) not null,
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

    create sequence hibernate_sequence minvalue 100;
