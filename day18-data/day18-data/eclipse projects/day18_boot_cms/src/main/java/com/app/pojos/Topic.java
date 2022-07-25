package com.app.pojos;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "topics")
@Getter
@Setter
@ToString
public class Topic extends BaseEntity {
//id | name
	@Column(name = "name")
	private String topicName;

		
}
