package com.kee.cms;

import java.io.BufferedInputStream;
import java.io.Console;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import org.apache.ibatis.jdbc.ScriptRunner;

import com.mysql.jdbc.Connection;

/**
 * 
 * 自动安装修改乱码 
 * @author keehang
 * 
 */
public class Install {
	private static String CMS_PROPERTIES = "cms.properties";
	private static String CMS_INSTALL_SQL = "sql/install.sql";
	private static int INSTALLCOUNT = 3;

	Console console = System.console();

	public static void main(String[] args) {

		Install install = new Install();
		install.welcome();
		for (int i = 1; i <= INSTALLCOUNT; i++) {
			if (install.importData()) {
				System.out.println("\n\n安装成功，使用 mvn jetty:run 运行系统。\n\n");
				break;
			} else {
				System.out.println("第" + i + "/10 安装失败，请根据错误提示检测 "
						+ CMS_PROPERTIES + " 相关数据库的配置是否正常。");
			}
		}
	}
	/**
	 * 读取系统环境信息
	 */
	private void welcome() {                                              
		Properties props = System.getProperties();
		System.out.println("操作系统的名称\t\t" + props.getProperty("os.name"));
		System.out.println("操作系统的架构\t\t" + props.getProperty("os.arch"));
		System.out.println("操作系统的版本\t\t" + props.getProperty("os.version"));
		System.out.println("用户的账户名称\t\t" + props.getProperty("user.name"));
		System.out.println("用户的主目录\t\t" + props.getProperty("user.home"));
		System.out.println("用户的当前工作目录\t" + props.getProperty("user.dir"));
		System.out.println("运行时环境版本\t\t" + props.getProperty("java.version"));
		System.out.println("Java安装目录\t\t" + props.getProperty("java.home"));
		System.out
				.println("Java虚拟机供应商\t" + props.getProperty("java.vm.vendor"));
		System.out.println("Java虚拟机名称\t\t" + props.getProperty("java.vm.name"));

		System.out.println("\n\n开始前，需要配置 " + CMS_PROPERTIES
				+ "，此文件包含数据库连接的相关信息。");
	}

	/**
	 * 将sql/install.sql导入数据库中
	 */
	private boolean importData() {
		console.readLine("\n按control+c推出，按其它键继续安装。。。\n");
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(CMS_PROPERTIES));
			Properties props = new Properties();
			props.load(bis);
			String url = props.getProperty("jdbc.url");
			String driver = props.getProperty("jdbc.driverClass");
			String username = props.getProperty("jdbc.user");
			String password = props.getProperty("jdbc.password");
			Class.forName(driver).newInstance();
			conn = (Connection) DriverManager.getConnection(url, username,
					password);
			ScriptRunner runner = new ScriptRunner(conn);
			runner.setErrorLogWriter(null);
			runner.setLogWriter(null);
			runner.runScript(new InputStreamReader(new FileInputStream(
					CMS_INSTALL_SQL), "UTF-8"));
			return true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			return false;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println("ERROR: " + e.getMessage());
			}
		}
	}
}
