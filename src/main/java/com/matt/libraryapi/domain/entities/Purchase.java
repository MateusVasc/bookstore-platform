package com.matt.libraryapi.domain.entities;

import com.matt.libraryapi.domain.enums.PurchaseStatus;
import com.matt.libraryapi.domain.enums.PaymentMethod;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "purchases")
public class Purchase {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "purchase_id")
  private UUID id;

  @Column(name = "total_price", nullable = false)
  private double totalPrice;

  @Column(name = "purchased_at", nullable = false)
  private LocalDateTime purchasedAt;

  @Column(name = "shipping_address", nullable = false)
  private String shippingAddress;

  @Column(name = "billing_address", nullable = false)
  private String billingAddress;

  @Column(name = "payment_method", nullable = false)
  private PaymentMethod paymentMethod;

  @Column(name = "purchased_status", nullable = false)
  @Enumerated(EnumType.STRING)
  private PurchaseStatus purchaseStatus;

  private User user;

  private List<Book> books;
}
