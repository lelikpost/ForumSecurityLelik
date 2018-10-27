package telran.forum.configuration;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

@Configuration
@ManagedResource
public class AccountConfiguration {

	@Value("${exp.value}")
	int expPeriod;

	@ManagedAttribute
	public int getExpPeriod() {
		return expPeriod;
	}

	@ManagedAttribute
	public void setExpPeriod(int expPeriod) {
		this.expPeriod = expPeriod;
	}

	public AccountUserCredential tokenDecode(String auth) {
		// FIXME
		int pos = auth.indexOf(" ");
		String token = auth.substring(pos + 1);
		byte[] decodeBytes = Base64.getDecoder().decode(token);
		String credential = new String(decodeBytes);
		String[] credentials = credential.split(":");
		return new AccountUserCredential(credentials[0], credentials[1]);
	}
	
}
