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

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.trade.core.dao.Aspect;

/**
 * TradestrategyOrders generated by hbm2java
 * 
 * @author Simon Allen
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "tradestrategy")
public class TradestrategyOrders extends Aspect implements Serializable {

	private static final long serialVersionUID = -2181676329258092177L;

	private ContractLite contract;
	private String status;
	private ZonedDateTime lastUpdateDate;
	private List<TradeOrder> tradeOrders = new ArrayList<TradeOrder>(0);

	public TradestrategyOrders() {
	}

	/**
	 * Method getIdTradeStrategy.
	 * 
	 * @return Integer
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getIdTradeStrategy() {
		return this.id;
	}

	/**
	 * Method setIdTradeStrategy.
	 * 
	 * @param idTradeStrategy
	 *            Integer
	 */
	public void setIdTradeStrategy(Integer idTradeStrategy) {
		this.id = idTradeStrategy;
	}

	/**
	 * Method getContract.
	 * 
	 * @return ContractLite
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "id_contract", insertable = false, updatable = false, nullable = false)
	public ContractLite getContract() {
		return this.contract;
	}

	/**
	 * Method setContract.
	 * 
	 * @param contract
	 *            ContractId
	 */
	public void setContract(ContractLite contract) {
		this.contract = contract;
	}

	/**
	 * Method getStatus.
	 * 
	 * @return String
	 */
	@Column(name = "status", length = 20)
	public String getStatus() {
		return this.status;
	}

	/**
	 * Method setStatus.
	 * 
	 * @param status
	 *            String
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Method getLastUpdateDate.
	 * 
	 * @return ZonedDateTime
	 */
	@Column(name = "last_update_date", nullable = false)
	public ZonedDateTime getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	/**
	 * Method setLastUpdateDate.
	 * 
	 * @param lastUpdateDate
	 *            ZonedDateTime
	 */
	public void setLastUpdateDate(ZonedDateTime lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	/**
	 * Method getTradeOrders.
	 * 
	 * @return List<Trade>
	 */
	@OneToMany(mappedBy = "tradestrategy", fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH })
	public List<TradeOrder> getTradeOrders() {
		return this.tradeOrders;
	}

	/**
	 * Method setTradeOrders.
	 * 
	 * @param tradeOrders
	 *            List<TradeOrder>
	 */
	public void setTradeOrders(List<TradeOrder> tradeOrders) {
		this.tradeOrders = tradeOrders;
	}

	/**
	 * Method addTradeOrder.
	 * 
	 * @param tradeOrders
	 *            TradeOrder
	 */
	public void addTradeOrder(TradeOrder tradeOrders) {
		this.tradeOrders.add(tradeOrders);
	}

	/**
	 * Method getOpenTradePosition.
	 * 
	 * @return TradePosition
	 */
	@Transient
	public TradePosition getOpenTradePosition() {
		return this.getContract().getTradePosition();
	}

	/**
	 * Method hasOpenTradePosition.
	 * 
	 * @return boolean
	 */
	public boolean hasOpenTradePosition() {
		if (null == getOpenTradePosition()) {
			return false;
		}
		return true;
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
}
