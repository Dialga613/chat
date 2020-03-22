package com.xujian.chat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

import com.xujian.util.HttpGet; 

@ServerEndpoint("/match")
public class Match {
 
	private boolean isRobot = false;
	private String gender;
	private String iconSrc;
	private Match matchTo;
	private String area;
	private Session session;
	private static Integer memCount = 0; //����
	private static Random r = new Random();
	
	@OnOpen
	public void connect(Session session) {
 
		this.session = session;
		memAdd();
		System.out.println("һ���˽��������ˣ������û���memCount-->" + memCount);
 
	}
	
	//���������տͻ�����Ϣ
	@OnMessage
	public void onMessage(String message, Session session) {
//		System.out.println("���Կͻ��˵���Ϣ:" + message);
		JSONObject jsonObj = new JSONObject(message);
		
		if(jsonObj.get("msgType").equals("uInfo")) {
			this.area = (String) jsonObj.get("area");
			this.gender = (String) jsonObj.get("gender");
			
			int boy_index = r.nextInt(5) + 1; //�������1��5������
			int girl_index = r.nextInt(5) + 1; //�������1��5������
			if(this.gender.equals("�к�")) {
				this.iconSrc = "./img/boy_"+boy_index+".jpg";
			}else {
				this.iconSrc = "./img/girl_"+girl_index+".jpg";
			}
			FindOther.addMatch(this);
		}else if(jsonObj.get("msgType").equals("sendMsg")) {
			String content = (String)jsonObj.get("content");
			Match matchTo = this.getMatchTo();
			if(matchTo != null) {
				if(matchTo.isRobot == false)  //ƥ�䵽�Ĳ��ǻ�����
					matchTo.sendMessage("{\"msgType\":\"sendMsg\", \"content\":"+"\""+content+"\""+"}");
				else if(matchTo.isRobot == true){//ƥ�䵽��������
					System.out.println("���˷������������ݣ�"+content);
					//String API_HOST = "http://i.itpk.cn/api.php";
					String api_key = "496119d9af12f4f10d3e277a56b2f430";
					String api_secret = "q76zj8ugapu2";
					
					Integer limit = 2;
					String temp ="http://i.itpk.cn/api.php?question="+content+ 
							"&api_key="+api_key+
							"&api_secret="+api_secret;
					  
					String respond = HttpGet.httpRequest(temp);
					matchTo.getMatchTo().sendMessage("{\"msgType\":\"sendMsg\", \"content\":"+"\""+respond+"\""+"}");
					//System.out.println(respond);
				}
			}
		} 
	}
	
	//������������Ϣ���ͻ���
	public void sendMessage(String message){
        try {
        	if(this.session != null) {
    			this.session.getBasicRemote().sendText(message);
        	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
    }
	
	@OnClose
	public void onClose(){
		memSub();
		System.out.println("һ���˶Ͽ������ˣ������û���memCount-->" + memCount);
		FindOther.subMatch(this);
	}

	
	public void memAdd(){
		synchronized (memCount) {
			memCount++;
		}
		
    }
    public void memSub(){
    	synchronized (memCount) {
    		memCount--;
		}
    	
    }

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public static Integer getMemCount() {
		return memCount;
	}

	public static void setMemCount(Integer memCount) {
		Match.memCount = memCount;
	}

	public Match getMatchTo() {
		return matchTo;
	}

	public void setMatchTo(Match matchTo) {
		this.matchTo = matchTo;
	}

	public String getIconSrc() {
		return iconSrc;
	}

	public void setIconSrc(String iconSrc) {
		this.iconSrc = iconSrc;
	}

	public boolean isRobot() {
		return isRobot;
	}

	public void setRobot(boolean isRobot) {
		this.isRobot = isRobot;
	}
    


}
