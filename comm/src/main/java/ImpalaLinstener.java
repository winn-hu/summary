//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.security.UserGroupInformation;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class ImpalaLinstener implements ServletContextListener {
	/*private static Configuration kuduconf = new Configuration();

	public void initkerberos() {
		String keytabPrincipal = "impala/indata-147-12-78-61.hnds.com@HNDS.COM";
		String krb5_conf = "/etc/krb5.conf";
		String keytabFile = "/etc/security/keytab//impala.haproxy.keytab";
		Configuration conf = getKuduConf();
		try {
			System.setProperty("java.security.krb5.conf", krb5_conf);
			System.out.println("Kerberos验证开始");
			UserGroupInformation.setConfiguration(conf);
			UserGroupInformation.loginUserFromKeytab(keytabPrincipal, keytabFile);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(krb5_conf);
			System.out.println(keytabPrincipal);
			System.out.println(keytabFile);
			System.out.println("Kerberos验证失败");
		}
	}

	public void contextInitialized(ServletContextEvent sce) {
		TimerTask task = new TimerTask() {
			public void run() {
				ImpalaLinstener.this.initkerberos();
				System.out.println("Kerberos验证完成");
			}
		};
		Timer timer = new Timer();
		long delay = 0;
		long intevalPeriod = 1800000;
		timer.scheduleAtFixedRate(task, delay, intevalPeriod);
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}

	public static Configuration getKuduConf() {
		kuduconf.set("hadoop.security.authentication", "Kerberos");
		return kuduconf;
	}*/
}