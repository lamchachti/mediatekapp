package org.learn.mediatek.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDto {
    @NotBlank
    @Size(min=5,max = 30,message = "Out of binds")
    private String nom;
    @NotBlank
    @Size(min=5,max = 30)
    private String prenom;
    @NotBlank
    @Pattern(regexp = "^6.{8}$",message = "Not a valid Spanish number")
    private String telephone;
}
