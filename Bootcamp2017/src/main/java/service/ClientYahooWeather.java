
package service;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author federico
 */
@Path("/v1/public")
public interface ClientYahooWeather{
    @GET //https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22nome%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys
    @Path("yql")
    @Produces("application/json")
    public String getForecastDayByLocation(@QueryParam ("q") String query, @QueryParam("format")String format);
}
