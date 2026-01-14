package com.conveniencestore.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ImportRequestDTO {
	private Long id;
	private String importNumber;
	private Long supplierId;
	private Long staffId;
	private BigDecimal totalAmount;
	private String note;
	private int isDeleted;
}
