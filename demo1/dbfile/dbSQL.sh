
-----设计表果然是个看起来简单，但实际上很容易就返修的事情
create table OPUS_DETAIL
(
   OPUS_DETAIL_ID     NUMBER(9,0)          not null,
   OPUS_DETAIL        BLOB                 not null,
   constraint PK_DEMO_OPUS primary key (OPUS_DETAIL_ID)
)

create table DEMO_OPUS
(
   OPUS_ID            NUMBER(9,0)          not null,
   OPUS_NAME          VARCHAR2(120)        not null,
   OPUS_DETAIL_ID     NUMBER(9,0)          not null,
   STATE              CHAR(1)              not null,
   STATE_DATE         DATE                 not null,
   constraint PK_OPUS primary key (OPUS_ID),
   CONSTRAINT FK_OPUS_DETAIL_ID FOREIGN KEY(OPUS_DETAIL_ID) REFERENCES OPUS_DETAIL(OPUS_DETAIL_ID)
)

create table DEMO_PERSON
(
   PERSON_ID             NUMBER(9,0)          not null,  --主键
   PERSON_NAME           VARCHAR2(120)        not null,
   OPUS_ID        NUMBER(9,0),    --做外键
   PERSON_PHOTO_ID       NUMBER(4,0),    --改成4，0
   PERSON_MV_ID          NUMBER(3,0),
   PERSON_DESC           CLOB,
   STATE                 CHAR(1)              not null,
   STATE_DATE            DATE                 not null,
   constraint PK_PERSON primary key (PERSON_ID),
   CONSTRAINT FK_OPUS_ID FOREIGN KEY(OPUS_ID) REFERENCES DEMO_OPUS(OPUS_ID)
)

create table DEMO_USER
(
   USER_ID             NUMBER(9,0)          not null,  --主键
   USER_NAME           VARCHAR2(120)        not null,
   STATE               CHAR(1)              not null,--0 1 2
   CREATE_DATE         DATE                 not null,
   STATE_DATE          DATE                 not null,
   EMAIL               VARCHAR2(120)        not null,
   LOST_FOUND_KEY      NUMBER(9,0)    not null,
   constraint PK_DEMO_USER primary key (USER_ID)
)
ALTER TABLE DEMO_USER ADD CONSTRAINT EMAIL UNIQUE (EMAIL);  --创建唯一约束

create table DEMO_USER_DETAIL
(
   USER_ID     NUMBER(9,0)          not null,
   USER_DETAIL        BLOB          not null,
   CONSTRAINT FK_USER_ID FOREIGN KEY(USER_ID) REFERENCES DEMO_USER(USER_ID)
)


create table DEMO_RESOURCE
(
   RESOURCE_ID         NUMBER(9,0)          not null,  --主键
   RESOURCE_NAME       VARCHAR2(120)        not null,
   USER_ID             NUMBER(9,0)          not null,  
   USER_NAME           VARCHAR2(120)        not null,
   RESOURCE_DESC       VARCHAR2(200),
   constraint PK_RESOURCE_ID primary key (RESOURCE_ID),
   CONSTRAINT FK_DEMO_RESOURCE_USER_ID FOREIGN KEY(USER_ID) REFERENCES DEMO_USER(USER_ID)
)

alter table DEMO_RESOURCE modify RESOURCE_DESC VARCHAR2(1000)

create table DEMO_RESOURCE_DETAIL
(
   RESOURCE_ID         NUMBER(9,0)          not null,  --主键
   CREATE_DATE         DATE                 not null,
   RESOURCE_SIZE       VARCHAR2(50),
   HOT                 NUMBER(9,0), 
   RESOURCE_LINK       VARCHAR2(100),
   DOWNLOAD_ABLE       CHAR(1)              not null,
   RESOURCE_FILE       VARCHAR2(200),
   CONSTRAINT FK_RESOURCE_DETAIL_RESOURCE_ID FOREIGN KEY(RESOURCE_ID) REFERENCES DEMO_RESOURCE(RESOURCE_ID)
)

drop table DEMO_RESOURCE_DETAIL;



select a.*, rowid from OPUS_DETAIL a;
select a.*, rowid from DEMO_OPUS a;
select a.*, rowid from DEMO_PERSON a;

select a.*, rowid from DEMO_USER a;
select a.*, rowid from DEMO_USER_DETAIL a;
select a.*, rowid from DEMO_RESOURCE a;
select a.*, rowid from DEMO_RESOURCE_DETAIL a;





------------今天业务需求，写了一个触发器如下，这里记录下：
DELETE FROM bil_job_service WHERE service_id not IN (SELECT min(service_id) FROM bil_job_service GROUP BY service_name)

select count(service_id)  from bil_job_service ;
SELECT count(service_name) FROM (SELECT DISTINCT service_name FROM bil_job_service);


drop trigger job_name_duplicate_check

create or replace trigger job_name_duplicate_check after insert on bil_job_service
declare
  allName int;
  distinctnName int;
begin
  select count(service_id) into allName from bil_job_service ;
  SELECT count(service_name) into distinctnName  FROM (SELECT DISTINCT service_name FROM bil_job_service);
  if (allName-distinctnName) > 0 then
  DELETE FROM bil_job_service WHERE service_id not IN (SELECT min(service_id) FROM bil_job_service GROUP BY service_name);
  DBMS_OUTPUT.put_line ('The trigger job_name_duplicate_check finds that after the insert operation on the table bil_job_service, there are two or more groups of data with exactly the same service_name.');
  DBMS_OUTPUT.put_line ('For the table bil_job_service, there should be no two or more groups of data with exactly the same service_name');
  DBMS_OUTPUT.put_line ('The trigger job_name_duplicate_check has automatically deleted the data with large service_id in two or more groups of data, and only retains the data with the smallest service_id.');
  end if;
end job_name_duplicate_check;