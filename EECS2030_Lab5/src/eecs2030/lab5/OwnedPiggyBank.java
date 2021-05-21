package eecs2030.lab5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import java.util.List;

/**
 * A class representing a piggy bank that has an owner. 
 * A piggy bank owns a collection (or possibly collections) of coins,
 * but does not own the coins themselves. In other words,
 * the piggy bank and its collection of coins form a composition.
 * 
 * <p>
 * Only the owner of the piggy bank is able to remove coins
 * from the piggy bank. The piggy bank does own its owner.
 * In other words, the piggy bank and its owner form an
 * aggregation.
 */
public class OwnedPiggyBank {
	/*
	 * YOU NEED A FIELD HERE TO HOLD THE COINS OF THIS PIGGY BANK
	 */
	private List<Coin> ownerCoins;

	/**
	 * The owner of this piggy bank.
	 */
	private Owner owner;

	/**
	 * Initializes this piggy bank so that it has the specified owner
	 * and its collection of coins is empty.
	 * 
	 * @param owner
	 *            the owner of this piggy bank
	 */
	public OwnedPiggyBank(Owner owner) {
		this.owner = owner;
		this.ownerCoins = new ArrayList<>();
		
	}

	/**
	 * Initializes this piggy bank by copying another piggy bank. This piggy
	 * bank will have the same owner and the same number and type of coins as
	 * the other piggy bank.
	 * 
	 * @param other
	 *            the piggy bank to copy
	 */
	public OwnedPiggyBank(OwnedPiggyBank other) {
		this.owner = other.owner;
		this.ownerCoins = new ArrayList<>(other.ownerCoins);
		
	}

	/**
	 * Returns the owner of this piggy bank.
	 * 
	 * <p>
	 * This method is present only for testing purposes. Returning
	 * the owner of this piggy bank allows any user to remove coins
	 * from the piggy bank (because any user can get the owner
	 * of this piggy bank)!
	 * 
	 * @return the owner of this piggy bank
	 */
	public Owner getOwner() {
		// ALREADY IMPLEMENTED; DO NOT MODIFY
		return this.owner;
	}
	
	/**
	 * Allows the current owner of this piggy bank to give this
	 * piggy bank to a new owner.
	 * 
	 * @param currentOwner the current owner of this piggy bank
	 * @param newOwner the new owner of this piggy bank
	 * @throws IllegalArgumentException if currentOwner is not the
	 * current owner of this piggy bank
	 */
	public void changeOwner(Owner currentOwner, Owner newOwner) {
		if(this.getOwner() != currentOwner) {
			throw new IllegalArgumentException("currentOwner is not the current owner of this piggy bank");
		}
		this.owner = newOwner;
		
	}

	/**
	 * Adds the specified coins to this piggy bank.
	 * 
	 * @param coins
	 *            a list of coins to add to this piggy bank
	 */
	public void add(List<Coin> coins) {
		for(int i = 0; i < coins.size(); i++) {
			this.ownerCoins.add(coins.get(i));
		}
		
	}

	/**
	 * Returns true if this piggy bank contains the specified coin, and false
	 * otherwise.
	 * 
	 * @param coin
	 *            a coin
	 * @return true if this piggy bank contains the specified coin, and false
	 *         otherwise
	 */
	public boolean contains(Coin coin) {
		boolean result = true;
		if(!ownerCoins.contains(coin)) {
			result = false;
		}
		return result;
	}

	/**
	 * Allows the owner of this piggy bank to remove a coin equal to the value
	 * of the specified coin from this piggy bank.
	 * 
	 * <p>
	 * If the specified user is not equal to the owner of this piggy bank,
	 * then the coin is not removed from this piggy bank, and null is returned.
	 * 
	 * @param user
	 *            the person trying to remove the coin
	 * @param coin
	 *            a coin
	 * @return a coin equal to the value of the specified coin from this piggy
	 *         bank, or null if user is not the owner of this piggy bank
	 * @pre. the piggy bank contains a coin equal to the specified coin
	 */
	public Coin remove(Owner user, Coin coin) {
		if(!user.equals(owner)) {
			return null;
		}
		this.ownerCoins.remove(coin);
		return coin;
		
	}

	/**
	 * Allows the owner of this piggy bank to remove
	 * the smallest number of coins whose total value in cents is equal
	 * to the specified value in cents from this piggy bank.
	 * 
	 * <p>
	 * Returns the empty list if the specified user is not equal to
	 * the owner of this piggy bank.
	 * 
	 * @param user
	 *            the person trying to remove coins from this piggy bank
	 * @param value
	 *            a value in cents
	 * @return the smallest number of coins whose total value in cents is equal
	 *         to the specified value in cents from this piggy bank 
	 * @pre. the piggy bank contains a group of coins whose total value is equal
	 *         to specified value
	 */
	public List<Coin> removeCoins(Owner user, int value) {
		List<Coin> takeCoin = new ArrayList<Coin>();
		if(!user.equals(owner)) {
			return new ArrayList<Coin>(); 
		}
		Collections.sort(ownerCoins);
		for(int i = ownerCoins.size() - 1; i >= 0; i--) {
			if(ownerCoins.get(i).getValue() <= value) {
				takeCoin.add(ownerCoins.get(i));
				value = value - ownerCoins.get(i).getValue();
			}
		}
		for(Coin c : takeCoin) {
			ownerCoins.remove(c);
		}
		return takeCoin;
	}

	/**
	 * Returns a deep copy of the coins in this piggy bank. The returned list
	 * has its coins in sorted order (from smallest value to largest value;
	 * i.e., pennies first, followed by nickels, dimes, quarters, loonies, and
	 * toonies).
	 * 
	 * @return a deep copy of the coins in this piggy bank
	 */
	public List<Coin> deepCopy() {
		List<Coin> deepIt = new ArrayList<>();
		for(Coin c : this.ownerCoins) {
			deepIt.add(new Coin(c));
		}
		Collections.sort(deepIt);
		return deepIt;
		
	}
}
