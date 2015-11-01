import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import setup.RouteConfig;

/**
 * Created by Anant on 22-10-2015.
 */

public class RaptorUI {
    private Vertx vertx;
    private String uiBus;

    public RaptorUI(Vertx vertx, String uiBus)
    {
        this.vertx = vertx;
        this.uiBus = uiBus;
    }

    public void start() {
        Router router = Router.router(vertx);
        RouteConfig routeConfig = new RouteConfig(router);

        routeConfig.initRoutes();

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(8080);
    }
}
