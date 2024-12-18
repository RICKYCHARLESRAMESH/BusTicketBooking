package com.model;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "reviews")
public class Review {
	@Id
	@Column(name="review_id")
	private int reviewId;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "trip_id", nullable = false)
	private Trip trip;

	@Column(nullable = false)
	private Integer rating;

	@Column(length = 500) 
	private String comment;

	@Column(name = "review_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date reviewDate;

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public Review(int reviewId, Customer customer, Trip trip, Integer rating, String comment, Date reviewDate) {
		super();
		this.reviewId = reviewId;
		this.customer = customer;
		this.trip = trip;
		this.rating = rating;
		this.comment = comment;
		this.reviewDate = reviewDate;
	}
	
}
