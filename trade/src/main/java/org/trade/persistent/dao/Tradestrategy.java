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
import java.math.BigDecimal;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

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
import javax.validation.constraints.Min;

import org.trade.core.dao.Aspect;
import org.trade.core.factory.ClassFactory;
import org.trade.core.util.CoreUtils;
import org.trade.core.util.TradingCalendar;
import org.trade.dictionary.valuetype.TradestrategyStatus;
import org.trade.strategy.data.StrategyData;

/**
 * Tradestrategy generated by hbm2java
 * 
 * @author Simon Allen
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "tradestrategy")
public class Tradestrategy extends Aspect implements Serializable, Cloneable {

	private static final long serialVersionUID = -2181676329258092177L;
	@Min(1)
	private Integer chartDays;
	@Min(30)
	private Integer barSize;
	private Contract contract;
	private Tradingday tradingday;
	private Strategy strategy;
	private Portfolio portfolio;
	private String tier;
	private String status;
	private String side;
	private BigDecimal riskAmount;
	private Boolean trade = new Boolean(false);
	private ZonedDateTime lastUpdateDate;

	private StrategyData strategyData = null;
	private TradestrategyStatus tradestrategyStatus = new TradestrategyStatus();
	private List<TradeOrder> tradeOrders = new ArrayList<TradeOrder>(0);
	private List<CodeValue> codeValues = new ArrayList<>(0);

	public Tradestrategy() {
		this.lastUpdateDate = TradingCalendar.getDateTimeNowMarketTimeZone();
	}

	/**
	 * Constructor for Tradestrategy.
	 * 
	 * @param chartDays
	 *            Integer
	 * @param barSize
	 *            Integer
	 * @param name
	 *            String
	 */
	public Tradestrategy(Integer barSize, Integer chartDays, Strategy strategy) {
		this.setBarSize(barSize);
		this.chartDays = chartDays;
		this.strategy = strategy;
		super.setDirty(true);
	}

	/**
	 * Constructor for Tradestrategy.
	 * 
	 * @param contract
	 *            Contract
	 */
	public Tradestrategy(Contract contract) {
		this.contract = contract;
		super.setDirty(true);
	}

	/**
	 * Constructor for Tradestrategy.
	 * 
	 * @param contract
	 *            Contract
	 * @param tradingday
	 *            Tradingday
	 * @param strategy
	 *            Strategy
	 * @param account
	 *            Account
	 * @param riskAmount
	 *            BigDecimal
	 * @param side
	 *            String
	 * @param tier
	 *            String
	 * @param trade
	 *            Boolean
	 * @param chartDays
	 *            Integer
	 * @param barSize
	 *            Integer
	 */
	public Tradestrategy(Contract contract, Tradingday tradingday, Strategy strategy, Portfolio portfolio,
			BigDecimal riskAmount, String side, String tier, Boolean trade, Integer chartDays, Integer barSize) {
		this.setBarSize(barSize);
		this.chartDays = chartDays;
		this.contract = contract;
		this.strategy = strategy;
		this.tradingday = tradingday;
		this.portfolio = portfolio;
		this.riskAmount = riskAmount;
		this.side = side;
		this.tier = tier;
		this.trade = trade;
		this.lastUpdateDate = TradingCalendar.getDateTimeNowMarketTimeZone();
		super.setDirty(true);
	}

	/**
	 * Method getIdTradeStrategy.
	 * 
	 * @return Integer
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idTradeStrategy", unique = true, nullable = false)
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
	 * Method getBarSize.
	 * 
	 * @return Integer
	 */
	@Column(name = "barSize")
	public Integer getBarSize() {
		return this.barSize;
	}

	/**
	 * Method setBarSize.
	 * 
	 * @param barSize
	 *            Integer
	 */
	public void setBarSize(Integer barSize) {
		this.barSize = barSize;
		if (null != barSize && barSize == 1) {
			Duration duration = Duration.between(this.getTradingday().getOpen(), this.getTradingday().getClose());
			long daySeconds = duration.getSeconds();
			this.barSize = ((int) daySeconds) * barSize;
		}
	}

	/**
	 * Method getChartDays.
	 * 
	 * @return Integer
	 */
	@Column(name = "chartDays")
	public Integer getChartDays() {
		return this.chartDays;
	}

	/**
	 * Method setChartDays.
	 * 
	 * @param chartDays
	 *            Integer
	 */
	public void setChartDays(Integer chartDays) {
		this.chartDays = chartDays;
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
		if (null != this.status) {
			tradestrategyStatus.setValue(this.status);
		}
	}

	/**
	 * Method getTradestrategyStatus.
	 * 
	 * @return TradestrategyStatus
	 */
	@Transient
	public TradestrategyStatus getTradestrategyStatus() {
		return tradestrategyStatus;
	}

	/**
	 * Method getRiskAmount.
	 * 
	 * @return BigDecimal
	 */
	@Column(name = "riskAmount", nullable = false, precision = 10)
	public BigDecimal getRiskAmount() {
		return this.riskAmount;
	}

	/**
	 * Method setRiskAmount.
	 * 
	 * @param riskAmount
	 *            BigDecimal
	 */
	public void setRiskAmount(BigDecimal riskAmount) {
		this.riskAmount = riskAmount;
	}

	/**
	 * Method getSide.
	 * 
	 * @return String
	 */
	@Column(name = "side", length = 3)
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
	 * Method getTier.
	 * 
	 * @return String
	 */
	@Column(name = "tier", length = 1)
	public String getTier() {
		return this.tier;
	}

	/**
	 * Method setTier.
	 * 
	 * @param tier
	 *            String
	 */
	public void setTier(String tier) {
		this.tier = tier;
	}

	/**
	 * Method getContract.
	 * 
	 * @return Contract
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "idContract", insertable = true, updatable = true, nullable = false)
	public Contract getContract() {
		return this.contract;
	}

	/**
	 * Method setContract.
	 * 
	 * @param contract
	 *            Contract
	 */
	public void setContract(Contract contract) {
		this.contract = contract;
	}

	/**
	 * Method getTradingday.
	 * 
	 * @return Tradingday
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "idTradingDay", insertable = true, updatable = true, nullable = false)
	public Tradingday getTradingday() {
		return this.tradingday;
	}

	/**
	 * Method setTradingday.
	 * 
	 * @param tradingday
	 *            Tradingday
	 */
	public void setTradingday(Tradingday tradingday) {
		this.tradingday = tradingday;
	}

	/**
	 * Method getStrategy.
	 * 
	 * @return Strategy
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idStrategy", insertable = true, updatable = true, nullable = false)
	public Strategy getStrategy() {
		return this.strategy;
	}

	/**
	 * Method setStrategy.
	 * 
	 * @param strategy
	 *            Strategy
	 */
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * Method getPortfolio.
	 * 
	 * @return Portfolio
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idPortfolio", insertable = true, updatable = true, nullable = false)
	public Portfolio getPortfolio() {
		return this.portfolio;
	}

	/**
	 * Method setPortfolio.
	 * 
	 * @param portfolio
	 *            Portfolio
	 */
	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	/**
	 * Method getTrade.
	 * 
	 * @return Boolean
	 */
	@Column(name = "org/trade", length = 1)
	public Boolean getTrade() {

		return this.trade;
	}

	/**
	 * Method setTrade.
	 * 
	 * @param trade
	 *            Boolean
	 */
	public void setTrade(Boolean trade) {
		this.trade = trade;
	}

	/**
	 * Method getLastUpdateDate.
	 * 
	 * @return ZonedDateTime
	 */
	@Column(name = "lastUpdateDate", nullable = false)
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
	 * Method getTradeOrders.
	 * 
	 * @return List<Trade>
	 */
	@OneToMany(mappedBy = "tradestrategy", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
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
	 * Method getCodeValues.
	 * 
	 * @return List<CodeValue>
	 */
	@OneToMany(mappedBy = "tradestrategy", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	public List<CodeValue> getCodeValues() {
		return this.codeValues;
	}

	/**
	 * Method setCodeValues.
	 * 
	 * @param codeValues
	 *            List<CodeValue>
	 */
	public void setCodeValues(List<CodeValue> codeValues) {
		this.codeValues = codeValues;
	}

	/**
	 * Returns the value associated with for the this name attribute name. For
	 * String data types you should define an classEditorName in the
	 * CodeAttribute table, this should be a
	 * org.trade.dictionary.valuetype.Decode These are presented as a combo box
	 * in the UI for editing. all other data types use JFormattedField.
	 * 
	 * @param name
	 *            the name of the attribute.
	 * 
	 * 
	 * @return The value of the attribute.
	 * @throws Exception
	 */

	@Transient
	public Object getValueCode(String name) throws Exception {
		Object codeValue = null;
		for (CodeValue value : this.getCodeValues()) {
			if (name.equals(value.getCodeAttribute().getName())) {
				Vector<Object> parm = new Vector<Object>();
				parm.add(value.getCodeValue());
				codeValue = ClassFactory.getCreateClass(value.getCodeAttribute().getClassName(), parm, this);
				return codeValue;
			}
		}
		return codeValue;
	}

	/**
	 * Method addTradeOrder.
	 * 
	 * @param tradeOrder
	 *            TradeOrder
	 */
	public void addTradeOrder(TradeOrder tradeOrder) {
		int index = 0;
		for (TradeOrder currTradeOrder : this.tradeOrders) {
			if (CoreUtils.nullSafeComparator(currTradeOrder.getIdTradeOrder(), tradeOrder.getIdTradeOrder()) == 0) {
				index = this.tradeOrders.indexOf(currTradeOrder);
				break;
			}
		}
		if (index > 0)
			this.tradeOrders.remove(index);

		this.tradeOrders.add(tradeOrder);
	}

	/**
	 * Method getStrategyData.
	 * 
	 * @return StrategyData
	 */
	@Transient
	public StrategyData getStrategyData() {
		return this.strategyData;
	}

	/**
	 * Method setStrategyData.
	 * 
	 * @param strategyData
	 *            StrategyData
	 */
	public void setStrategyData(StrategyData strategyData) {
		if (null != this.strategyData) {
			this.strategyData.clearBaseCandleDataset();
		}
		this.strategyData = strategyData;
	}

	/**
	 * Method setDirty.
	 * 
	 * @param dirty
	 *            boolean
	 */
	public void setDirty(boolean dirty) {
		super.setDirty(dirty);
		if (dirty)
			this.getTradingday().setDirty(dirty);
	}

	public static final Comparator<Tradestrategy> DATE_ORDER_ASC = new Comparator<Tradestrategy>() {
		public int compare(Tradestrategy o1, Tradestrategy o2) {
			m_ascending = true;
			int returnVal = 0;

			if (CoreUtils.nullSafeComparator(o1.getTradingday().getOpen(), o2.getTradingday().getOpen()) == 0) {
				if (CoreUtils.nullSafeComparator(o1.getSide(), o2.getSide()) == 0) {
					returnVal = CoreUtils.nullSafeComparator(o1.getTier(), o2.getTier());
				} else {
					returnVal = CoreUtils.nullSafeComparator(o1.getSide(), o2.getSide());
				}

			} else {
				returnVal = CoreUtils.nullSafeComparator(o1.getTradingday().getOpen(), o2.getTradingday().getOpen());

			}

			if (m_ascending.equals(Boolean.FALSE)) {
				returnVal = returnVal * -1;
			}
			return returnVal;
		}
	};

	public static final Comparator<Tradestrategy> TRADINGDAY_CONTRACT = new Comparator<Tradestrategy>() {
		public int compare(Tradestrategy o1, Tradestrategy o2) {
			m_ascending = true;
			int returnVal = 0;

			if (CoreUtils.nullSafeComparator(o1.getTradingday().getOpen(), o2.getTradingday().getOpen()) == 0) {
				if (o1.getContract().equals(o2.getContract())) {
					if (CoreUtils.nullSafeComparator(o1.getBarSize(), o2.getBarSize()) == 0) {
						returnVal = CoreUtils.nullSafeComparator(o1.getChartDays(), o2.getChartDays());
					} else {
						returnVal = CoreUtils.nullSafeComparator(o1.getBarSize(), o2.getBarSize());
					}
				} else {
					returnVal = o1.getContract().getSymbol().compareTo(o2.getContract().getSymbol());
				}

			} else {
				returnVal = CoreUtils.nullSafeComparator(o1.getTradingday().getOpen(), o2.getTradingday().getOpen());

			}

			if (m_ascending.equals(Boolean.FALSE)) {
				returnVal = returnVal * -1;
			}
			return returnVal;
		}
	};

	/**
	 * Method toString.
	 * 
	 * @return String
	 */
	public String toString() {
		if (null != this.getContract()) {
			return this.getContract().getSymbol().toUpperCase();
		}
		return super.toString();
	}

	/**
	 * Method hashCode.
	 * 
	 * For every field tested in the equals-Method, calculate a hash code c by:
	 * 
	 * If the field f is a boolean: calculate * (f ? 0 : 1);
	 * 
	 * If the field f is a byte, char, short or int: calculate (int)f;
	 * 
	 * If the field f is a long: calculate (int)(f ^ (f >>> 32));
	 * 
	 * If the field f is a float: calculate Float.floatToIntBits(f);
	 * 
	 * If the field f is a double: calculate Double.doubleToLongBits(f) and
	 * handle the return value like every long value;
	 * 
	 * If the field f is an object: Use the result of the hashCode() method or 0
	 * if f == null;
	 * 
	 * If the field f is an array: See every field as separate element and
	 * calculate the hash value in a recursive fashion and combine the values as
	 * described next.
	 * 
	 * @return int
	 */
	public int hashCode() {
		int hash = super.hashCode();
		hash = hash + (this.getContract() == null ? 0 : this.getContract().hashCode());
		hash = hash + (this.getStrategy() == null ? 0 : this.getStrategy().hashCode());
		hash = hash + (this.getPortfolio() == null ? 0 : this.getPortfolio().hashCode());
		hash = hash + (this.getTradingday() == null ? 0 : this.getTradingday().hashCode());
		hash = hash + (this.getBarSize() == null ? 0 : this.getBarSize().hashCode());
		hash = hash + (this.getChartDays() == null ? 0 : this.getChartDays().hashCode());
		return hash;
	}

	/**
	 * Method equals.
	 * 
	 * @param objectToCompare
	 *            Object
	 * @return boolean
	 */
	public boolean equals(Object objectToCompare) {

		if (super.equals(objectToCompare))
			return true;

		if (objectToCompare instanceof Tradestrategy) {
			Tradestrategy tradestrategy = (Tradestrategy) objectToCompare;
			if (this.getContract().equals(tradestrategy.getContract())) {
				if (this.getTradingday().getOpen().compareTo(tradestrategy.getTradingday().getOpen()) == 0) {
					if (this.getStrategy().getName().equals(tradestrategy.getStrategy().getName())) {
						if (this.getPortfolio().getName().equals(tradestrategy.getPortfolio().getName())) {
							if (this.getBarSize().equals(tradestrategy.getBarSize())) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * Method isThereOpenTradePosition.
	 * 
	 * @return boolean Is there open trade.
	 * 
	 */
	@Transient
	public boolean isThereOpenTradePosition() {
		for (TradeOrder tradeOrder : this.getTradeOrders()) {
			if (tradeOrder.getIsFilled() && tradeOrder.getTradePosition()
					.equals(tradeOrder.getTradePosition().getContract().getTradePosition())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Method clone.
	 * 
	 * @return Object
	 * @throws CloneNotSupportedException
	 */
	public Object clone() throws CloneNotSupportedException {

		Tradestrategy tradestrategy = (Tradestrategy) super.clone();
		Contract contract = (Contract) this.getContract().clone();
		tradestrategy.setContract(contract);
		Tradingday tradingday = (Tradingday) this.getTradingday().clone();
		tradestrategy.setTradingday(tradingday);
		Portfolio portfolio = (Portfolio) this.getPortfolio().clone();
		tradestrategy.setPortfolio(portfolio);
		Strategy strategy = (Strategy) this.getStrategy().clone();
		tradestrategy.setStrategy(strategy);
		List<TradeOrder> tradeOrders = new ArrayList<TradeOrder>(0);
		tradestrategy.setTradeOrders(tradeOrders);
		return tradestrategy;
	}

}
