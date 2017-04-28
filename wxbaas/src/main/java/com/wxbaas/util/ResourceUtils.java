package com.wxbaas.util;

import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Resource���?����
 * 
 * @author peiyang.wy
 */
public class ResourceUtils {
	final private static Logger logger = LoggerFactory.getLogger(ResourceUtils.class);
/**
 * 
 * @param conn
 */
	public static void closeQuietly(Closeable conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				logger.error(null, e);
			}
		}
	}
/**
 * 
 * @param stmt
 */
	public static void closeQuietly(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				logger.error(null, e);
			}
		}
	}
/**
 * 
 * @param conn
 */
	public static void closeQuietly(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				logger.error(null, e);
			}
		}
	}
/**
 * 
 * @param conn
 */
	public static void closeQuietly(Socket conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				logger.error(null, e);
			}
		}
	}
/**
 * 
 * @param conn
 */
	public static void closeQuietly(ServerSocket conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				logger.error(null, e);
			}
		}
	}
/**
 * 
 * @param rs
 */
	public static void closeQuietly(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				logger.error(null, e);
			}
		}
	}
/**
 * 
 * @param ps
 */
	public static void closeQuietly(PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (Exception e) {
				logger.error(null, e);
			}
		}
	}
/**
 * 
 * @param in
 */
	public static void closeQuietly(InputStream in) {
		if (in != null) {
			try {
				in.close();
			} catch (Exception e) {
				logger.error(null, e);
			}
		}
	}
/**
 * 
 * @param out
 */
	public static void closeQuietly(OutputStream out) {
		if (out != null) {
			try {
				out.close();
			} catch (Exception e) {
				logger.error(null, e);
			}
		}
	}
/**
 * 
 * @param reader
 */
	public static void closeQuietly(Reader reader) {
		if (reader != null) {
			try {
				reader.close();
			} catch (Exception e) {
				logger.error(null, e);
			}
		}
	}
/**
 * 
 * @param writer
 */
	public static void closeQuietly(Writer writer) {
		if (writer != null) {
			try {
				writer.close();
			} catch (Exception e) {
				logger.error(null, e);
			}
		}
	}
/**
 * 
 * @param t
 * @return
 */
	public static String getErrorStack(Throwable t) {
		StringWriter sw = new StringWriter();
		t.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}
}
