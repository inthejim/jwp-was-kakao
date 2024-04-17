# 웹 애플리케이션 서버

## 진행 방법

* 웹 애플리케이션 서버 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정

* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

# 기능 요구사항 - 1단계

- [X] GET /index.html을 응답한다.
    - [X] 요청에 대한 모든 메시지를 출력한다.

- [X] Request line 에서 Path를 추출한다.

- [X] Path에 해당하는 파일을 읽어 응답한다.
    - [X] 파일을 읽는다.
    - [X] 파일을 요청에 따라 응답한다.

- [X] CSS 지원하기
    - [X] StyleSheet 파일을 지원하도록 구현한다.

- [X] QueryString 파싱
    - [X] 회원 가입 매뉴를 클릭하면 `http://localhost:8080/user/form.html` 로 이동하면서 회원가입을 한다.
    - [X] 회원가입을 하면 사용자가 입력한 값이 서버에 전달된다.
    - [X] HTML과 URL을 비교하고, 사용자가 입력한 값을 파싱해 model.User 클래스에 저장한다.
    - [X] 회원가입할 때 생성한 User 객체를 `DataBase.addUser()` 메서드를 활용해 RAM 메모리(DB)에 저장한다.

- [X] POST 방식으로 회원가입
    - [X] `http://localhost:8080/user/form.html` 파일의 form 태그 method를 `get`에서 `post`로 수정한 후 회원가입 기능이 정상적으로 동작하도록 구현한다.
- [X] Redirect 기능 구현
    - [X] 현재는 회원가입을 완료 후, URL이 /user/create 로 유지되는 상태로 읽어서 전달할 파일이 없다. redirect 방식처럼 회원가입을 완료한 후 index.html로 이동해야 한다.

