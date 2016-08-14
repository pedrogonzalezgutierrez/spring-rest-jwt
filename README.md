# Spring Rest JWT

Spring Rest JWT provides a implementation of the JSON Web Token under the Spring Framework using HMAC algorithm

## Gradle and Spring Boot 
This rest application is using the Spring Boot Gradle Plugin, allowing you to package executable jar or war archives, run Spring Boot applications and use the dependency management provided by spring-boot-dependencies.

## Json Web Token
JSON Web Token (JWT) is an open standard (RFC 7519) that defines a compact and self-contained way for securely transmitting information between parties as a JSON object. This information can be verified and trusted because it is digitally signed. JWTs can be signed using a secret (with the HMAC algorithm) or a public/private key pair using RSA

JWT is compact because of its smaller size, JWTs can be sent through an URL, POST parameter, or inside an HTTP header. Additionally, the smaller size means transmission is fast.

## How JWT works
The claims in a JWT are encoded as a JSON object that is used as the payload of a JSON Web Signature (JWS) structure or as the plaintext of a JSON Web Encryption (JWE) structure, enabling the claims to be digitally signed or integrity protected with a Message Authentication Code (MAC) and/or encrypted.
**Named imports:**

## End Points
**Create a new user: POST /user?username={user}&password={pass}**
```
POST /user?username=pedro&password=pedro
{
  "message": "Welcome pedro"
}
```
```
POST /user?username=pedro&password=pedro
{
  "message": "Validation",
  "items": [
    "The username already exists"
  ]
}
```
```
POST /user?username=&password=po
{
  "message": "Validation",
  "items": [
    "The username is too short",
    "The password is too short"
  ]
}
```

**Retrieve users (ROLE_ADMIN): GET /user?token={validToken}[?page={numPage}&size={numElements}&sort={attribute}]**
```
GET /user
{
  "timestamp": 1471146481761,
  "status": 403,
  "error": "Forbidden",
  "message": "Access Denied",
  "path": "/user"
}
```
```
GET /user?token=MyValid.Token.JWT
{
  "message": "List users",
  "items": {
    "content": [
      {
        "id": 1,
        "username": "pedro"
      },
      {
        "id": 2,
        "username": "user"
      },
      {
        "id": 3,
        "username": "git"
      },
      {
        "id": 4,
        "username": "debian"
      },
      {
        "id": 5,
        "username": "macarena"
      }
    ],
    "last": false,
    "totalElements": 9,
    "totalPages": 2,
    "numberOfElements": 5,
    "sort": null,
    "first": true,
    "size": 5,
    "number": 0
  }
}
```
```
GET /user?page=0&sort=username&size=3&token=MyValid.Token.JWT
{
  "message": "List users",
  "items": {
    "content": [
      {
        "id": 6,
        "username": "betis"
      },
      {
        "id": 4,
        "username": "debian"
      },
      {
        "id": 3,
        "username": "git"
      }
    ],
    "last": false,
    "totalElements": 9,
    "totalPages": 3,
    "numberOfElements": 3,
    "sort": [
      {
        "direction": "ASC",
        "property": "username",
        "ignoreCase": false,
        "nullHandling": "NATIVE",
        "ascending": true
      }
    ],
    "first": true,
    "size": 3,
    "number": 0
  }
}
```