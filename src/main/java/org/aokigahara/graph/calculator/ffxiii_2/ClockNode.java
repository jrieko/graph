package org.aokigahara.graph.calculator.ffxiii_2;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * TODO
 * 
 * @author rieko
 * 
 */
public class ClockNode {
	private int index;
	private int value;

	/**
	 * TODO
	 * 
	 * @param index
	 * @param value
	 */
	public ClockNode(int index, int value) {
		this.index = index;
		this.value = value;
	}

	public int getIndex() {
		return index;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
