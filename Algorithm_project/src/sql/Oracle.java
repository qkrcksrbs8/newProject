package sql;

public class Oracle {
	/* query */
	
	/* 시퀀스 */
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
//	/* 유저 테이블 */
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
//	/* 생성된 테이블에 pk 부여 */
//	ALTER TABLE user_mst
//	ADD CONSTRAINT PK_user_mst primary key(user_no);
//	--
//	/* 유저 테이블 insert */
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
//		,'박찬균'
//		,'01012345678'
//		,'test_id'
//		,'test_id'
//	);
//
//	/* 게시판 */
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
//	/* 생성된 테이블에 pk 부여 */
//	ALTER TABLE notice_mst
//	ADD CONSTRAINT PK_notice_mst primary key(notice_no );
//	/* 생성된 테이블에 참조키 설정 */
//	ALTER TABLE notice_mst
//	ADD CONSTRAINT FK_notice_mst foreign key(user_no)
//	references user_mst(user_no);
//	/* 게시판 insert */
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
//		,'테스트 게시판'
//		,'테스트 내용입니다람쥐'
//		,'test_id'
//		,'test_id'
//	);
//
//
//	/* 로그인 히스토리 */
//	create table login_history(
//		user_no number
//		,user_id varchar2(20) not null
//		,login_no number
//		,login_result varchar2(2)
//		,created_by varchar2(20) not null
//		,created_date timestamp default sysdate not null
//	);
//	/* 생성된 테이블에 pk 부여 */
//	ALTER TABLE login_history
//	ADD CONSTRAINT PK_login_history primary key(login_no );
//	/* 생성된 테이블에 참조키 설정 */
//	ALTER TABLE login_history
//	ADD CONSTRAINT FK_login_history foreign key(login_no)
//	references user_mst(user_no);




}
