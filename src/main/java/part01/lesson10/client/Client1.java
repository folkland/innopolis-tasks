package part01.lesson10.client;

import part01.lesson10.constants.Params;

/**
 * Instance of Client
 *
 * @author folkland
 */
public class Client1 {

    public static void main(String[] args) {
        ClientImpl client = new ClientImpl("Client1", Params.SERVER_PORT, Params.ADDRESS);
    }
}
