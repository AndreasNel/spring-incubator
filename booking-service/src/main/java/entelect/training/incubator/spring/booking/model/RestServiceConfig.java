package entelect.training.incubator.spring.booking.model;

import jakarta.validation.constraints.NotBlank;

public record RestServiceConfig(@NotBlank String baseUrl, @NotBlank String username, @NotBlank String password) {
}
