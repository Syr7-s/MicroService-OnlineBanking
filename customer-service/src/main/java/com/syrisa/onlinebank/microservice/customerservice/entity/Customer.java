package com.syrisa.onlinebank.microservice.customerservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    private long customerTC;
    private String customerName;
    private String customerLastname;
    @Enumerated(EnumType.STRING)
   // private Gender customerGender;
    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", message = "Phone Number Ex:+(123)-456-78-90")
    @Length(min = 18, max = 18)
    private String customerPhone;
    private String customerEmail;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate customerBirthDate;
    private boolean isState;
  /*  @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="address_id")
    @JsonIgnore
    private Address address;*/
}
