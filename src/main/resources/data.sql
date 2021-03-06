-- 권한 등록
INSERT INTO AUTHORITY (AUTHORITY, AUTHORITY_NAME, CREATE_BY, LAST_MODIFIED_BY, CREATED_DATE, LAST_MODIFIED_DATE) VALUES ('ROLE_ADMIN', '관리자', 'admin@naver.com', 'admin@naver.com', NOW(), NOW());
INSERT INTO AUTHORITY (AUTHORITY, AUTHORITY_NAME, CREATE_BY, LAST_MODIFIED_BY, CREATED_DATE, LAST_MODIFIED_DATE) VALUES ('ROLE_USER', '회원', 'admin@naver.com', 'admin@naver.com', NOW(), NOW());

-- 회원 등록(비번 1234)
INSERT INTO MEMBER (NAME, PASSWORD, EMAIL, HP, STATUS, CREATE_BY, LAST_MODIFIED_BY, CREATED_DATE, LAST_MODIFIED_DATE) VALUES ('관리자', '$2a$10$is4cfHcYBgNPhcTMW2e8c.gd0EdqYShhhicWddNX28zfQUQkDJrq2', 'admin@naver.com', '01011112222', 'ACTIVITY', 'admin@naver.com', 'admin@naver.com', NOW(), NOW());
INSERT INTO MEMBER (NAME, PASSWORD, EMAIL, HP, STATUS, CREATE_BY, LAST_MODIFIED_BY, CREATED_DATE, LAST_MODIFIED_DATE) VALUES ('회원', '$2a$10$is4cfHcYBgNPhcTMW2e8c.gd0EdqYShhhicWddNX28zfQUQkDJrq2', 'member@naver.com', '01011112222', 'ACTIVITY', 'member@naver.com', 'member@naver.com', NOW(), NOW());

-- 회원 권한 등록
INSERT INTO MEMBER_AUTHORITY (MEMBER_ID, AUTHORITY_ID) VALUES (1, 1);
INSERT INTO MEMBER_AUTHORITY (MEMBER_ID, AUTHORITY_ID) VALUES (2, 2);

-- 게시판 등록
INSERT INTO BOARD_MANAGER (NAME, DESCRIPTION, BOARD_TYPE, BOARD_USE_YN, COMMENT_USE_YN, CREATE_BY, LAST_MODIFIED_BY, CREATED_DATE, LAST_MODIFIED_DATE) VALUES ('공지사항', '공지사항게시판', 'NOTICE', 'Y', 'Y', 'admin@naver.com', 'admin@naver.com', NOW(), NOW());

-- 테스트 메뉴 등록
INSERT INTO MENU (MENU_ID, CREATED_DATE, LAST_MODIFIED_DATE, CREATE_BY, LAST_MODIFIED_BY, DESCRIPTION, LEVEL, MENU_TYPE, NAME, ORD, USE_YN, PARENT_ID, TOP_ID) VALUES (1, NOW(), NOW(), 'admin@naver.com', 'admin@naver.com', '서울소식', 0, 'MENU', '서울소식', 2, 'Y', null, null);
INSERT INTO MENU (MENU_ID, CREATED_DATE, LAST_MODIFIED_DATE, CREATE_BY, LAST_MODIFIED_BY, DESCRIPTION, LEVEL, MENU_TYPE, NAME, ORD, USE_YN, PARENT_ID, TOP_ID) VALUES (2, NOW(), NOW(), 'admin@naver.com', 'admin@naver.com', '새소식', 1, 'MENU', '새소식', 1, 'Y', 1, 1);
INSERT INTO MENU (MENU_ID, CREATED_DATE, LAST_MODIFIED_DATE, CREATE_BY, LAST_MODIFIED_BY, DESCRIPTION, LEVEL, MENU_TYPE, NAME, ORD, USE_YN, PARENT_ID, TOP_ID) VALUES (3, NOW(), NOW(), 'admin@naver.com', 'admin@naver.com', '서울시 정책 뉴스', 1, 'BOARD', '서울시 정책 뉴스', 2, 'Y', 1, 1);
INSERT INTO MENU (MENU_ID, CREATED_DATE, LAST_MODIFIED_DATE, CREATE_BY, LAST_MODIFIED_BY, DESCRIPTION, LEVEL, MENU_TYPE, NAME, ORD, USE_YN, PARENT_ID, TOP_ID) VALUES (4, NOW(), NOW(), 'admin@naver.com', 'admin@naver.com', '보도해명자료', 1, 'BOARD', '보도해명자료', 3, 'Y', 1, 1);

