Swagger: http://localhost:8080/swagger-ui.html

Authorization: public

for getting token need to authorize a client:
    POST query, with basic auth:
    login: trusted-client
    pass: grub  

POST: /oauth/token?grant_type=password&username=email@mail.com&password=String


registration: public
    *09.10.2017 Stable version :8081*
GET:    /api/reg?username=email@mail.com&password=String
    *In developing*
POST:   /api/reg?username=email@mail.com&password=String&firstName=String&lastName=String&company=String

PUT:   /api/adddata?username=email@mail.com&&firstName=String&lastName=String&company=String

user api: private
GET:    /api/users/all?access_token=String 
GET:    /api/users/byusername?username=email@mail.com&access_token=String
DELETE: /api/users/deluser?username=email@mail.com&access_token=String

email confirmation: public
GET:    /activation?activationCode=String

email send activation code: public
POST:   /sendActivationCode?username=email@mail.com

email send password reminder: public
POST:   /sendPasswordReminder?username=email@mail.com
	
image api: public
GET:    /api/images/all 
GET:    /api/images/byname?name=String 
GET:    /api/image/newimage?name=String&imageUrl=String 
DELETE: /api/images/delimage?name=String

food: public
GET:    /api/food/all
GET:    /api/food/byname?foodName=String
GET:    /api/food/new?foodName=String&category=String
GET:    /api/food/del?foodName=String