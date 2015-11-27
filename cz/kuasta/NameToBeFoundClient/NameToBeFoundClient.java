package cz.kuasta.NameToBeFoundClient;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import cz.kuasta.networkClasses.ErrorMessage;
import cz.kuasta.networkClasses.KryoUtil;
import cz.kuasta.networkClasses.LoginResponse;
import cz.kuasta.networkClasses.RegistrationRequest;
import cz.kuasta.networkClasses.RegistrationResponse;

public class NameToBeFoundClient {
	
	public static Client client;
	public static int accountLevel;
	public static int characterId;
		
	public static void main(String[] args) {
		client = new Client();
        KryoUtil.registerClientClass(client);
 
        client.start();
 
        client.addListener(new Listener() {
            @Override
            public void connected(Connection connection) {
            	
            }
            @Override
            public void disconnected(Connection connection) {
            	
            }       
            @Override
            public void received(Connection connection, Object object) {
                if(object instanceof ErrorMessage){
                	ErrorMessage errorMessage = (ErrorMessage) object;
                	JOptionPane.showMessageDialog(null, errorMessage.message, errorMessage.errorTitle, JOptionPane.ERROR_MESSAGE);
                }else if(object instanceof LoginResponse){
                	LoginResponse response = (LoginResponse) object;
                	
                	if(response.login == true && response.password == true){
                		characterId = response.id;
                		accountLevel = response.accountLevel;
                	}else if(response.login == true && response.password == false){
                		JOptionPane.showMessageDialog(null, "The password doesn't match this login. Please re-enter the password", "Wrong password", JOptionPane.INFORMATION_MESSAGE);
                	}else {
                		JOptionPane.showMessageDialog(null, "The The netered login is not registered, ple re-enter your login", "Wrong login", JOptionPane.INFORMATION_MESSAGE);
                	}
                }else if(object instanceof RegistrationResponse){
                	RegistrationResponse response = (RegistrationResponse) object;
                	
                	switch(response.state){
						case 1:
							JOptionPane.showMessageDialog(null, "Your acount has been registered", "Sucessfully registered", JOptionPane.INFORMATION_MESSAGE);
							break;
						case 3:
							JOptionPane.showMessageDialog(null, "This email has already been registered.", "Email already registered", JOptionPane.INFORMATION_MESSAGE);
							break;
						case 0:
							JOptionPane.showMessageDialog(null, "An account with the same name has already been registered.", "Name already taken", JOptionPane.INFORMATION_MESSAGE);
							break;
						case 2:
							JOptionPane.showMessageDialog(null, "Email, login or password are not entered properly", "Wrong style", JOptionPane.INFORMATION_MESSAGE);
							break;
                	}
                }
            }
        });
 
        try {
            client.connect(5000, KryoUtil.SERVER_IP, KryoUtil.TCP_PORT, KryoUtil.UDP_PORT);
        } catch (IOException ex) {
            System.out.println(ex);
        }
		
		RegistrationRequest request = new RegistrationRequest();
		request.login = "Fadashaf";
		request.password = "md18nmjchdnss";
		request.email = "Kokot.zjebany@fucku.cz";
		
		client.sendTCP(request);
		
		try {
		    Thread.sleep(2000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		//client.sendTCP(new Shutdown());
	}

}
