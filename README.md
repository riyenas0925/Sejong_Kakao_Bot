<h1 align="center">Sejong Kakao Bot</h1>

[![Build Status](http://vscode.riyenas.dev/buildStatus/icon?job=Sejong_Kakao_Bot%2Fdevelop)](http://vscode.riyenas.dev/job/Sejong_Kakao_Bot/job/develop/)

<p align="center">
  <img src="https://user-images.githubusercontent.com/32615702/71518315-929bbd00-28f5-11ea-8270-2ccd38f72422.gif">
</p>

> :warning: 현재 새로운 버전(kakao i openbuilder 2.0)은 녹화 중에 있으며 [파이썬 장고로 개발한 이전 버전(Kakao Auto Reply v1.0)](https://github.com/riyenas0925/Sejong_Univ_Chef_Bot)영상 입니다.

> 추가되었으면 하는 기능이 있으면 깃허브 이슈또는 riyenas0925@gmail.com으로 보내주시면 감사하겠습니다.

## 개발자
* [@riyenas0925](https://github.com/riyenas0925) - 세종대학교 학술동아리 인터페이스 30기

## 카카오 채널
* 운영 채널 : 이전에 운영하던 세종냥이 채널로 연결할 예정
* 개발 채널 : [세종냥이-개발 채널 추가하기](http://pf.kakao.com/_xjXxakxb)

## 기술 스택
* Language : Java 11
* Web Framework : SpringBoot 2.3.0
* IDE : Intellij IDEA
* CI/CD : Jenkins 2.254
* Dependency Management : gradle-6.4.1

## 기능 
| # | 기능 명 | 기능 | ```키워드``` | 사용예시 |
|:--------:|:--------:|:--------:|:--------:|:--------:|
| 1 | 학생식당 메뉴 | 학생식당별 메뉴 표시 | ```학생회관```, ```군자관```, ```우정당```... | ```군자관``` 메뉴 알려줘|
| 2 | 미세먼지 | 지역별 미세먼지 표시 | ```부평```,```광진구``` ... | ```광진구``` 미세먼지 알려줘 |
| 3 | 공지사항 | 키워드 별로 최근 5개 공지 표시 | ```일반```, ```학사```, ```입학```...| ```입학``` 공지사항 알려줘 |

> 자세한 내용은 아래의 세부 기능을 참고해 주세요  

#### 공지사항
아래의 키워드에 해당하는 공지사항을 크롤링 하여 최근 5개의 목록으로 반환한다.
* 카카오톡 응답 타입 : [ListCard](https://i.kakao.com/docs/skill-response-format#listcard)
* 키워드 : 일반(기본값), 입학, 학사, 취업, 장학, 교내모집

#### 학생식당 메뉴
* 카카오톡 응답 타입 
  * 학생회관 : [SimpleText](https://i.kakao.com/docs/skill-response-format#simpletext)
  * 군자관, 가든뷰 : [Carousel](https://i.kakao.com/docs/skill-response-format#carousel)
* 키워드 : 학생식당(기본값), 군자관, 우정당, 가든뷰
  
#### 미세먼지
* 카카오톡 응답 타입 : [Carousel](https://i.kakao.com/docs/skill-response-format#carousel)
* 키워드 : 광진구(기본값), 부평 ....

## API
* [카카오 i 오픈빌더 스킬 API](https://i.kakao.com/docs/getting-started-overview#%EC%98%A4%ED%94%88%EB%B9%8C%EB%8D%94-%EC%86%8C%EA%B0%9C)
* [미세먼지 API](https://www.data.go.kr/data/15000581/openapi.do)

## 크롤링 사이트
* [학생식당 메뉴](http://m.sejong.ac.kr/front/cafeteria2.do)
* [교내 공지사항](http://sejong.ac.kr/community/index.html)