INSERT INTO MENU (MENU_ID, CREATED_DATE, LAST_MODIFIED_DATE, CREATE_BY, LAST_MODIFIED_BY, DESCRIPTION, LEVEL, MENU_TYPE, NAME, ORD, USE_YN, PARENT_ID, TOP_ID) VALUES (5, NOW(), NOW(), 'admin@naver.com', 'admin@naver.com', '보도자료', 2, 'BOARD', '보도자료', 1, 'Y', 4, 1);
INSERT INTO MENU (MENU_ID, CREATED_DATE, LAST_MODIFIED_DATE, CREATE_BY, LAST_MODIFIED_BY, DESCRIPTION, LEVEL, MENU_TYPE, NAME, ORD, USE_YN, PARENT_ID, TOP_ID) VALUES (6, NOW(), NOW(), 'admin@naver.com', 'admin@naver.com', '해명자료', 2, 'BOARD', '해명자료', 2, 'Y', 4, 1);

INSERT INTO MENU (MENU_ID, CREATED_DATE, LAST_MODIFIED_DATE, CREATE_BY, LAST_MODIFIED_BY, DESCRIPTION, LEVEL, MENU_TYPE, NAME, ORD, USE_YN, PARENT_ID, TOP_ID) VALUES (7, NOW(), NOW(), 'admin@naver.com', 'admin@naver.com', '이벤트 신청', 2, 'BOARD', '이벤트 신청', 3, 'Y', 2, 1);
INSERT INTO MENU (MENU_ID, CREATED_DATE, LAST_MODIFIED_DATE, CREATE_BY, LAST_MODIFIED_BY, DESCRIPTION, LEVEL, MENU_TYPE, NAME, ORD, USE_YN, PARENT_ID, TOP_ID) VALUES (8, NOW(), NOW(), 'admin@naver.com', 'admin@naver.com', '분야별 새소식', 2, 'BOARD', '분야별 새소식', 1, 'Y', 2, 1);
INSERT INTO MENU (MENU_ID, CREATED_DATE, LAST_MODIFIED_DATE, CREATE_BY, LAST_MODIFIED_BY, DESCRIPTION, LEVEL, MENU_TYPE, NAME, ORD, USE_YN, PARENT_ID, TOP_ID) VALUES (9, NOW(), NOW(), 'admin@naver.com', 'admin@naver.com', '이달의 행사 및 축제', 2, 'BOARD', '이달의 행사 및 축제', 2, 'Y', 2, 1);

INSERT INTO MENU (MENU_ID, CREATED_DATE, LAST_MODIFIED_DATE, CREATE_BY, LAST_MODIFIED_BY, DESCRIPTION, LEVEL, MENU_TYPE, NAME, ORD, USE_YN, PARENT_ID, TOP_ID) VALUES (10, NOW(), NOW(), 'admin@naver.com', 'admin@naver.com', '시민참여', 0, 'BOARD', '시민참여', 1, 'Y', null, null);
INSERT INTO MENU (MENU_ID, CREATED_DATE, LAST_MODIFIED_DATE, CREATE_BY, LAST_MODIFIED_BY, DESCRIPTION, LEVEL, MENU_TYPE, NAME, ORD, USE_YN, PARENT_ID, TOP_ID) VALUES (11, NOW(), NOW(), 'admin@naver.com', 'admin@naver.com', '서울시민과의 대화', 1, 'CONTENTS', '서울시민과의 대화', 1, 'Y', 10, 10);


