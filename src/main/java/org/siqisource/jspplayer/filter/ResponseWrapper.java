package org.siqisource.jspplayer.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class ResponseWrapper extends HttpServletResponseWrapper {
	private StringWriter stringWriter;// ������

	public ResponseWrapper(HttpServletResponse response) {
		super(response);
		stringWriter = new StringWriter();
	}

	/**
	 * ��servlet��jsp����Writerʱ��������һ������װ����writer, �������������д����
	 */
	public PrintWriter getWriter() {
		return (new PrintWriter(stringWriter));
	}

	/**
	 * ͬ������ ���� getOutputStream�� ����һ��ģ���output stream
	 */
	public ServletOutputStream getOutputStream() {
		return (new ServletOutputStream() {

			@Override
			public void write(int b) throws IOException {
				stringWriter.write(b);
			}

			@Override
			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void setWriteListener(WriteListener listener) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	/**
	 * ����buffer���ַ�����ʾ
	 */
	public String toString() {
		return (stringWriter.toString());
	}
}