gmoapp
======
A. INTRODUCE
======
This project is using:
Hibernate, Spring, MySQL, Spring security with Oauth2

B. STEP TO START PROJECT
======
1. Require
- Eclipse, M2e, JDK7, tomcat 7, MySQL 5
- REST client plug-in for chrome or firefox
- Missing library: Spring-scurity-oauth2-1.1.0BUILD-SNAPSHOT.jar
2. Import project 
By File->Import->Maven->Exists maven project
3. Configure db setting in gmo-basic project
4. Start using tomcat

C. TEST
======
1. Get access_token from:
POST http://localhost:8080/gmostore-api/oauth/token?grant_type=password&username=sang&password=password&client_id=my-trusted-client
Response:
{
access_token: "64fc5963-7dc5-44b5-a3fc-a91fd830d59a"
token_type: "bearer"
refresh_token: "64d4c65a-6e13-4474-b5cb-c4d004a99d75"
expires_in: 43108
scope: "read trust write"
client_id: "my-trusted-client"
}
2. Access to resources by access_token:
GET http://localhost:8080/gmostore-api/api/user/logging?access_token=64fc5963-7dc5-44b5-a3fc-a91fd830d59a
Response: 
Current user is logging: Sang
3. Get new access_token by refresh_token:
POST http://localhost:8080/sparklr2/oauth/token?grant_type=refresh_token&refresh_token=8018aceb-0466-43bf-ac82-57416f807dd0&client_id=my-trusted-client&client_secret=123
4. Access by authorization_code
POST http://localhost:8080/sparklr2/oauth/token?grant_type=authorization_code&code=e097b9a7-7156-4fdf-92b5-6b6e1da74cb1&client_id=my-trusted-client&client_secret=123&redirect_uri=google.com
