package com.heren.his.register;

import com.google.common.collect.ImmutableMap;
import com.thoughtworks.i0.ApplicationModule;
import com.thoughtworks.i0.Launcher;
import com.thoughtworks.i0.config.Configuration;
import com.thoughtworks.i0.config.builder.ConfigurationBuilder;
import com.thoughtworks.i0.server.JettyServer;
import org.eclipse.jetty.client.HttpClient;
import org.junit.After;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class RegisterTests {

    private HttpClient client;
    private JettyServer server;

    private String get(String url) throws Exception {
        return new String(client().GET(url).get().content());
    }

    private HttpClient client() throws Exception {
        if (client == null || !client.isRunning()) {
            client = new HttpClient();
            client.start();
        }
        return client;
    }

    private static final Configuration config = new ConfigurationBuilder() {
        {
            HashMap<String, String> map = new HashMap<>();
            map.put("hibernate.hbm2ddl.auto","create-drop");
            database().driver("org.h2.Driver").url("jdbc:h2:mem").user("sa").password("");
            http().port(8081);
        }
    }.build();

    @Test
    public void should_use_application_name_as_root_context() throws Exception {
        server = new Launcher(modules(new Register()), config).launch(false, "register");
//        assertThat(get("http://localhost:8081/register/api/clinic_registers/all"), is("something\n"));
    }

    private Map<String, ApplicationModule> modules(ApplicationModule... modules) throws Exception {
        ImmutableMap.Builder<String, ApplicationModule> builder = new ImmutableMap.Builder<>();
        for (ApplicationModule module : modules)
            builder.put(module.getApplication().name(), module);
        return builder.build();
    }


    @After
    public void after() throws Exception {
        if (server != null) server.close();
        if (client != null && client.isRunning()) client.stop();
    }


}
