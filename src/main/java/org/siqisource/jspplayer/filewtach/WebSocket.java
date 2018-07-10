package org.siqisource.jspplayer.filewtach;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class WebSocket {

	private static Set<Session> SESSION_SET = new HashSet<Session>();

	@OnOpen
	public void onOpen(Session session) {
		SESSION_SET.add(session);
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose(Session session) {
		SESSION_SET.remove(session);
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println(message);
	}

	@OnError
	public void onError(Session session, Throwable error) {
		error.printStackTrace();
		SESSION_SET.remove(session);
	}

	public static void notify(String url) throws IOException {
		for (Session session : SESSION_SET) {
			if (session.isOpen()) {
				session.getBasicRemote().sendText(url);
			} else {
				SESSION_SET.remove(session);
			}

		}
	}

}
