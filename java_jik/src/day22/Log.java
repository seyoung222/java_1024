package day22;

import java.util.ArrayList;
import java.util.Date;

import lombok.Data;

@Data
public class Log {
	ArrayList<StudentLog> slogs = new ArrayList<StudentLog>();
	private String date;
	
	public Log(ArrayList<Student>stds, String date) {
		this.date = date;
		for(Student std : stds) {
			StudentLog slog = new StudentLog(std, null);
			slogs.add(slog);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass()) {
			if(! (obj instanceof String)) {
				return false;
			}
			return date.equals(obj);
		}
		Log other = (Log) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}
}
