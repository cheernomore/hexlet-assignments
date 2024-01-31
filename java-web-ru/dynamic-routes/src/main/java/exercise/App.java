package exercise;

import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import io.javalin.http.NotFoundResponse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// BEGIN

// END

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/companies/{id}", ctx -> {
            Map<String, String> company;
            String id = ctx.pathParam("id");

            if (Integer.parseInt(id) > COMPANIES.size()) {
                ctx.status(HttpStatus.NOT_FOUND);
                ctx.json("Company not found");
            } else {
                for (Map<String, String> cmp : COMPANIES) {
                    if (cmp.get("id").equals(id)) {
                        company = cmp;
                        ctx.json(company);
                    }
                }
            }
        });
        // END

        app.get("/companies", ctx -> {
            ctx.json(COMPANIES);
        });

        app.get("/", ctx -> {
            ctx.result("open something like (you can change id): /companies/5");
        });

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
