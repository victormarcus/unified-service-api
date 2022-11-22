# unified-service-api

# Requirements
1. RDBMS: Mysql
2. Database name: unifieddb
3. Database user: root
4. Database password: root
5. Build docker image using docker `docker build -t unified-service-api:1.0.0 .`
6. run docker image `docker run -d --name service_1 -p 8080:8080 unified-service-api:1.0.0`

# Environment vars
BASE_URL = unified-service.free.beeceptor.com

# Refs
http://www.javabyexamples.com/configuring-timeout-for-apache-httpclient-4