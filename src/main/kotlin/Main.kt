import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*

fun main(){

//    val app = Javalin.create().start(7000)
//    app.get("/WCC"){ctx -> ctx.result("Olá Deeevas! =D") }
    val userDao = UserDao()
    val app = Javalin.create().apply {
        exception(Exception::class.java){e, ctx -> e.printStackTrace()}
        error(404) {ctx -> ctx.json("NOT FOUND")}
    }.start(7000)
    //mostrando todos os usuários
    app.routes{
        get("/users"){ctx -> ctx.json(userDao.users)}
        get("/users/{users-id}"){ctx -> ctx.json(userDao.findById(ctx.pathParam("user-id").toInt())!!)}
    }
}
