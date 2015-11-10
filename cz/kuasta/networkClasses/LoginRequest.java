package cz.kuasta.networkClasses;

import java.net.InetAddress;

public class LoginRequest extends Request{
	public InetAddress ip;
	public String login, password;
}