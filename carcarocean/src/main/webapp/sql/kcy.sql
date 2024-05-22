--공지사항
CREATE TABLE notice (
    notice_num NUMBER NOT NULL,
    notice_title VARCHAR2(150) NOT NULL,
    notice_content CLOB NOT NULL,
    notice_photo VARCHAR2(400),
    notice_impt NUMBER(1) DEFAULT 0 NOT NULL,
    notice_hit NUMBER DEFAULT 0 NOT NULL,
    notice_reg DATE DEFAULT SYSDATE NOT NULL,
    notice_modify DATE,
    CONSTRAINT notice_pk PRIMARY KEY (notice_num)
);
CREATE SEQUENCE notice_seq;

--자유게시판
CREATE TABLE board(
	board_num NUMBER NOT NULL,
	mem_num NUMBER NOT NULL,
	board_title VARCHAR2(150) NOT NULL,
	board_content CLOB NOT NULL,
	board_photo VARCHAR2(400),
	board_hit NUMBER DEFAULT 0 NOT NULL,
	board_report NUMBER DEFAULT 0 NOT NULL,
	board_reg DATE DEFAULT SYSDATE NOT NULL,
	board_modify DATE DEFAULT,
	CONSTRAINT board_pk PRIMARY KEY (board_num),
 	CONSTRAINT member_fk FOREIGN KEY (mem_num) REFERENCES member (mem_num)
);
CREATE SEQUENCE board_seq;

--자유게시판 댓글
CREATE TABLE board_comment(
	bor_comm_num NUMBER NOT NULL,
	board_num NUMBER NOT NULL,
	mem_num NUMBER NOT NULL,
	bor_comm_content CLOB NOT NULL,
	bor_comm_report NUMBER DEFAULT 0 NOT NULL,
	bor_comm_reg DATE DEFAULT SYSDATE NOT NULL,
	CONSTRAINT board_comment_pk PRIMARY KEY (bor_comm_num),
 	CONSTRAINT board_fk1 FOREIGN KEY (board_num) REFERENCES board (board_num),
 	CONSTRAINT member_fk2 FOREIGN KEY (mem_num) REFERENCES member (mem_num)
);
CREATE SEQUENCE board_comment_seq;

--구매 후기 게시판
CREATE TABLE b_re(
	b_re_num NUMBER NOT NULL,
	buy_num NUMBER NOT NULL,
	mem_num NUMBER NOT NULL,
	car_num NUMBER NOT NULL,
	checker_num NUMBER NOT NULL,
	b_re_title VARCHAR2(150) NOT NULL,
	b_re_content CLOB NOT NULL,
	b_re_photo VARCHAR2(400) NOT NULL,
	b_re_reg DATE DEFAULT SYSDATE NOT NULL,
	b_re_modify DATE,
	CONSTRAINT b_re_pk PRIMARY KEY (b_re_num),
 	CONSTRAINT buy_fk1 FOREIGN KEY (buy_num) REFERENCES buy (buy_num),
 	CONSTRAINT member_fk2 FOREIGN KEY (mem_num) REFERENCES buy (mem_num),
 	CONSTRAINT car_fk3 FOREIGN KEY (car_num) REFERENCES buy (car_num),
 	CONSTRAINT checker_fk4 FOREIGN KEY (checker_num) REFERENCES checker (checker_num)
);
CREATE SEQUENCE b_re_seq;

--구매 후기 게시판 댓글
CREATE TABLE b_re_comment(
	b_re_comm_num NUMBER NOT NULL,
	b_re_num NUMBER NOT NULL,
	mem_num NUMBER NOT NULL,
	b_re_comm_content CLOB NOT NULL,
	b_re_comm_report NUMBER DEFAULT 0 NOT NULL,
	b_re_comm_reg DATE NOT NULL,
	CONSTRAINT b_re_comment_pk PRIMARY KEY (b_re_comm_num),
 	CONSTRAINT b_re_fk1 FOREIGN KEY (b_re_num) REFERENCES b_re (b_re_num),
 	CONSTRAINT member_fk2 FOREIGN KEY (mem_num) REFERENCES b_re (mem_num)
);
CREATE SEQUENCE b_re_comment_seq;



