package com.sharing.cn.common;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
* 
* @generated
*/
public enum Ordering {
	/**
	 * 
	 * @generated
	 */
	ASC(0, "ASC", "ASC"),

	/**
	 * 
	 * @generated
	 */
	DESC(1, "DESC", "DESC");

	/**
	 * @generated
	 */
	public static final int ASC_VALUE = 0;

	public static final int DESC_VALUE = 1;

	/**
	 * @generated
	 */
	private static final Ordering[] VALUES_ARRAY =
		new Ordering[] {
			ASC,
			DESC,
		};

	/**
	 * @generated
	 */
	public static final List<Ordering> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * @generated
	 */
	public static Ordering get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Ordering result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * @generated
	 */
	public static Ordering getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Ordering result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * @generated
	 */
	public static Ordering get(int value) {
		switch (value) {
			case ASC_VALUE: return ASC;
			case DESC_VALUE: return DESC;
		}
		return null;
	}

	/**
	 * @generated
	 */
	private final int value;

	/**
	 * @generated
	 */
	private final String name;

	/**
	 * @generated
	 */
	private final String literal;

	/**
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * @generated
	 */
	private Ordering(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}
}
