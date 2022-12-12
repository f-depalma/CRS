package client;

import client.core.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class ClientApp extends Application {

    @Override public void start(Stage stage) throws Exception
    {
        ClientFactory cf = new ClientFactory();
        cf.getClient().startClient();
        ModelFactory mf = new ModelFactory(cf);
        ViewModelFactory vmf = new ViewModelFactory(mf);
        ViewHandler vh = new ViewHandler(vmf);
        vh.start();
    }
}
