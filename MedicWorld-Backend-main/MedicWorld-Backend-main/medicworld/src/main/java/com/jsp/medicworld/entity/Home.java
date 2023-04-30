package com.jsp.medicworld.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Home {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hid;
	private String name;
	private String type;
	private String heading;
	@Lob
	@Column(nullable = false, columnDefinition = "blob")
	private byte[] image;
	@Column(length = 350)
	private String p1;
	@Column(length = 350)
	private String p2;
	@Column(length = 350)
	private String p3;

}
