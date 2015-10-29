import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import setup.RouteConfig;

/**
 * Created by Anant on 22-10-2015.
 */

public class Starter {
    public static void main(String[] args) {
        Starter starter = new Starter();

        starter.start();
    }

    public void start() {
        Vertx vertx = Vertx.vertx();
        Router router = Router.router(vertx);
        RouteConfig routeConfig = new RouteConfig(router);

        routeConfig.initRoutes();
        HttpServer httpServer = vertx.createHttpServer();

        httpServer
                .requestHandler(router::accept)
                .listen(8080);
    }
}
