# The CookBook App

## Why?
My brother and I love to cook. You would find us online often looking for new things to cook. Upon finding tasty a recipe we would take note of it in a small notepad. Not the smart choice ,but it was what we had to work with meantime. Eventually we lost the notebook and resulting in loosing recipes.

## Creation...
Using some web development skills I learned in the past year. I created my own application to solve this issue. Now, it didnt bring back our lost recipes ,but it will prevent us from ever loosing them while add some other cool features. Using Java and Spring the application implements:

### REST Back End Stack:
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

![Thymeleaf](https://img.shields.io/badge/Thymeleaf-%23005C0F.svg?style=for-the-badge&logo=Thymeleaf&logoColor=white)

![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white)

![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)

![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
  

### Front End Stack:
![Bootstrap](https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white)

![html5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)

![CSS](https://img.shields.io/badge/CSS-239120?&style=for-the-badge&logo=css3&logoColor=white)

![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)

![jQuery](https://img.shields.io/badge/jquery-%230769AD.svg?style=for-the-badge&logo=jquery&logoColor=white)

Using these technologies I implemented these features:

<hr>

## Authenticate/Register
### Login
POST a JSON obj to the endpoint with the following fields.
```http
  POST /api/v1/auth/login
```
| Parameter | Type     | Description          |
| :-------- | :------- |:---------------------|
| `username` | `String` | The user's username  |
| `password` | `String` | The user's password  |


### Register
POST a JSON obj to the endpoint with the following fields.
```http
  POST /api/v1/auth/register
```
| Parameter | Type     | Description         |
| :-------- | :------- |:--------------------|
| `email` | `String` | The user's email    |
| `username` | `String` | The user's username |
| `password` | `String` | The user's password |

<hr>

## Manage Cookbooks
### Create Cookbook
POST a JSON obj to the endpoint with the following fields.
```http
  POST /api/v1/cookbook
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `name` | `String` | Name of cookbook |
| `userId` | `Integer` | The Id of the user creating the cookbook |
| `type` | `String` | The ethnicity of the cook book |




### Get Cookbooks with User ID
Make a GET Request with the following query parameter
```http
  GET /api/v1/cookbook?userId=
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `userId` | `Integer` | Id of the user |

<i><b>Returns:</b> Array of Cookbooks</i>

<hr>

## Manage Recipes
### Create Recipe
POST a JSON obj to the endpoint with the following fields.
```http
  POST /api/v1/recipe
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `name` | `String` | Name of recipe |
| `cookBookId` | `Integer` | The Id of the cookbook the recipe belongs to |
| `type` | `String` | The ethnicity of the recipe |


### Delete Recipe
Make a DELETE request to the endpoint including the query parameter.
```http
  DELETE /api/v1/recipe?id=
```
| Parameter | Type     | Description      |
|:----------| :------- |:-----------------|
| `id`      | `Integer` | Id of the Recipe |


### Get Recipes with CookbookId
Make a GET request to the endpoint including the query parameter.
```http
  GET /api/v1/recipe?cookBookId=
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `cookBookId` | `Integer` | Id of the Cookbook |
<i><b>Returns:</b> Array of Recipes</i>

<hr>

## Recipe Finder
POST a JSON obj to the endpoint with the following fields.
```http
  POST /api/v1/finder/recipe
```
| Parameter     | Type     | Description           |
|:--------------|:---------|:----------------------|
| `ingredients` | `String` | Available Ingredients |
| `ethnicity`   | `String` | Id of the Cookbook    |
<i><b>Returns:</b> A Recipe </i>

<hr>

## Web Page locations
#### App Landing Page
```http
   /v1/landing
```

#### App Login Page
```http
   /v1/login
```

#### App Register Page
```http
   /v1/register
```

#### App Account Page
```http
   /v1/main
```

#### Cookbook Page
```http
   /v1/cookbook
```

#### Conversion page
```http
   /v1/tool/conversion
```

#### Recipe Finder page
```http
   /v1/tool/finder
```

<hr>