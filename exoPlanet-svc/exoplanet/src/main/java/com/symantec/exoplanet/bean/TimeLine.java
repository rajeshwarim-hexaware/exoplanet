package com.symantec.exoplanet.bean;

import java.util.Comparator;

public class TimeLine {

	private Integer year;
	private Long small;
	private Long medium;
	private Long large;

	public TimeLine(Integer year, Long small, Long medium, Long large) {
		this.year = year;
		this.small = small;
		this.medium = medium;
		this.large = large;
	}

	public TimeLine() {
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Long getSmall() {
		return small;
	}

	public void setSmall(Long small) {
		this.small = small;
	}

	public Long getMedium() {
		return medium;
	}

	public void setMedium(Long medium) {
		this.medium = medium;
	}

	public Long getLarge() {
		return large;
	}

	public void setLarge(Long large) {
		this.large = large;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((large == null) ? 0 : large.hashCode());
		result = prime * result + ((medium == null) ? 0 : medium.hashCode());
		result = prime * result + ((small == null) ? 0 : small.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeLine other = (TimeLine) obj;
		if (large == null) {
			if (other.large != null)
				return false;
		} else if (!large.equals(other.large))
			return false;
		if (medium == null) {
			if (other.medium != null)
				return false;
		} else if (!medium.equals(other.medium))
			return false;
		if (small == null) {
			if (other.small != null)
				return false;
		} else if (!small.equals(other.small))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	static final Comparator<TimeLine> yearComparator = new Comparator<TimeLine>() {

		@Override
		public int compare(TimeLine t1, TimeLine t2) {
			return t1.year - t2.year;
		}

	};

	public static final Comparator<TimeLine> smallComparator = new Comparator<TimeLine>() {

		@Override
		public int compare(TimeLine t1, TimeLine t2) {
			return (int) (t1.small - t2.small);
		}

	};
	public static final Comparator<TimeLine> mediumComparator = new Comparator<TimeLine>() {

		@Override
		public int compare(TimeLine t1, TimeLine t2) {
			return (int) (t1.medium - t2.medium);
		}

	};

	public static final Comparator<TimeLine> largeComarator = new Comparator<TimeLine>() {

		@Override
		public int compare(TimeLine t1, TimeLine t2) {
			return (int) (t1.large - t2.large);
		}

	};
}
