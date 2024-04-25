use testdb;

-- 멤버
create table member (
   member_idx       int            primary key auto_increment,
    member_id          varchar(30)    not null unique,
    member_pw       varchar(30)    not null,
    member_name      varchar(20)     not null,
    member_phone     varchar(20)    not null,
    member_date      date          not null,
    member_role      int            check(member_role in (1, 2)) not null default 1
);

INSERT INTO member (member_id, member_pw, member_name, member_phone, member_date, member_role)
VALUES ('manager','1','manager','1',CURDATE(),'2');
INSERT INTO member (member_id, member_pw, member_name, member_phone, member_date, member_role)
VALUES ('member','1','member','1',CURDATE(),'1');



-- 도서
CREATE TABLE book
(
  book_idx       INT         primary key auto_increment,
  book_name    VARCHAR(30) NOT NULL,
  book_author    VARCHAR(30) NOT NULL,
  book_pub       VARCHAR(30) NOT NULL,
  book_year    DATE      NOT NULL,
  book_genre    VARCHAR(10)   NOT NULL,
  book_ISBN      VARCHAR(13)   NOT NULL,
  book_price   INT         NOT NULL,
  book_rent CHAR NOT NULL DEFAULT 'Y' CHECK (book_rent IN ('Y', 'N'))
);

INSERT INTO book (book_name, book_author, book_pub, book_year, book_genre, book_ISBN, book_price, book_rent)
VALUES ('노인과바다','어니스트 헤밍웨이','어니스트 에디스코','1972-4-1', '세계문학','9788901147453','8500','N');
INSERT INTO book (book_name, book_author, book_pub, book_year, book_genre, book_ISBN, book_price, book_rent)
VALUES ('연금술사','파울로 코엘료','문학동네', '2001-6-17', '판타지','9771228246438','7000','Y');
INSERT INTO book (book_name, book_author, book_pub, book_year, book_genre, book_ISBN, book_price, book_rent)
VALUES ('데일 카네기 인간관계론','데일 카네기','영신사', '2019-10-7', '인문학','9791187182749','11500','N');
INSERT INTO book (book_name, book_author, book_pub, book_year, book_genre, book_ISBN, book_price, book_rent)
VALUES ('SQL 첫걸음','아사이 아츠시','한빛미디어', '2015-11-1', '전공서적','9788968482311','22000','N');
INSERT INTO book (book_name, book_author, book_pub, book_year, book_genre, book_ISBN, book_price, book_rent)
VALUES ('쉽게 배우는 자바 프로그래밍','우종정','한빛아카데미', '2020-11-22', '전공서적','9791156645146','29000','N');
INSERT INTO book (book_name, book_author, book_pub, book_year, book_genre, book_ISBN, book_price, book_rent)
VALUES ('쉽게 배우는 AWS AI 서비스','피터 엘거, 오언 셔너히','한빛미디어', '2022-4-15', '전공서적','9791162245521','30000','N');
INSERT INTO book (book_name, book_author, book_pub, book_year, book_genre, book_ISBN, book_price, book_rent)
VALUES ('TOEFL ESSAY는 정답이 없다','킴홍','이앤엠 리서치', '2006-4-10','언어','9589127841255','17000','N');
INSERT INTO book (book_name, book_author, book_pub, book_year, book_genre, book_ISBN, book_price, book_rent)
VALUES ('어린왕자','앙투안 드 생텍쥐페리','갈리마르', '1943-4-1','소설','9849127841255','10000','N');
INSERT INTO book (book_name, book_author, book_pub, book_year, book_genre, book_ISBN, book_price)
VALUES ('어린왕자','앙투안 드 생텍쥐페리','갈리마르', '1943-4-1','소설','9849127841255','10000');

commit;

-- 대여

create table if not exists rental
(rental_idx int primary key auto_increment,
rental_period date not null,
rental_return date not null,
rental_latefee int ,
member_idx int,
constraint foreign key(member_idx) references member(member_idx),
book_idx int,
constraint foreign key(book_idx) references book(book_idx)
);


drop table book;
drop table member;
drop table rental;
select * from book;
select * from rental;
select * from member;
delete from book;
