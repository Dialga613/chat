package com.xujian.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

import com.xujian.beans.Story;
import com.xujian.service.StoryService;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class StoryList
 */
public class StoryList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final StoryService storyService = new StoryService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoryList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("StoryList�ӿڷ��ʣ����ԣ�" + request.getContextPath());
		
		//��¼��ǰ��ʱ��
		String minDateTime = request.getParameter("minDateTime");
		//System.out.println("minDateTime" + minDateTime);
		 
		List<Story> storyList = storyService.storyList(minDateTime);
		/*��list����װ����json����*/
		JSONArray data = JSONArray.fromObject(storyList);
		//��������������
		/*���ñ��룬��ֹ������������*/
		response.setCharacterEncoding("utf-8");
		/*�õ������*/
		PrintWriter respWritter = response.getWriter();
		/*��JSON��ʽ�Ķ���toString()����*/
		respWritter.append(data.toString());
		/*
		 * for(Story s: storyList) { System.out.println(s.getTitle());
		 * System.out.println(s.getContent()); System.out.println(s.getDateTime()); }
		 */
	 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
