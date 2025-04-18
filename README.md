create dir resources/application.yaml
Skeleton:
"
  server:
  port: 8080
  servlet:
    context-path: /store

spring:
  datasource:
    url: "{your-prefered-db}"
    username: 
    password: 
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
    database-platform: org.hibernate.dialect.PostgreSQLDialect
jwt:
  signer-key: "{your-signer-key}"
  valid-duration: 30 #minutes
  refreshable-duration: 120 #days (usually days or weeks)

validation:
  constraint:
    password:
      reg-ex: "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,16}$"

security:
  password:
    default: "123456789" #the default password

"
