package part01.lesson10.client;

import part01.lesson10.constants.Params;

/**
 * Instance of Client
 *
 * @author folkland
 */
public class Client2 {

    public static void main(String[] args) {
        ClientImpl client = new ClientImpl("Client2", Params.SERVER_PORT, Params.ADDRESS);
    }
}
