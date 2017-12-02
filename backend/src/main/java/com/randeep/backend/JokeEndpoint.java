/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.randeep.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.randeep.jokelibrary.JokeTeller;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.randeep.com",
                ownerName = "backend.randeep.com",
                packagePath = ""
        )
)
public class JokeEndpoint {

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "sayHi")
    public JokeBean sayHi(@Named("name") String name) {
        JokeBean response = new JokeBean();
        response.setData(name + "SERVER");

        return response;
    }

    /** A simple endpoint method that takes a joke string and returns it **/
    @ApiMethod(name = "tellJoke")
    public JokeBean tellJoke() {
        JokeBean response = new JokeBean();

        response.setData(new JokeTeller().getJoke());

        return response;
    }
}