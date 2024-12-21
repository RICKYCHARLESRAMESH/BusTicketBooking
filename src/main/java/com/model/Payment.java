package com.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "payments")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer paymentId;
 
	@OneToOne
	@JoinColumn(name = "booking_id", nullable = false)
	@JsonIgnore
	private Booking booking;
 
	@OneToOne
	@JoinColumn(name = "customer_id")
	@JsonIgnore
	private Customer customer;
 
	@Column(nullable = false, precision = 10, scale = 2)

	private BigDecimal amount;
 
	@Column(name = "payment_date")
	@Temporal(TemporalType.TIMESTAMP)

	private Date paymentDate;
 
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

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Payment(Integer paymentId, Booking booking, Customer customer, BigDecimal amount, Date paymentDate,
			PaymentStatus paymentStatus) {
		super();
		this.paymentId = paymentId;
		this.booking = booking;
		this.customer = customer;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.paymentStatus = paymentStatus;
	}
}