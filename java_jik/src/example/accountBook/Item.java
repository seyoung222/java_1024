package example.accountBook;

import lombok.Data;

@Data
public class Item {
	private String inAndOut, category, contents;
	private int amount, year,month,day;
	
	public Item(String inAndOut, String category, String contents, int amount, int year, int month, int day) {
		this.inAndOut = inAndOut;
		this.category = category;
		this.contents = contents;
		this.amount = amount;
		this.year = year;
		this.month = month;
		this.day = day;
	}
	@Override
	public String toString() {
		return inAndOut+" | "+ year+"-"+month+"-"+day+" | "+category+" | "+contents+
				" | "+amount;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + day;
		result = prime * result + month;
		result = prime * result + year;
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
		Item other = (Item) obj;
		if (day != other.day)
			return false;
		if (month != other.month)
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	public Item(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
}
