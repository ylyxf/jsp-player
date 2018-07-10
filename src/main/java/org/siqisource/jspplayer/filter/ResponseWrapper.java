package org.siqisource.jspplayer.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class ResponseWrapper extends HttpServletResponseWrapper {
	private StringWriter stringWriter;// 缓存区

	public ResponseWrapper(HttpServletResponse response) {
		super(response);
		stringWriter = new StringWriter();
	}

	/**
	 * 当servlet或jsp请求Writer时，给它们一个被封装过的writer, 其会往缓冲区中写数据
	 */
	public PrintWriter getWriter() {
		return (new PrintWriter(stringWriter));
	}

	/**
	 * 同样，当 调用 getOutputStream， 返回一个模拟的output stream
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
	 * 返回buffer的字符串表示
	 */
	public String toString() {
		return (stringWriter.toString());
	}
}