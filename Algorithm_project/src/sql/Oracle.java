package sql;

public class Oracle {
	/* query */
	
	/* ������ */
//	CREATE SEQUENCE user_seq
//	  START WITH 1
//	  INCREMENT BY 1
//	  MAXVALUE 10000
//	  MINVALUE 0
//	  NOCYCLE;
//
//	CREATE SEQUENCE notice_seq
//	  START WITH 1
//	  INCREMENT BY 1
//	  MAXVALUE 10000
//	  MINVALUE 0
//	  NOCYCLE;
//
//	CREATE SEQUENCE login_seq
//	  START WITH 1
//	  INCREMENT BY 1
//	  MAXVALUE 10000
//	  MINVALUE 0
//	  NOCYCLE;
//	-- seq_no.NEXTVAL
//
//	/* ���� ���̺� */
//	create table user_mst(
//		user_no number not null
//		,user_id varchar2(20) not null
//		,user_password varchar2(25) not null
//		,user_name varchar2(30) not null
//		,user_phone varchar2(11) not null
//		,last_login_date timestamp default sysdate not null
//		,created_by varchar2(20) not null
//		,created_date timestamp default sysdate not null
//		,last_update_by varchar2(20) not null
//		,last_update_date timestamp default sysdate not null
//	);	
//	/* ������ ���̺� pk �ο� */
//	ALTER TABLE user_mst
//	ADD CONSTRAINT PK_user_mst primary key(user_no);
//	--
//	/* ���� ���̺� insert */
//	insert into user_mst(
//		user_no
//		,user_id
//		,user_password
//		,user_name
//		,user_phone
//		,created_by
//		,last_update_by
//		)
//	values(
//		user_seq.nextval
//		,'test_id'
//		,'1234'
//		,'������'
//		,'01012345678'
//		,'test_id'
//		,'test_id'
//	);
//
//	/* �Խ��� */
//	create table notice_mst(
//		user_no number not null
//		,user_id varchar2(20) not null
//		,notice_no number not null
//		,notice_title varchar2(50) not null
//		,notice_contents varchar2(250) not null
//		,notice_views number default 0
//		,created_by varchar2(20) not null
//		,created_date timestamp default sysdate not null
//		,last_update_by varchar2(20) not null
//		,last_update_date timestamp default sysdate not null
//	);
//	/* ������ ���̺� pk �ο� */
//	ALTER TABLE notice_mst
//	ADD CONSTRAINT PK_notice_mst primary key(notice_no );
//	/* ������ ���̺� ����Ű ���� */
//	ALTER TABLE notice_mst
//	ADD CONSTRAINT FK_notice_mst foreign key(user_no)
//	references user_mst(user_no);
//	/* �Խ��� insert */
//	insert into notice_mst(
//		user_no
//		,user_id
//		,notice_no
//		,notice_title
//		,notice_contents
//		,created_by
//		,last_update_by
//		)
//	values(
//		(select user_no from user_mst where user_id = 'test_id')
//		,'test_id'
//		,notice_seq.nextval
//		,'�׽�Ʈ �Խ���'
//		,'�׽�Ʈ �����Դϴٶ���'
//		,'test_id'
//		,'test_id'
//	);
//
//
//	/* �α��� �����丮 */
//	create table login_history(
//		user_no number
//		,user_id varchar2(20) not null
//		,login_no number
//		,login_result varchar2(2)
//		,created_by varchar2(20) not null
//		,created_date timestamp default sysdate not null
//	);
//	/* ������ ���̺� pk �ο� */
//	ALTER TABLE login_history
//	ADD CONSTRAINT PK_login_history primary key(login_no );
//	/* ������ ���̺� ����Ű ���� */
//	ALTER TABLE login_history
//	ADD CONSTRAINT FK_login_history foreign key(login_no)
//	references user_mst(user_no);




}
