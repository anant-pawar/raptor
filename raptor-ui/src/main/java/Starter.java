import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

/**
 * Created by Anant on 22-10-2015.
 */

public class Starter {
    public  static void main(String[] args){
        Starter starter = new Starter();

        starter.start();
    }

    public void start()
    {
        Vertx vertx = Vertx.vertx();
        Router router = Router.router(vertx);

        HttpServer httpServer = vertx.createHttpServer();

        router.route("/").handler(routingContext -> {
            routingContext
                    .response()
                    .sendFile("./web/home.html");
        });

        // route for css files
        router.route("/bootstrap/css/:stylesheet").handler(routingContext -> {
            routingContext
                    .response()
                    .sendFile("web/bootstrap/css/" + routingContext
                            .request()
                            .params()
                            .get("stylesheet"));
        });

        // route for js files
        router.route("/bootstrap/js/:javascript").handler(routingContext -> {
            routingContext
                    .response()
                    .sendFile("web/bootstrap/js/" + routingContext
                            .request()
                            .params()
                            .get("javascript"));
        });

        httpServer
                .requestHandler(router::accept)
                .listen(8080);
    }
}
