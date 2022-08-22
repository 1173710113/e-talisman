package com.example.demo.domain;

import com.example.demo.msghandler.BaseException;
import com.example.demo.msghandler.ExceptionEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "付费模板")
public class AccessPaymentPlan {

	// 法币月付价格
	@ApiModelProperty(value = "月付价格")
	protected Integer monthlyPayment;
	// 法币年付价格
	@ApiModelProperty(value = "年付价格")
	protected Integer yearlyPayment;
	// 法币一次性付价格
	@ApiModelProperty(value = "全款价格")
	protected Integer fullyPayment;
	
	public AccessPaymentPlan() {
		super();
	}

	public AccessPaymentPlan(Integer monthlyPayment, Integer yearlyPayment, Integer fullyPayment) {
		super();
		this.monthlyPayment = monthlyPayment;
		this.yearlyPayment = yearlyPayment;
		this.fullyPayment = fullyPayment;
	}

	public Integer getMonthlyPayment() {
		return monthlyPayment;
	}

	public void setMonthlyPayment(Integer monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

	public Integer getYearlyPayment() {
		return yearlyPayment;
	}

	public void setYearlyPayment(Integer yearlyPayment) {
		this.yearlyPayment = yearlyPayment;
	}

	public Integer getFullyPayment() {
		return fullyPayment;
	}

	public void setFullyPayment(Integer fullyPayment) {
		this.fullyPayment = fullyPayment;
	}

	@Override
	public String toString() {
		return "AccessPaymentPlan [monthlyPayment=" + monthlyPayment + ", yearlyPayment=" + yearlyPayment
				+ ", fullyPayment=" + fullyPayment + "]";
	}
	
	
	public Integer getPayment(String paymentPlan) {
		switch (paymentPlan) {
		case "monthlyPayment":
			return monthlyPayment;
		case "yearlyPayment":
			return yearlyPayment;
		case "fullyPayment":
			return fullyPayment;
		default:
			throw new BaseException(ExceptionEnum.AUGUMENT_ERROR);
		}
	}
	
	
	
	
	
	
	
}
