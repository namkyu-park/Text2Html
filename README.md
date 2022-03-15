# Text2Html
Text를 HTML로 변환하기 위해 만든 소스

## 개발 목적
웹소설 등록 시 text 형식으로 등록받고 이를 HTML 형식으로 저장하여 EPub viewer 또는 브라우저를 이용하여 보기 위해 개발함
기존 < br > tag 대신 newline을 < p > tag로 처리하는 방식으로 변경함
< p > 안에 너무 많은 텍스트가 들어갈 경우 epub 뷰어에서 성능적으로 문제가 발생될 수 있음 

### 참고
웹소설 뷰어는 웹뷰를 이용하여 개발되었고 EPub 뷰어와 같이 다양한 부가 기능을 사용자에게 제공하기 어렵다. (ex. TTS, 책갈피, 등)
