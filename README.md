# The CookBook App

## Why?
My brother and I love to cook. You would find us online often looking for new things to cook. Upon finding tasty a recipe we would take note of it in a small notepad. Not the smart choice ,but it was what we had to work with meantime. Eventually we lost the notebook and resulting in loosing recipes.

## Creation...
Using some web development skills I learned in the past year. I created my own application to solve this issue. Now, it didnt bring back our lost recipes ,but it will prevent us from ever loosing them while add some other cool features. Using Java and Spring the application implements:

#### RESTful Back End:
- Java
- Thymeleaf
- Spring Security
- Spring Boot
- Hibernate
- PostgreSQL
- Docker
- JSON Web Token (JWT)
  

#### Front End:
- Bootstrap
- HTML5
- CSS
- JavaScript
- Jquery

Using these technologies I implemented these features:

## Authenticate/Register
### Login
```http
  POST /api/v1/auth/login
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `username` | `String` | The user's username|
| `password` | `String` | The user's password|

### Register

```http
  POST /api/v1/auth/register
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `email` | `String` | The user's email|
| `username` | `String` | The user's username|
| `password` | `String` | The user's password|

## Manage Cookbooks

### Create Cookbook
```http
  POST /api/v1/cookbook
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `name` | `String` | Name of cookbook |
| `userId` | `Integer` | The Id of the user creating the cookbook |
| `type` | `String` | The ethnicity of the cook book |

### Get Cookbooks with User Id
```http
  GET /api/v1/cookbook?userId=
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `userId` | `Integer` | Id of the user |

<i><b>Returns:</b> Array of Cookbooks</i>

### Manage Recipes

### Create Recipe 
```http
  POST /api/v1/recipe
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `name` | `String` | Name of recipe |
| `cookBookId` | `Integer` | The Id of the cookbook the recipe belongs to |
| `type` | `String` | The ethnicity of the recipe |

|

### Get Recipes with CookbookId
```http
  GET /api/v1/recipe?cookBookId=
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `cookBookId` | `Integer` | Id of the Cookbook |

<i><b>Returns:</b> Array of Recipes</i>

### Mesurement Conversion
-  COMING SOON

### Recipe Finder
- COMING SOON






