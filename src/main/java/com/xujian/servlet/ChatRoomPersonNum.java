package com.xujian.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xujian.memorydata.ChatRoomData;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class ChatRoomPersonNum
 */
public class ChatRoomPersonNum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatRoomPersonNum() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	 
		ConcurrentHashMap<String, Integer> roomNumMap = ChatRoomData.getRoomNumMap();
		JSONObject data = JSONObject.fromObject(roomNumMap);
		System.out.println(data);
		/*���ñ��룬��ֹ������������*/
		response.setCharacterEncoding("utf-8");
		/*�õ������*/
		PrintWriter respWritter = response.getWriter();
		/*��JSON��ʽ�Ķ���toString()����*/
		respWritter.append(data.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
