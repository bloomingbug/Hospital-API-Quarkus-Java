package rumah.sakit.utility;

import io.vertx.core.json.JsonObject;

import javax.ws.rs.core.Response;

public class ResponseUtil {
    public static Response ok(Object data) {
        JsonObject response = new JsonObject();
        response.put("data", data);
        return Response
                .ok()
                .entity(response)
                .build();
    }

    public static Response ok(Object data, Object token) {
        JsonObject response = new JsonObject();
        response.put("data", data);
        response.put("token", token);
        return Response
                .ok()
                .entity(response)
                .build();
    }

    public static Response ok(Object data, Integer jumlahData,Integer maxData, Integer total, Integer currentPage,Integer totalPage) {
        JsonObject response = new JsonObject();
        response.put("data", data);
        response.put("jumlahData", jumlahData);
        response.put("maxData", maxData);
        response.put("total", total);
        response.put("currentPage", currentPage);
        response.put("totalPage", totalPage);
        return Response
                .ok()
                .entity(response)
                .build();
    }

}
