package au.com.telstra.simcardactivator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class SimCardActivator {

    @Autowired
    private ActivationRecordRepository activationRecordRepository;

    @PostMapping("/activate")
    public String activateSimCard(@RequestBody ActivationRequest activationRequest) {
        String actuatorUrl = "http://localhost:8444/actuate";
        boolean activationSuccess = sendActivationRequestToActuator(activationRequest.getIccid(), actuatorUrl);

        ActivationRecord activationRecord = new ActivationRecord();
        activationRecord.setIccid(activationRequest.getIccid());
        activationRecord.setCustomerEmail(activationRequest.getCustomerEmail());
        activationRecord.setActive(activationSuccess);
        activationRecordRepository.save(activationRecord);

        if (activationSuccess) {
            return "SIM card activated successfully.";
        } else {
            return "Failed to activate SIM card.";
        }
    }

    // Helper method to send activation request to actuator
    private boolean sendActivationRequestToActuator(String iccid, String actuatorUrl) {
        // Implement logic to send activation request to actuator
        // and return whether activation was successful
        return true; // Placeholder return value, implement actual logic
    }

    @GetMapping("/query")
    public ActivationRecord getActivationRecord(@RequestParam("simCardId") Long simCardId) {
        return activationRecordRepository.findById(simCardId)
                .orElseThrow(() -> new RuntimeException("Activation record not found"));
    }

    public static void main(String[] args) {
        SpringApplication.run(SimCardActivator.class, args);
    }
}
