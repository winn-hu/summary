package utils.kafka;

import java.util.Properties;

public class SecurityUtils {

	private static PropertiesLoader prokafka = new PropertiesLoader("kafka.properties");

	public static Properties getSecurityProperties() {
		String krb5 = prokafka.getProperty("kafka.krb5.conf");
		String keytab = prokafka.getProperty("kafka.keytabFile");
		String principal = prokafka.getProperty("kafka.keytab.principal");

		Properties props = new Properties();

		if (krb5 != null && keytab != null && krb5.length() != 0 && keytab.length() != 0) {
						
			System.setProperty("java.security.krb5.conf", krb5);
			
			StringBuilder jaas = new StringBuilder("com.sun.security.auth.module.Krb5LoginModule required");
			jaas.append(" useKeyTab=true storeKey=true");
			jaas.append(" keyTab=\"" + keytab + "\"");
			jaas.append(" principal=\"" + principal + "\";");

			System.out.println(jaas.toString());
			props.put("sasl.jaas.config", jaas.toString());
			props.put("security.protocol", "SASL_PLAINTEXT");
			props.put("sasl.kerberos.service.name", "kafka");
			props.put("sasl.mechanism", "GSSAPI");
			
			String[] KAFKY_KEY_LIST= {"bootstrap.servers","acks","retries","batch.size","linger.ms",
					"buffer.memory","key.serializer","value.serializer","auto.create.topics.enable",
					"enable.auto.commit","auto.commit.interval.ms","auto.offset.reset",
					"session.timeout.ms","key.deserializer","value.deserializer"};
			for(String key:KAFKY_KEY_LIST) {
				props.put(key, prokafka.getProperty("kafka."+key));
			}

		}else{
			System.out.println("No base configer file, process exiting");
			System.exit(0);
		}

		return props;
	}

}
