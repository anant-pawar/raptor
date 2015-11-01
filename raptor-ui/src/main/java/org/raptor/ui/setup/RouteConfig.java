package org.raptor.ui.setup;

import io.vertx.ext.web.Router;

/**
 * Created by Anant on 30-10-2015.
 */
public class RouteConfig {
    private Router router;

    public RouteConfig(Router router) {
        this.router = router;
    }

    public void initRoutes() {
        router.route("/").handler(routingContext -> {
            routingContext
                    .response()
                    .sendFile("./web/home.html");
        });

        // route for css files
        router.route("/lib/bootstrap/css/:stylesheet").handler(routingContext -> {
            routingContext
                    .response()
                    .sendFile("web/lib/bootstrap/css/" + routingContext
                            .request()
                            .params()
                            .get("stylesheet"));
        });

        router.route("/css/:stylesheet").handler(routingContext -> {
            routingContext
                    .response()
                    .sendFile("web/css/" + routingContext
                            .request()
                            .params()
                            .get("stylesheet"));
        });

        // route for js files
        router.route("/lib/bootstrap/js/:javascript").handler(routingContext -> {
            routingContext
                    .response()
                    .sendFile("web/lib/bootstrap/js/" + routingContext
                            .request()
                            .params()
                            .get("javascript"));
        });

        router.route("/lib/jquery/:javascript").handler(routingContext -> {
            routingContext
                    .response()
                    .sendFile("web/lib/jquery/" + routingContext
                            .request()
                            .params()
                            .get("javascript"));
        });

        router.route("/lib/html5shiv/:javascript").handler(routingContext -> {
            routingContext
                    .response()
                    .sendFile("web/lib/html5shiv/" + routingContext
                            .request()
                            .params()
                            .get("javascript"));
        });

        router.route("/lib/respond/:javascript").handler(routingContext -> {
            routingContext
                    .response()
                    .sendFile("web/lib/respond/" + routingContext
                            .request()
                            .params()
                            .get("javascript"));
        });

    }
}
