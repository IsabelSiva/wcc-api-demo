package application
import io.javalin.apibuilder.ApiBuilder.*
import model.Location
import dao.LocationDao
import io.javalin.Javalin

fun main(){

    //utilize essa variavel para chamar as funções
    val locationDao = LocationDao()

    val app = Javalin.create().apply{
        exception(Exception::class.java) { e, ctx -> e.printStackTrace()}
        error(404) { ctx -> ctx.json("NOT FOUND") }
    }.start(7000)

    app.routes {

        //TODO EXERCICIO 1
        get("/locations") { ctx ->
            ctx.json(locationDao.getAll())
        }

        //TODO EXERCICIO 2
        get("/locations/{location-id}") { ctx ->
            ctx.json(locationDao.findById(ctx.pathParam("location-id").toInt())!!)
        }

        /*TODO DESAFIO 1
        * Dica: Utilize essa variavel abaixo para pegar o body da requisição
        */
        post("/locations") { ctx ->
            val localizacao = ctx.bodyAsClass<Location>()
            locationDao.save(mensagem = localizacao.mensagem, longitude = localizacao.longitude, latitude = localizacao.latitude, galaxia = localizacao.galaxia, planeta = localizacao.planeta)
            ctx.status(201)
        }
         //TODO DESAFIO 2
        delete("/locations/{location-id}"){ ctx ->
            locationDao.delete(ctx.pathParam("location-id").toInt())
            ctx.status(204)
        }

        //TODO DESAFIO 3
        patch("/locations/{location-id}") { ctx ->
            val localizacao = ctx.bodyAsClass<Location>()
            locationDao.update(
                id = ctx.pathParam("location-id").toInt(),
                 location = localizacao
            )
            ctx.status(204)
        }

    }

}
