To Do list:
================

- **Seguir Usuarios**
    - Crear tabla relación usuario - usuario.
    - Clase user añadir lista de usuarios a los que sigue un usuario.
    - Controller ver usuarios a los que sigues, añadir usuario, eliminar usuario.

- **El Xat**

    -   a

- **Valoraciones**

    - **Como funciona:**

        - **Crear** relación **user - animes** con un **campo valoración**. En la cual **animeid** y **userid** son las **claves primárias**. Ya que un usuario puede valorar varios animes y un anime ser valorado por varios usuarios

            `Datos de ejemplo:`

            **USERS**
            userid | name
            --- | ---
            u1 | user1
            u2 | user2
            u3 | user3
            u4 | user4

            **ANIMES**
            animeid | title
            --- | ---
            a1 | anime1
            a2 | anime2
            a3 | anime3
            a4 | anime4

            **RATINGS**
            userid | animeid | rating
            --- | --- | ---
            u1 | a1 | 5
            u1 | a2 | 2
            u1 | a3 | 8
            u2 | a4 | 10
            u2 | a1 | 6
            ... | ... | ...

            `un usuario no puede valorar el mismo anime varias veces, en ese caso actualiza su reseña anterior.`

    - **Métodos implementados:**

        - **Add Rating:**

            *Valorar un anime*

            Request URL
            ```https
            http://localhost:8080/animes/rating/
            ```
            Request Body
            ```json
            {
                "animeid": "4bdad251-445e-4f83-bba8-f5c4344a14a9",
                "userid": "dedf1b39-0254-4e0e-a277-566171ae7ad9",
                "rating": 1
            }
            ```

        - **Remove Rating:**

            *Eliminar una valoración*

            Request URL
            ```https
            http://localhost:8080/animes/rating/4bdad251-445e-4f83-bba8-f5c4344a14a9/dedf1b39-0254-4e0e-a277-566171ae7ad9
            ```
            Response
            ```json
            "registro eliminador"

            -----------------------

            "No s'ha trobat la valoració"
            ```


        - **Get Ratings:**

            *Ver valoraciones de un anime*

            Request URL
            ```https
            http://localhost:8080/animes/rating/4bdad251-445e-4f83-bba8-f5c4344a14a9
            ```
            Body
            ```json
            [
                {
                    "animeid": "4bdad251-445e-4f83-bba8-f5c4344a14a9",
                    "ratings": [
                        {
                            "animeid": "4bdad251-445e-4f83-bba8-f5c4344a14a9",
                            "userid": "6bcfb2f1-4895-4f95-b48c-13e61abe0773",
                            "rating": 5.5
                        }
                    ],
                    "name": "Anime One"
                }
            ]
            ```