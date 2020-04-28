package it.polito.tdp.poweroutages.model;

import java.sql.Date;
import java.time.Instant;

public class Out {
	
	private Instant begin;
	private Instant finish;
	private int hours;
	private int affected;
	private int year;
	
	public Out(Instant begin, Instant finish, int hours, int year, int affected) {
		this.begin = begin;
		this.finish = finish;
		this.affected = affected;
		this.hours = hours;
		this.year = year;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Instant getBegin() {
		return begin;
	}

	public void setBegin(Instant begin) {
		this.begin = begin;
	}

	public Instant getFinish() {
		return finish;
	}

	public void setFinish(Instant finish) {
		this.finish = finish;
	}

	public int getAffected() {
		return affected;
	}

	public void setAffected(int affected) {
		this.affected = affected;
	}

	@Override
	public String toString() {
		return year+" "+begin+" "+finish+" "+hours+" "+affected+"\n";
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}
	
	
	

}
