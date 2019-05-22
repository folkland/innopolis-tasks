package part01.lesson10.client;

import part01.lesson10.constants.Params;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ClientUDPListener extends Thread {

    private int port;
    private boolean sockerWork = true;

    public ClientUDPListener(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            while (sockerWork) {
                byte[] text = new byte[250];
                DatagramPacket packet = new DatagramPacket(text, text.length, InetAddress.getByName(Params.ADDRESS), port);
                DatagramSocket socket = new DatagramSocket();
                socket.receive(packet);
                byte[] message = packet.getData();
                System.out.println(new String(message));
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setStop() {
        sockerWork = false;
    }
}
