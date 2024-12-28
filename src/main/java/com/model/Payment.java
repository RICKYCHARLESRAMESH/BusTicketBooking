package com.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "payments")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer paymentId;
 
	@OneToOne(cascade =CascadeType.ALL)
	@JoinColumn(name = "booking_id")
	@JsonIgnore
	private Booking booking;
	
 
	@OneToOne(cascade =CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	@JsonIgnore
	private Customer customer;
 
	@Column(nullable = false)
	private BigDecimal amount;
 
	@Column(name = "payment_date")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime paymentDate;
 
	@Enumerated(EnumType.STRING)
	@Column(name = "payment_status", nullable = false)

	private PaymentStatus paymentStatus;
 
	public enum PaymentStatus {

		Success, Failed

	}
 
	public Payment() {

		super();
 
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Payment(Integer paymentId, Booking booking, Customer customer, BigDecimal amount, LocalDateTime paymentDate,
			PaymentStatus paymentStatus) {
		super();
		this.paymentId = paymentId;
		this.booking = booking;
		this.customer = customer;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.paymentStatus = paymentStatus;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", booking=" + booking + ", customer=" + customer + ", amount="
				+ amount + ", paymentDate=" + paymentDate + ", paymentStatus=" + paymentStatus + "]";
	}
	
	
}