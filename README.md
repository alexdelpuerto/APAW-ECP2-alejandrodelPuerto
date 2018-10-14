# APAW-ECP2-alejandrodelPuerto
#### Asignatura: *Ingeniería Web: Visión General*
#### Máster en Ingeniería Web por la U.P.M.

## Diseño de entidades
 
![ClassDiagram](https://github.com/alexdelpuerto/APAW-ECP2-alejandrodelPuerto/blob/master/docs/ClassDiagram.PNG)

## API
### POST /persons
#### Parámetros del cuerpo
- `nick`: String (**requerido**)
#### Respuesta
- 200 OK 
  - `id`: String
- 403 BAD_REQUEST
---
### POST /persons/{id}/votes
#### Parámetros del cuerpo
- `id`: String (**requerido**)
- `value`: Integer (**requerido**)
- `comment`: String
#### Respuesta
- 200 OK 
- 403 BAD_REQUEST
- 404 NOT_FOUND
---
### POST /songs
#### Parámetros del cuerpo
- `title`: String (**requerido**)
- `category`: Category
- `personId`: String (**requerido**)
#### Respuesta
- 200 OK 
  - `id`: String
- 403 BAD_REQUEST
- 404 NOT_FOUND
---
### PATH /songs/{id}/category
#### Parámetros del cuerpo
- `category`: String (**requerido**)
#### Respuesta
- 200 OK 
- 403 BAD_REQUEST
- 404 NOT_FOUND
---
### GET /songs
#### Respuesta
- 200 OK 
  - `[ {id:String,title:String} ]`
---
### PUT /songs/{id}
#### Parámetros del cuerpo
- `title`: String (**requerido**)
#### Respuesta
- 200 OK 
- 403 BAD_REQUEST
- 404 NOT_FOUND
---
### DELETE /songs/{id}
#### Respuesta
- 200 OK 
---
### GET /songs/search?q=vote:>=5
#### Respuesta
- 200 OK
  - `[ {id:String,title:String} ]`
- 403 BAD_REQUEST
---
##### Autor: Alejandro del Puerto Extremera
