package com.backend.studycenter.scpay.dto.response;

import com.backend.studycenter.scpay.util.CustomDateDeserializer;
import com.backend.studycenter.scpay.util.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PaymentResponseDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("studentName")
    private String studentName;
    @JsonProperty("groupName")
    private String groupName;
    @JsonProperty("amount")
    private BigDecimal amount;
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @JsonProperty("createdAt")
    private LocalDateTime createdAt;
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;
}
