# Spring Rest JWT

Spring Rest JWT provides a implementation of the JSON Web Token under the Spring Framework using HMAC algorithm

## Gradle and Spring Boot 
This rest application is using the Spring Boot Gradle Plugin, allowing you to package executable jar or war archives, run Spring Boot applications and use the dependency management provided by spring-boot-dependencies.

## Json Web Token
JSON Web Token (JWT) is an open standard (RFC 7519) that defines a compact and self-contained way for securely transmitting information between parties as a JSON object. This information can be verified and trusted because it is digitally signed. JWTs can be signed using a secret (with the HMAC algorithm) or a public/private key pair using RSA

JWT is compact because of its smaller size, JWTs can be sent through an URL, POST parameter, or inside an HTTP header. Additionally, the smaller size means transmission is fast.

## How JWT works
The claims in a JWT are encoded as a JSON object that is used as the payload of a JSON Web Signature (JWS) structure or as the plaintext of a JSON Web Encryption (JWE) structure, enabling the claims to be digitally signed or integrity protected with a Message Authentication Code (MAC) and/or encrypted.

## Spring Security
The security of this web application is configured *security.xml*. There are three roles *ROLE_ADMIN*, *ROLE_EDITOR* and *ROLE_STAFF*. There are different endpoints for each role.