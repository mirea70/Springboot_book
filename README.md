<div align=center> 
    <h1> 📖 SpringBoot 기반 게시판 제작 </h1>
</div>
<p align="center"> 
  <img src="https://user-images.githubusercontent.com/101246806/206641463-25ce3cc5-0445-4d8e-a6db-a677e3399aa2.png" width= "800"/>
</p>
<div align=center> 
  <div align=left>
  <h3> - ‘스프링 부트와 AWS로 혼자 구현하는 웹서비스’ 라는 책을 학습하며 간단한 게시판을 만든 프로젝트입니다. <br/>
       - SpringBoot 기반으로 서버 애플리케이션을 만들고, mustache를 이용해 간단한 뷰도 제작하였습니다. <br/> 
       - AWS를 활용하여 배포, CI/CD 구현 및 무중단 배포까지 진행하였습니다. <br/>
       - Spring Security와 OAuth2, Session을 활용해 소셜 로그인 기능을 구현했습니다. <br/>
       - JPA를 활용하여 게시판의 기본 CRUD를 구현하였습니다.
    <h3>
    </div>
</div>

# 💨 구현한 기능
### - 소셜 로그인 기능
<p align="left"> 
  <img src="https://user-images.githubusercontent.com/101246806/211680453-62031632-6606-4a78-8834-4a3103295678.png" width = "600">
</p>

- 메인화면에서 Google Login 버튼을 클릭하면 위처럼 소셜 인증이 시작됩니다.
- 인증이 완료되면 위처럼 사용자명이 로그인 버튼위치에 나타나고, Logout 버튼이 생성됩니다.
- Naver 로그인도 구현하였지만 과정이 같으므로 사진은 Google 로그인만 포함하였습니다.

 <br/>

### - 글 등록 기능
<p align="left"> 
  <img src="https://user-images.githubusercontent.com/101246806/211680596-fe978a00-791b-420e-99c2-28db2cecf115.png" width = "600">
</p> 

- 글 등록 버튼을 누르면 글 등록 페이지로 이동 됩니다.
- 제목, 작성자, 내용을 입력 후 등록 버튼을 누르면

 <br/>

### - 글 수정 기능
<p align="left"> 
  <img src="https://user-images.githubusercontent.com/101246806/211680675-cdf44527-17b7-4d7c-80f3-8c8aa2df15cc.png" width = "600">
</p> 

- 등록된 게시글의 제목을 누르면
- 해당 게시글의 수정페이지로 이동되며, 수정이가능합니다.
- 제목과 내용만 수정이 가능하고, 글 번호와 작성자는 입력 자체가 불가능합니다.

<br/>

### - 글 삭제 기능
<p align="left"> 
  <img src="https://user-images.githubusercontent.com/101246806/211680865-6e2fb5c5-de65-4e12-ae74-f0bf8f89e0d9.png" width = "600">
</p> 

- 게시글 수정 페이지에서 삭제 버튼을 누르면 알림 문구가 뜨며, 확인을 누르면 메인 페이지로 이동됩니다.

<div align="center"> 
    <h3> 프로젝트 기간 : 2022.06.15 ~ 2022.07.14 </h3>
</div>
<br/>
  
# :sparkler: 기술스택
<p align="left"> 
  <img src="https://user-images.githubusercontent.com/101246806/206643092-c99192df-1518-4b23-8145-51ccab141ebe.png" width= "800"/>
</p>
  
<br/>

