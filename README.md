# 기본 개발 가이드

1. Spring Boot 프로젝트 구성하기
	1. 스프링 부트 시작 페이지 가기
		* [Spring Initializer](https://start.spring.io/)
	2. 세부 세팅 체크
		* Gradle Project
		* java
		* 2.6.4
		* jar
		* java 11
	3. Dependencies 고르기
		* Spring Web
		* H2
		* Mustache
		* Mybatis
	4. 다운로드 후, IntelliJ로 프로젝트 오픈
		* Gradle Project 로 인식 후 라이브러리 다운로드도 진행.
2. Database 구성
	1. H2 다운로드 페이지 가기
		* [H2] ([Archive Downloads](https://www.h2database.com/html/download-archive.html)
	2. 1.4 버전으로 다운로드 및 설치
	3.  H2 실행하기
		* Window는 바로기기 존재
		* 혹, 없다면 H2 설치 디렉터리 내 bin 폴더에서 bat 파일 실행
	4. 생성하기
		* jdbc url 지정
			* 예시 : jdbc:h2:~/assignment
	5. 테이블 생성하기
		* 작성한 erd를 통해서 DDL 추출
		* 예시
```sql
CREATE TABLE ACCOUNT (
	ACCOUNT_ID INT AUTO_INCREMENT,
  NICKNAME VARCHAR(50) NOT NULL UNIQUE,
  KOR_NAME VARCHAR(50) NOT NULL,
  TEL_NUM  VARCHAR(30) NOT NULL,
  STATE    VARCHAR(10) NOT NULL,
  CREATED_AT DATETIME NOT NULL,
  MODIFIED_AT DATETIME NOT NULL
);
```
3. 설계하기
    1. 요구사항 도출
        * 간단한 사용자 관리 페이지
    2. 설계
        * 화면
            * 사용자 관리 페이지 
            * 화면을 직접 그려서 준비.
        * 관련 데이터 모델링
            * 해당 과정에서 erd 작성 및 ddl 생성
            * 모델링 기진행되어, Database 구성에서 선진행.
        * 서비스
            * 필요한 기능 등을 나열하고, I/O를 정의함.
            * 예시
                * 사용자 조회
                    * Input
                        * nickname
                    * output
                        * accountId
                        * nickname
                        * korName
                        * telNum
                        * state
                * 사용자 추가
                    * Input
                        * nickname
                        * korName
                        * telNum
                * 사용자 삭제
                    * Input
                        * accountId
        3. 개발
            1. Application.propertis 세팅
                * db 연결에 대한 설정
                * mybatis 연동에 대한 세팅
            2. 

4. 주의사항
    build.gradle
   	resources 폴더 (시맨틱 ui, application properties 중요중요, mybatis-xml)