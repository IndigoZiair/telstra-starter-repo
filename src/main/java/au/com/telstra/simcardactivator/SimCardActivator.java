package au.com.telstra.simcardactivator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class SimCardActivator {

    private final RestTemplate restTemplate;

    public SimCardActivator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/activate")
    public String activateSimCard(@RequestBody ActivationRequest activationRequest) {
        String actuatorUrl = "http://localhost:8444/actuate";
        ActivationResponse response = restTemplate.postForObject(actuatorUrl, activationRequest, ActivationResponse.class);

        if (response != null && response.isSuccess()) {
            return "SIM card activated successfully.";
        } else {
            return "Failed to activate SIM card.";
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(SimCardActivator.class, args);
    }

    // Define the ActivationRequest class as an inner class
    public static class ActivationRequest {
        private String iccid;

        public ActivationRequest() {
        }

        public ActivationRequest(String iccid) {
            this.iccid = iccid;
        }

        public String getIccid() {
            return iccid;
        }

        public void setIccid(String iccid) {
            this.iccid = iccid;
        }
    }

    // Define the ActivationResponse class as an inner class
    public static class ActivationResponse {
        private boolean success;

        public ActivationResponse() {
        }

        public ActivationResponse(boolean success) {
            this.success = success;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }
    }
}
