# 일정 관리 앱 Develop
2025.05.19~2025.05.26


## 사용한 기술
- Java
- Spring Boot
- JPA


## API 명세서
https://documenter.getpostman.com/view/44682451/2sB2qcCgVz


## ERD
###일정

| 필드명     | 타입           | 제약 조건                    | 설명                              |
|------------|----------------|-------------------------------|-----------------------------------|
| id         | Long           | PK, Auto Increment            | 기본 키                           |
| user       | User           | FK (`user_id`)                | 작성자 (다대일 연관관계)          |
| title      | String         | NOT NULL                      | 일정 제목                         |
| content    | String         | (columnDefinition = longtext) | 일정 내용                         |
| createdAt  | LocalDateTime  | NOT NULL, updatable = false   | 생성 시간 (`@CreatedDate`)        |
| updatedAt  | LocalDateTime  |                               | 수정 시간 (`@LastModifiedDate`)   |

### 유저

| 필드명     | 타입           | 제약 조건                    | 설명                          |
|------------|----------------|-----------------------------|---------------------------------|
| id         | Long           | PK, Auto Increment          | 기본 키                         |
| name       | String         | NOT NULL                    | 사용자 이름                     |
| email      | String         | NOT NULL                    | 사용자 이메일                   |
| password   | String         | NOT NULL                    | 사용자 비밀번호                 |
| createdAt  | LocalDateTime  | NOT NULL, updatable = false | 생성 시간 (`@CreatedDate`)      |
| updatedAt  | LocalDateTime  |                             | 수정 시간 (`@LastModifiedDate`) |


## 기능 소개
### Lv1. 일정 CRUD
- 일정은 `작성 유저명`, `할일 제목`, `할일 내용`, `작성일`, `수정일` 필드를 가지도록 설정
- 일정을 생성, 전체 조회, 선택 조회, 수정, 삭제할 수 있도록 구현

### Lv2. 유저 CRUD
- 유저는 `유저명`, `이메일`, `비밀번호`, `작성일`, `수정일` 필드를 가지도록 설정
- 유저를 생성, 조회, 수정, 삭제할 수 있도록 구현
- 일정이 `작성 유저명` 필드 대신 유저 고유 식별자 필드를 가지도록 수정

### Lv3. 회원가입
- (회원가입은 유저의 생성과 동일하여 별도의 구현을 하지 않았습니다.)

### Lv4. 로그인(인증)
- Cookie/Session을 활용해 로그인 기능을 구현
- Session을 삭제하는 방식으로 로그아웃 기능도 구현
- 회원가입과 로그인을 제외한 경로에서는 로그인한 사용자인지 필터링하는 기능을 구현
- 일정과 유저를 수정 및 삭제할 때 로그인한 사용자의 경로인지 필터링하는 기능을 구현

### Lv5. 다양한 예외처리 적용
- Bean Validation을 사용하여 요청 받은 값이 적절한지 검증
- 기존의 ResponseStatusException들을 커스텀 예외 클래스로 분리하고 GlobalExceptionHandler에서 처리하도록 수정


## 실행 방법
1. src > main > java > com > example > scheduleapi > ScheduleApiDevelopApplication...main()을 실행한다.
2. Postman을 사용하여 API 명세서를 토대로 요청을 보내면 응답을 받을 수 있다.
