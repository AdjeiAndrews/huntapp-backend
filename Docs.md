### User Registration
POST https://huntapp-backend-production.up.railway.app/api/auth/register
Content-Type: application/json
```json
{
  "username": "",
  "password": ""
}
```

### User login
POST https://huntapp-backend-production.up.railway.app/api/auth/login
Content-Type: application/json
```json
{
  "username": "",
  "password": ""
}
```

### Create product
POST https://huntapp-backend-production.up.railway.app/api/products
Content-Type: application/json
Authorization: Bearer {{token from login}}
```json
{
  "name": "",
  "tagline": "",
  "description": "",
  "websiteUrl": "",
  "thumbnailUrl": ""
}
```
### Get all products
GET https://huntapp-backend-production.up.railway.app/api/products
Authorization: Bearer {{token from login}}

### Get product by id
GET https://huntapp-backend-production.up.railway.app/api/products/{{id}}
Authorization: Bearer {{token from login}}

### Delete your product
DELETE https://huntapp-backend-production.up.railway.app/api/products/{{id}}
Authorization: Bearer {{token from login}}

### Update your product
PUT https://huntapp-backend-production.up.railway.app/api/products/{{id}}
Content-Type: application/json
Authorization: Bearer {{token from login}}
```json
{
  "name": "",
  "tagline": "",
  "description": "",
  "websiteUrl": "",
  "thumbnailUrl": ""
}
```
### Upvote product
POST https://huntapp-backend-production.up.railway.app/api/products/{{id}}/upvote
Authorization: Bearer {{token from login}}
