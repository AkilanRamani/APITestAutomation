package APITestAutomation;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetConfigsForPO {
	
	public List<GetColumnConfig> getConfig() {
		return config;
	}

	public void setConfig(List<GetColumnConfig> config) {
		this.config = config;
	}

	@Override
	public String toString() {
		return "GetConfigsForPO [config=" + config + ", status=" + status + ", message=" + message + "]";
	}

	public List<GetColumnConfig> config;
	public String status;
	public String message;
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
