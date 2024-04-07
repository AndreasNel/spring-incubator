package entelect.training.incubator.spring.booking.model;

import jakarta.validation.constraints.NotBlank;

public record SoapServiceConfig(@NotBlank String baseUrl) {
}
