/* ===========================================================
 * TradeManager : a application to trade strategies for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2011-2011, by Simon Allen and Contributors.
 *
 * Project Info:  org.trade
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Java is a trademark or registered trademark of Oracle, Inc.
 * in the United States and other countries.]
 *
 * (C) Copyright 2011-2011, by Simon Allen and Contributors.
 *
 * Original Author:  Simon Allen;
 * Contributor(s):   -;
 *
 * Changes
 * -------
 *
 */
package org.trade.persistent.dao;

// Generated Feb 21, 2011 12:43:33 PM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.trade.core.dao.Aspect;
import org.trade.core.valuetype.Money;

/**
 * Orderfill generated by hbm2java
 * 
 * @author Simon Allen
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "tradeorderfill")
public class TradeOrderfill extends Aspect implements java.io.Serializable, Cloneable {

	private static final long serialVersionUID = -4345234694835258864L;

	private String accountNumber;
	private BigDecimal averagePrice;
	private BigDecimal commission;
	private Integer cumulativeQuantity;
	private String execId;
	private String orderReference;
	private Integer permId;
	@NotNull
	private BigDecimal price;
	@Min(1)
	private Integer quantity;
	private String side;
	private String exchange;
	private ZonedDateTime time;
	private TradeOrder tradeOrder;

	public TradeOrderfill() {
	}

	/**
	 * Constructor for TradeOrderfill.
	 * 
	 * @param tradeOrder
	 *            TradeOrder
	 * @param accountNumber
	 *            String
	 * @param averagePrice
	 *            BigDecimal
	 * @param cumulativeQuantity
	 *            Integer
	 * @param exchange
	 *            String
	 * @param execId
	 *            String
	 * @param price
	 *            BigDecimal
	 * @param quantity
	 *            Integer
	 * @param side
	 *            String
	 * @param time
	 *            Date
	 */
	public TradeOrderfill(TradeOrder tradeOrder, String accountNumber, BigDecimal averagePrice,
			Integer cumulativeQuantity, String exchange, String execId, BigDecimal price, Integer quantity, String side,
			ZonedDateTime time) {
		this.tradeOrder = tradeOrder;
		this.accountNumber = accountNumber;
		this.averagePrice = averagePrice;
		this.cumulativeQuantity = cumulativeQuantity;
		this.execId = execId;
		this.exchange = exchange;
		this.price = price;
		this.quantity = quantity;
		this.side = side;
		this.time = time;
	}

	/**
	 * Method getIdTradeOrderFill.
	 * 
	 * @return Integer
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idTradeOrderFill", unique = true, nullable = false)
	public Integer getIdTradeOrderFill() {
		return this.id;
	}

	/**
	 * Method setIdTradeOrderFill.
	 * 
	 * @param idTradeOrderFill
	 *            Integer
	 */
	public void setIdTradeOrderFill(Integer idTradeOrderFill) {
		this.id = idTradeOrderFill;
	}

	/**
	 * Method getTradeOrder.
	 * 
	 * @return TradeOrder
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idTradeOrder", insertable = true, updatable = true, nullable = false)
	public TradeOrder getTradeOrder() {
		return this.tradeOrder;
	}

	/**
	 * Method setTradeOrder.
	 * 
	 * @param tradeOrder
	 *            TradeOrder
	 */
	public void setTradeOrder(TradeOrder tradeOrder) {
		this.tradeOrder = tradeOrder;
	}

	/**
	 * Method getAccountNumber.
	 * 
	 * @return String
	 */
	@Column(name = "accountNumber", length = 20)
	public String getAccountNumber() {
		return this.accountNumber;
	}

	/**
	 * Method setAccountNumber.
	 * 
	 * @param accountNumber
	 *            String
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * Method getAveragePrice.
	 * 
	 * @return BigDecimal
	 */
	@Column(name = "averagePrice", nullable = false, precision = 11)
	public BigDecimal getAveragePrice() {
		return this.averagePrice;
	}

	/**
	 * Method setAveragePrice.
	 * 
	 * @param averagePrice
	 *            BigDecimal
	 */
	public void setAveragePrice(BigDecimal averagePrice) {
		this.averagePrice = averagePrice;
	}

	/**
	 * Method getCommission.
	 * 
	 * @return BigDecimal
	 */
	@Column(name = "commission", precision = 11)
	public BigDecimal getCommission() {
		return this.commission;
	}

	/**
	 * Method setCommission.
	 * 
	 * @param commission
	 *            BigDecimal
	 */
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	/**
	 * Method getCumulativeQuantity.
	 * 
	 * @return Integer
	 */
	@Column(name = "cumulativeQuantity", nullable = false)
	public Integer getCumulativeQuantity() {
		return this.cumulativeQuantity;
	}

	/**
	 * Method setCumulativeQuantity.
	 * 
	 * @param cumulativeQuantity
	 *            Integer
	 */
	public void setCumulativeQuantity(Integer cumulativeQuantity) {
		this.cumulativeQuantity = cumulativeQuantity;
	}

	/**
	 * Method getExecId.
	 * 
	 * @return String
	 */
	@Column(name = "execId", nullable = false, length = 45)
	public String getExecId() {
		return this.execId;
	}

	/**
	 * Method setExecId.
	 * 
	 * @param execId
	 *            String
	 */
	public void setExecId(String execId) {
		this.execId = execId;
	}

	/**
	 * Method getOrderReference.
	 * 
	 * @return String
	 */
	@Column(name = "orderReference", nullable = true, length = 45)
	public String getOrderReference() {
		return this.orderReference;
	}

	/**
	 * Method setOrderReference.
	 * 
	 * @param orderRefeference
	 *            String
	 */
	public void setOrderReference(String orderReference) {
		this.orderReference = orderReference;
	}

	/**
	 * Method getExchange.
	 * 
	 * @return String
	 */
	@Column(name = "exchange", nullable = false, length = 10)
	public String getExchange() {
		return this.exchange;
	}

	/**
	 * Method setExchange.
	 * 
	 * @param exchange
	 *            String
	 */
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	/**
	 * Method getPermId.
	 * 
	 * @return Integer
	 */
	@Column(name = "permId")
	public Integer getPermId() {
		return this.permId;
	}

	/**
	 * Method setPermId.
	 * 
	 * @param permId
	 *            Integer
	 */
	public void setPermId(Integer permId) {
		this.permId = permId;
	}

	/**
	 * Method getPrice.
	 * 
	 * @return BigDecimal
	 */
	@Column(name = "price", nullable = false, precision = 10)
	public BigDecimal getPrice() {
		return this.price;
	}

	/**
	 * Method setPrice.
	 * 
	 * @param price
	 *            BigDecimal
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * Method getQuantity.
	 * 
	 * @return Integer
	 */
	@Column(name = "quantity", nullable = false)
	public Integer getQuantity() {
		return this.quantity;
	}

	/**
	 * Method setQuantity.
	 * 
	 * @param quantity
	 *            Integer
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * Method getSide.
	 * 
	 * @return String
	 */
	@Column(name = "side", nullable = false, length = 3)
	public String getSide() {
		return this.side;
	}

	/**
	 * Method setSide.
	 * 
	 * @param side
	 *            String
	 */
	public void setSide(String side) {
		this.side = side;
	}

	/**
	 * Method getTime.
	 * 
	 * @return ZonedDateTime
	 */
	@Column(name = "time", nullable = false)
	public ZonedDateTime getTime() {
		return this.time;
	}

	/**
	 * Method setTime.
	 * 
	 * @param time
	 *            ZonedDateTime
	 */
	public void setTime(ZonedDateTime time) {
		this.time = time;
	}

	/**
	 * Method getVersion.
	 * 
	 * @return Integer
	 */
	@Version
	@Column(name = "version")
	public Integer getVersion() {
		return this.version;
	}

	/**
	 * Method setVersion.
	 * 
	 * @param version
	 *            Integer
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * Method clone.
	 * 
	 * @return TradeOrderfill
	 */
	public TradeOrderfill clone() {
		try {
			TradeOrderfill tradeOrderfill = (TradeOrderfill) super.clone();

			return tradeOrderfill;
		} catch (CloneNotSupportedException e) {
			// will never happen
			return null;
		}
	}

	/**
	 * Method toString.
	 * 
	 * @return String
	 */
	public String toString() {
		return "Order Id: " + this.getTradeOrder().getIdTradeOrder() + " OrderKey: "
				+ this.getTradeOrder().getOrderKey() + " Trade Order Version: " + this.getTradeOrder().getVersion()
				+ " Order Fill Id: " + this.getIdTradeOrderFill() + " Order Fill Version: " + this.getVersion()
				+ " Quantity: " + this.getQuantity() + " Avg Price: " + new Money(this.getAveragePrice()) + " Cum Qty: "
				+ this.getCumulativeQuantity() + " Price: " + new Money(this.getPrice()) + " Exchange: "
				+ this.getExchange() + " Side: " + this.getSide() + " Time: " + this.getTime();
	}
}
