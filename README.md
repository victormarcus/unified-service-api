# unified-service-api

# Requirements
1. RDBMS: Mysql
2. Database name: unifieddb
3. Database userModel: root
4. Database password: root
5. Build docker image using docker `docker build -t unified-service-api:1.0.0 .`
6. run docker image `docker run -d --name service_1 -p 8080:8080 unified-service-api:1.0.0`

# Environment vars
BASE_URL = unified-service.free.beeceptor.com

# Example jwt token format  
'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYXVuYWsiLCJleHAiOjE2Njk0MzcxNjUsImlhdCI6MTY2OTQwMTE2NX0.-VSwoTtdKZ1i_HuwjXFlF2sQM0Km25ke2mIrIs9_HDs'

# Refs
http://www.javabyexamples.com/configuring-timeout-for-apache-httpclient-4